package xmu.crms.view;

//这个包手动添加,包含了GET,POST等声明
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.math.BigInteger;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.GradeService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.UserService;
import xmu.crms.service.impl.*;

import xmu.crms.view.vo.*;
import xmu.crms.dao.CourseDAO;
import xmu.crms.entity.*;

/**
 * @author LUWEIW
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
    CourseService courseService;
	@Autowired
     ClassService classService;
	@Autowired
     UserService userService;
	@Autowired
     SeminarService seminarService;
	@Autowired
     SeminarGroupService seminarGroupService;
	@Autowired
     GradeService gradeService;

    @RequestMapping(method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<UserCourseVO> getUserCourses(@RequestParam BigInteger userId,
                                             HttpServletResponse response) throws IllegalArgumentException, ClassesNotFoundException, UserNotFoundException {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserCourseVO> courseVOList = new ArrayList<UserCourseVO>();

        try {
            List<Course> courseList = courseService.listCourseByUserId(userId);
            for (Course course : courseList) {
                List<ClassInfo> classInfoList = classService.listClassByCourseId(course.getId());
                List<User> userList = new ArrayList<User>();
                for (ClassInfo classInfo : classInfoList) {
                    userList.addAll(userService.listUserByClassId(classInfo.getId(), "", ""));
                }
                UserCourseVO courseVO = new UserCourseVO(course, classInfoList.size(), userList.size());
                courseVOList.add(courseVO);
            }
            response.setStatus(200);
        } catch(CourseNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
        }
        return courseVOList;
    }

    @RequestMapping(method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public int createCourse(@RequestBody CourseVO courseVO,
                            @RequestParam BigInteger userId,HttpServletResponse response) {
        //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            User teacher = userService.getUserByUserId(userId);
            Course course = new Course(courseVO, teacher);
            int id = courseService.insertCourseByUserId(userId, course).intValue();
            response.setStatus(204);
            return id;
        } catch(UserNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = "/{courseId}", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public CourseByIdVO getCourseById(@PathVariable("courseId") int courseId, HttpServletResponse response) {
        try {
            Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
            CourseByIdVO courseByIdVO = new CourseByIdVO(course);
            return courseByIdVO;
        } catch(CourseNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{courseId}", method = PUT)
    public Response updateCourse(@PathVariable("courseId") int courseId,
                                 @RequestBody CourseVO courseVO, HttpServletResponse response) {
        try {
            Course course = new Course(courseVO);
            courseService.updateCourseByCourseId(BigInteger.valueOf(courseId), course);
            response.setStatus(204);
            return null;
        } catch(Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/{courseId}", method = DELETE)
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public Response deleteCourseById(@PathVariable("courseId") int courseId,
                                     HttpServletResponse response) {
        courseService.deleteCourseByCourseId(BigInteger.valueOf(courseId));
        response.setStatus(204);
        return null;
    }

    @RequestMapping(value = "/{courseId}/class", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<ClassVO> getClassByCourseId(@PathVariable("courseId") int courseId, HttpServletResponse response) {
        try {
            List<ClassInfo> classInfoList = classService.listClassByCourseId(BigInteger.valueOf(courseId));
            List<ClassVO> classVOList = new ArrayList<ClassVO>();
            for (ClassInfo classInfo : classInfoList) {
                classVOList.add(new ClassVO(classInfo.getId().intValue(), classInfo.getName()));
            }
            response.setStatus(200);
            return classVOList;
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return  null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/{courseId}/class", method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public int createClassByCourseId(@PathVariable("courseId") int courseId,
                                     @RequestBody ClassCreateVO classCreateVO, HttpServletResponse response) {
        try {
            ClassInfo classInfo = new ClassInfo(classCreateVO);
            int id = classService.insertClassById(BigInteger.valueOf(courseId), classInfo).intValue();
            response.setStatus(201);
            return id;
        } catch(CourseNotFoundException e) {
            response.setStatus(404);
            return 0;
        }
    }

    @RequestMapping(value = "/{courseId}/seminar", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<SeminarVO> getSeminarsByCourseId(@PathVariable("courseId") int courseId,
                                                 @RequestParam BigInteger userId,HttpServletResponse respons) {
        try {/*
            List<SeminarGroup> seminarGroupList = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
            System.out.println(seminarGroupList);
            List<SeminarVO> seminarVOList = new ArrayList<SeminarVO>();
            for (SeminarGroup seminarGroup : seminarGroupList) {
                seminarVOList.add(new SeminarVO(seminarGroup.getSeminar(), seminarGroup.getFinalGrade()));
                
            }
            */
            //List<ClassInfo> classInfoList = classService.listClassByUserId(userId);
            List<SeminarVO> seminarVOList = new ArrayList<SeminarVO>();
            List<Seminar> seminarList = seminarService.listSeminarByCourseId(BigInteger.valueOf(courseId));
            List<SeminarGroup> seminarGroupList = seminarGroupService.listSeminarGroupIdByStudentId(userId);
            for (Seminar seminar : seminarList) {
                int grade = -1;
                for (SeminarGroup seminarGroup : seminarGroupList) {
                    if (seminarGroup.getSeminar().getId() == seminar.getId())
                        grade = seminarGroup.getFinalGrade();
                }
                seminarVOList.add(new SeminarVO(seminar, grade));
            }
            respons.setStatus(200);
            return seminarVOList;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/{courseId}/seminar", method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public int createSeminarByCourseId(@PathVariable("courseId") int courseId, SeminarCreateVO seminarCreateVO,
                                       HttpServletResponse response) {
        try{
            Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
            Seminar seminar = new Seminar(seminarCreateVO, course);
            int id = seminarService.insertSeminarByCourseId(BigInteger.valueOf(courseId), seminar).intValue();
            response.setStatus(201);
            return id;
        } catch(CourseNotFoundException e) {
            response.setStatus(404);
            return 0;
        }
    }

    @RequestMapping(value = "/{courseId}/seminar/current", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public SeminarCurrentVO getCurrentSeminarByCourseId(@PathVariable("courseId") int courseId, HttpServletResponse response) {
        try {
            List<Seminar> seminarList = seminarService.listSeminarByCourseId(BigInteger.valueOf(courseId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = sdf.parse(sdf.format(new Date()));
            for (Seminar seminar : seminarList) {
                if (seminar.getStartTime().getTime() <= now.getTime() && seminar.getEndTime().getTime() >= now.getTime()) {
                    List<ClassInfo> classInfoList = classService.listClassByCourseId(seminar.getCourse().getId());
                    List<ClassVO> classVOList = new ArrayList<ClassVO>();
                    for (ClassInfo classInfo : classInfoList) {
                        classVOList.add(new ClassVO(classInfo.getId().intValue(), classInfo.getName()));
                    }
                    SeminarCurrentVO seminarCurrentVO = new SeminarCurrentVO(seminar, classVOList);
                    response.setStatus(200);
                    return seminarCurrentVO;
                }
            }
        } catch(CourseNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @RequestMapping(value = "/{courseId}/grade", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<SeminarGradeVO> getGradesByCourseId(@PathVariable("courseId") int courseId,
                                                    @RequestParam BigInteger userId,HttpServletResponse response) {
        try {
            //BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<SeminarGroup> seminarGroupList = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));

            List<SeminarGradeVO> seminarGradeVOList = new ArrayList<SeminarGradeVO>();
            for (SeminarGroup seminarGroup : seminarGroupList) {
                seminarGradeVOList.add(new SeminarGradeVO(seminarGroup));
            }
            response.setStatus(200);
            return seminarGradeVOList;
        } catch(Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
