package xmu.crms.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import xmu.crms.entity.*;
import xmu.crms.service.*;
import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

/**
 * @author LUWEIW
 */

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;
    //public CourseServiceImpl courseService;
    @Autowired
    UserService userService;
    @Autowired
    FixGroupService fixGroupService;
    @Autowired
    SeminarGroupService seminarGroupService;

    @RequestMapping(method = GET)
    @ResponseBody
    public List<CourseClassVO> getClasses(@RequestParam(required=false) String courseName,
    		@RequestParam(required=false) String courseTeacher,
    		@RequestParam BigInteger userId, HttpServletResponse response) {
        try {
            //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	List<ClassInfo> classInfoList = classService.listClassByUserId(userId);
            List<CourseClassVO> courseClassVOList = new ArrayList<CourseClassVO>();
        	if(courseName == null && courseTeacher == null) {
        		for (ClassInfo classInfo : classInfoList) {List<User> userList = userService.listUserByClassId(classInfo.getId(), "", "");
            		int numStudent = userList.size();
            		courseClassVOList.add(new CourseClassVO(classInfo, numStudent));
            	}
            } else {
            	for (ClassInfo classInfo : classInfoList) {
            		if (classInfo.getCourse().getName() == courseName && classInfo.getCourse().getTeacher().getName() == courseTeacher) {
            			List<User> userList = userService.listUserByClassId(classInfo.getId(), "", "");
            			int numStudent = userList.size();
            			courseClassVOList.add(new CourseClassVO(classInfo, numStudent));
            		}
            	}
            }
            return courseClassVOList;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}", method = GET)
    @ResponseBody
    public ClassByIdVO getClassById(@PathVariable("classId") int classId, HttpServletResponse response) throws ClassesNotFoundException {
        try {
            ClassInfo classInfo = classService.getClassByClassId(BigInteger.valueOf(classId));
            List<User> userList = userService.listUserByClassId(BigInteger.valueOf(classId), null, null);
            int numStudent = userList.size();
            ClassByIdVO classByIdVO = new ClassByIdVO(classInfo, numStudent);
            response.setStatus(200);
            return classByIdVO;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}", method = PUT)
    public Response updateClassById(@PathVariable("classId") int classId,
                                    ClassUpdateVO classUpdateVO, HttpServletResponse response) {
        try {
            ClassInfo classInfo = classService.getClassByClassId(BigInteger.valueOf(classId));
            classInfo.setName(classUpdateVO.getName());
            classInfo.setClassTime(classUpdateVO.getTime());
            classInfo.setSite(classUpdateVO.getSite());
            classInfo.setReportPercentage(classUpdateVO.getProportions().getReport());
            classInfo.setPresentationPercentage(classUpdateVO.getProportions().getPresentation());
            classInfo.setFivePointPercentage(classUpdateVO.getProportions().getC());
            classInfo.setFourPointPercentage(classUpdateVO.getProportions().getB());
            classInfo.setThreePointPercentage(classUpdateVO.getProportions().getA());
            classService.updateClassByClassId(BigInteger.valueOf(classId), classInfo);
            response.setStatus(204);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }
    /*
    @RequestMapping(value ="/{classId}", method = PUT)
    public Response updateClassCallingByClassId(@PathVariable("classId") int classId,
                                                CallingVO callingVO, HttpServletResponse response) {
        try {
            Location location = classService.get
            if (callingVO.getCalling() != -1) {

            } else {

            }
            Location location = classService.
            response.setStatus(204);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }
    */

    @RequestMapping(value = "/{classId}", method = DELETE)
    public Response deleteClassById(@PathVariable("classId") int classId, HttpServletResponse response) {
        try {
            classService.deleteClassByClassId(BigInteger.valueOf(classId));
            response.setStatus(204);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }

    }

    @RequestMapping(value = "/{classId}/student", method = GET)
    @ResponseBody
    public List<StudentVO> selectClass1(@PathVariable("classId") int classId,
                                        @RequestParam String numBeginWith,
                                        @RequestParam String nameBeginWith, HttpServletResponse response) {
        try {
            List<User> userList = userService.listUserByClassId(BigInteger.valueOf(classId), numBeginWith, nameBeginWith);
            List<StudentVO> studentVOList = new ArrayList<StudentVO>();
            for (User user : userList) {
                studentVOList.add(new StudentVO(user));
            }
            response.setStatus(200);
            return studentVOList;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/student", method = POST)
    @ResponseBody
    public Response selectClass2(@PathVariable("classId") int classId,
                                 @RequestParam BigInteger userId,
                                 IdVO id, HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            classService.insertCourseSelectionById(userId, BigInteger.valueOf(classId));
            response.setStatus(201);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/student/{studentId}", method = DELETE)
    public Response deselectClass(@PathVariable("classId") int classId,
                                  @PathVariable("studentId") int studentId,
                                  HttpServletResponse response) {
        try {
            classService.deleteCourseSelectionById(BigInteger.valueOf(studentId), BigInteger.valueOf(classId));
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/classgroup", method = GET)
    @ResponseBody
    public ClassGroupVO getClassGroupByClassId(@PathVariable("classId") int classId,
                                               @RequestParam BigInteger userId,
                                               HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, BigInteger.valueOf(classId));
            List<User> members = fixGroupService.listFixGroupMemberByGroupId(fixGroup.getId());
            ClassGroupVO classGroupVO = new ClassGroupVO(fixGroup, members);
            return classGroupVO;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (FixGroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/classgroup/resign", method = PUT)
    public Response resignClassGroupLeader(@PathVariable("classId") int classId,
                                           @RequestParam BigInteger userId,
                                           IdVO id, HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, BigInteger.valueOf(classId));
            seminarGroupService.resignLeaderById(fixGroup.getId(), userId);
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/classgroup/assign", method = PUT)
    public Response assignClassGroupLeader(@PathVariable("classId") int classId,
                                           @RequestParam BigInteger userId,
                                           IdVO id, HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, BigInteger.valueOf(classId));
            seminarGroupService.assignLeaderById(fixGroup.getId(), userId);
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(409);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/classgroup/add", method = PUT)
    public Response addClassgroupMember(@PathVariable("classId") int classId,
                                        @RequestParam BigInteger userId,
                                        IdVO id, HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, BigInteger.valueOf(classId));
            //当前无该小组
            if (fixGroup == null) {
                fixGroupService.insertFixGroupByClassId(BigInteger.valueOf(classId), BigInteger.valueOf(id.getId()));
            } else {
                fixGroupService.insertStudentIntoGroup(BigInteger.valueOf(id.getId()), fixGroup.getId());
            }
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (FixGroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{classId}/classgroup/remove", method = PUT)
    public Response removeClassgroupMember(@PathVariable("classId") int classId,
                                           @RequestParam BigInteger userId,
                                           IdVO id, HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, BigInteger.valueOf(classId));
            //当前无该小组
            if (fixGroup == null) {
                fixGroupService.deleteFixGroupByClassId(BigInteger.valueOf(classId));
            } else {
                fixGroupService.deleteFixGroupUserById(fixGroup.getId(), BigInteger.valueOf(id.getId()));
            }
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (FixGroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }
}
