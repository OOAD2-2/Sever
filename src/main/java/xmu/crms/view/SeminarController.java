package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import xmu.crms.dao.TopicDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.ClassMapper;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.TopicService;
import xmu.crms.service.UserService;
import xmu.crms.service.impl.CourseServiceImpl;
import xmu.crms.service.impl.SeminarServiceImpl;
import xmu.crms.service.impl.TopicServiceImpl;
import xmu.crms.view.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 */

@Controller
@RequestMapping("/seminar")
@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
public class SeminarController {

	@Autowired
	SeminarService seminarService;

	@Autowired
	TopicService topicService;

	@Autowired
	ClassService classService;

	@Autowired
	ClassMapper classMapper;

	//@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	SeminarGroupService seminarGroupService;

    @RequestMapping(value = "/{seminarId}", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSeminarById(@PathVariable("seminarId") int seminarId) throws IllegalArgumentException, SeminarNotFoundException {
        SeminarByIdVO seminarByIdVO = new SeminarByIdVO();
        try {
	        Seminar seminar=seminarService.getSeminarBySeminarId(new BigInteger(String.valueOf(seminarId)));
	        seminarByIdVO.setId(seminar.getId().intValue());
	        seminarByIdVO.setName(seminar.getName());
	        seminarByIdVO.setDescription(seminar.getDescription());
	        if(seminar.getFixed()) {
				seminarByIdVO.setGroupingMethod("Fixed");
			} else {
				seminarByIdVO.setGroupingMethod("Random");
			}
	        seminarByIdVO.setStartTime(seminar.getStartTime().toString());
	        seminarByIdVO.setEndTime(seminar.getEndTime().toString());
	        List<SeminarTopicVO> topicVOs = new ArrayList<SeminarTopicVO>();
	        List<Topic> topics=topicService.listTopicBySeminarId(new BigInteger(String.valueOf(seminarId)));
	        for(Topic t:topics)
	        {
	        	topicVOs.add(new SeminarTopicVO(t.getId().intValue(),t.getName()));
	        }
	        seminarByIdVO.setTopicVOList(topicVOs);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarByIdVO);
    	}
        catch (SeminarNotFoundException e)
        {
        	return ResponseEntity.status(404).build();
        }
    }

    @RequestMapping(value = "/{seminarId}", method = PUT)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity updateSeminarById(@PathVariable("seminarId") int seminarId,
											@RequestParam BigInteger userId,
    		HttpServletRequest httpServletRequest) throws java.text.ParseException, IOException {
    	try {

			BufferedReader br = httpServletRequest.getReader();

			String str, wholeStr = "";

			while((str = br.readLine()) != null){

				wholeStr += str;

			}
			SeminarUpdateVO seminarUpdateVO = new SeminarUpdateVO(wholeStr);

			Seminar seminar = new Seminar();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        seminar.setId(new BigInteger(String.valueOf(seminarId)));
	        seminar.setName(seminarUpdateVO.getName());
	        seminar.setDescription (seminarUpdateVO.getDescription());
	        seminar.setFixed("fixed".equals(seminarUpdateVO.getGroupingMethod()));
	        seminar.setStartTime(simpleDateFormat.parse(seminarUpdateVO.getStartTime()));
			seminar.setEndTime (simpleDateFormat.parse(seminarUpdateVO.getEndTime()));
			seminarService.updateSeminarBySeminarId(BigInteger.valueOf(seminarId), seminar);

			return ResponseEntity.status(204).build();

		} catch (SeminarNotFoundException e) {

			e.printStackTrace();

			return ResponseEntity.status(404).build();

		}
    }

    @RequestMapping(value = "/{seminarId}", method = DELETE)
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity deleteSeminarById(@PathVariable("seminarId") int seminarId,
                                      HttpServletResponse response) {
    	try {

			seminarService.deleteSeminarBySeminarId(BigInteger.valueOf(seminarId));

			return ResponseEntity.status(204).build();

		} catch (SeminarNotFoundException e) {

			e.printStackTrace();

			return ResponseEntity.status(404).build();

		}
    }

    @RequestMapping(value = "/{seminarId}/my", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getStudentSeminarById(@PathVariable("seminarId") int seminarId,
    		@RequestParam BigInteger classId) {
    	try {
    		StudentSeminarVO studentSeminarVO=new StudentSeminarVO();
	        Seminar seminar=seminarService.getSeminarBySeminarId(new BigInteger(String.valueOf(seminarId)));
	        Location location=classService.getCallStatusById(classId, BigInteger.valueOf(seminarId));
	        if(location!=null) {
				studentSeminarVO.setClassCalling(location.getStatus());
			} else {
				studentSeminarVO.setClassCalling(0);
			}
	        studentSeminarVO.setId(seminar.getId().intValue());
	        studentSeminarVO.setName(seminar.getName());
	        studentSeminarVO.setCourseName(seminar.getCourse().getName());
	        if(seminar.getFixed()) {
				studentSeminarVO.setGroupingMethod("fixed");
			} else {
				studentSeminarVO.setGroupingMethod("random");
			}
	        studentSeminarVO.setStartTime(seminar.getStartTime().toString());
	        studentSeminarVO.setEndTime(seminar.getEndTime().toString());
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentSeminarVO);
    	}
        catch (SeminarNotFoundException e)
        {
        	return ResponseEntity.status(404).build();
        }
    }

    @RequestMapping(value = "/{seminarId}/detail", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public SeminarDetailVO getSeminarDetailById(@PathVariable("seminarId") int seminarId,
    		HttpServletResponse response) throws IllegalArgumentException, UserNotFoundException, CourseNotFoundException {
    	try {
	        Seminar seminar=seminarService.getSeminarBySeminarId(new BigInteger(String.valueOf(seminarId)));
	        SeminarDetailVO seminarDetailVO=new SeminarDetailVO(seminar);
	        List<ClassInfo> classInfos=classService.listClassByCourseId(seminar.getCourse().getId());
	        seminarDetailVO.setTeacherName(seminar.getCourse().getTeacher().getName());
	        seminarDetailVO.setTeacherEmail(seminar.getCourse().getTeacher().getEmail());
	        seminarDetailVO.setSite(classInfos.get(0).getSite());
	        System.out.println(seminarDetailVO);
	        response.setStatus(200);
	        return seminarDetailVO;
    	}
        catch (SeminarNotFoundException e)
        {
        	response.setStatus(404);
        	return null;
        }
    }

    @RequestMapping(value = "/{seminarId}/topic", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getTopicsBySeminarId(@PathVariable("seminarId") int seminarId) {

        List<Topic> topics = topicService.listTopicBySeminarId(new BigInteger(String.valueOf(seminarId)));
		List<TopicVO> topicVOs= new ArrayList<TopicVO>();
		for(Topic t:topics)
		{
			TopicVO temp=new TopicVO(t);
			temp.setGroupLeft(t.getGroupNumberLimit()-seminarGroupService.listGroupByTopicId(t.getId()).size());
			topicVOs.add(temp);
		}
      return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topicVOs);
    }

    @RequestMapping(value = "/{seminarId}/topic", method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity createTopicBySeminarId(@PathVariable("seminarId") int seminarId,
												 @RequestParam BigInteger userId,HttpServletRequest httpServletRequest) throws IOException {
    	BufferedReader br = httpServletRequest.getReader();
    	String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		AddTopicVO addTopicVO = new AddTopicVO(wholeStr);
		System.out.println(addTopicVO.toString());
		Topic topic = new Topic(addTopicVO);
		BigInteger id = topicService.insertTopicBySeminarId(BigInteger.valueOf(seminarId), topic);
		Map<String, BigInteger> result = new HashMap<String, BigInteger>();
		result.put("id", id);
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
    }

    @RequestMapping(value = "/{seminarId}/group", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getGroupBySeminarId(@RequestParam(value="gradeable", required=false) String gradeable,
											  @RequestParam(value="classId", required=false) int classId,
											  @RequestParam(value="userId", required=false) int userId,
											  @PathVariable("seminarId") int seminarId) throws IllegalArgumentException, SeminarNotFoundException {


    	String falseString = "false";
		String trueString = "true";
		if (classId!=0 && userId!=0&& falseString.equals(gradeable)) {
			List <SeminarGroupTopicsVO> seminarGroupTopicsVOList = new ArrayList<SeminarGroupTopicsVO>();
			try {
				List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(BigInteger.valueOf(seminarId));
				for (SeminarGroup seminarGroup : seminarGroups) {
					System.out.println(seminarGroup);
					if (seminarGroup.getClassInfo().getId().intValue() == classId) {
						List<Topic> topicList = topicService.listTopicBySeminarId(seminarGroup.getId());

						SeminarGroupTopicsVO seminarGroupTopicsVO = new SeminarGroupTopicsVO(seminarGroup, topicList);

						seminarGroupTopicsVOList.add(seminarGroupTopicsVO);
					}
				}
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGroupTopicsVOList);
			} catch (SeminarNotFoundException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		}

        else if(trueString.equals(gradeable))
        {
        		try {
            		List<SeminarGroup> listGroup=seminarGroupService.listSeminarGroupBySeminarId(BigInteger.valueOf(seminarId));

            		List<SeminarOtherGradeVO> listGroupGradeale=new ArrayList<SeminarOtherGradeVO>();
            		List<Topic> listTopic=topicService.listTopicBySeminarId(BigInteger.valueOf(seminarId));

            		SeminarGroup myGroup=seminarGroupService.getSeminarGroupById(BigInteger.valueOf(seminarId), BigInteger.valueOf(userId));

            		List<SeminarGroupTopic> listGroupTopic=topicService.listSeminarGroupTopicByGroupId(myGroup.getId());

            		List<BigInteger> listTopicId=new  ArrayList<BigInteger>();
            		for(SeminarGroupTopic a:listGroupTopic)
            		{
            			listTopicId.add(a.getTopic().getId());
            		}

            		for(SeminarGroup a:listGroup)
            		{
            			System.out.println(a);
	            		if(!a.getId().equals(myGroup.getId())&&a.getClassInfo().getId().equals(myGroup.getClassInfo().getId()))
	            		{
	            			List<SeminarGroupTopic> listOtherGroupTopic=topicService.listSeminarGroupTopicByGroupId(a.getId());
	            			ArrayList<Integer> judge=new ArrayList<Integer>(listOtherGroupTopic.size());
	            			for(SeminarGroupTopic b:listOtherGroupTopic)
	            			{
	            				boolean flag=true; 
	            				for(BigInteger c:listTopicId)
	                    		{
	                    			if(!c.equals(b.getTopic().getId())) {
										flag=false;
									}
	                    		}
	            				if(flag==false) {
									listGroupGradeale.add(new SeminarOtherGradeVO(a.getId().intValue(),b.getTopic().getId().intValue()));
								}
	            			}
	            		}
            		}
            		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listGroupGradeale);
        		} catch (GroupNotFoundException e) {
        			e.printStackTrace();
        			return ResponseEntity.status(404).build();
        		}

        }
        else if(classId!=0)
        {
        	try {
        		List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(BigInteger.valueOf(seminarId));
        		List<SeminarGroupTopicVO> list=new ArrayList<SeminarGroupTopicVO>();
     			for(SeminarGroup t:seminarGroups)
     			{
     				if(t.getClassInfo().getId().toString().equals(classId))
     				{
     					List<SeminarGroupTopic> seminarGroupTopics = topicService.listSeminarGroupTopicByGroupId(t.getId());
     					for(SeminarGroupTopic p:seminarGroupTopics)
     					{
     						if(p.getTopic().getSeminar().getId().toString().equals(seminarId)) {
								list.add(new SeminarGroupTopicVO(t.getId().intValue(), p.getTopic().getName()));
							}
     					}
     				}
     			}
    			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(list);
    		} catch (SeminarNotFoundException e) {
    			e.printStackTrace();
    			return ResponseEntity.status(404).build();
    		}
        }
        else if(userId!=0)
        {
        	try {
        		SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(BigInteger.valueOf(seminarId), BigInteger.valueOf(Integer.valueOf(userId)));
    			SeminarGroupVO t=new SeminarGroupVO(seminarGroup.getId().intValue());
        		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(t);
    		} catch (GroupNotFoundException e) {
    			e.printStackTrace();
    			return ResponseEntity.status(404).build();
    		}
        }
        else
        {
        try {
 			List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(BigInteger.valueOf(seminarId));
 			List<SeminarGroupVO> list=new ArrayList<SeminarGroupVO>();
 			for(SeminarGroup t:seminarGroups)
 			{
 				list.add(new SeminarGroupVO(t.getId().intValue()));
 			}
 			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(list);
 		} catch (SeminarNotFoundException e) {
 			e.printStackTrace();
 			return ResponseEntity.status(404).build();
 		}
 		}
    }
    @RequestMapping(value = "/{seminarId}/group/my", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getStudentGroupBySeminarId(@PathVariable("seminarId") int seminarId,
													 @RequestParam BigInteger userId) {
    	MemberVO leader = null;
    	List<MemberVO> members;
    	try {
			SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(BigInteger.valueOf(seminarId), userId);
			System.out.println(seminarGroup);
			List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(seminarGroup.getId());
			try {
			leader = new MemberVO(seminarGroup.getLeader().getNumber(),seminarGroup.getLeader().getName());
			leader.setNumber(seminarGroup.getLeader().getId().toString());
			members = new ArrayList<MemberVO>();
			for (User user : member) {
				if(!user.getNumber().equals(leader.getId())) {
					members.add(new MemberVO(user.getNumber(),user.getName()));
				}
			}
			}
			catch(Exception e)
			{
				leader=new MemberVO();
				leader.setId("0");
				members = new ArrayList<MemberVO>();
				for (User user : member) {
					members.add(new MemberVO(user.getNumber(),user.getName()));
				}
			}
			
			List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
			List<MyTopicVO> topics = new ArrayList<MyTopicVO>();
			for (SeminarGroupTopic seminarGroupTopic : list) {
				System.out.println(seminarGroupTopic);
				MyTopicVO topicVO = new MyTopicVO(seminarGroupTopic.getTopic().getId().toString(), seminarGroupTopic.getTopic().getName());
				topics.add(topicVO);
			}
			 StudentGroupVO studentGroupVO = new StudentGroupVO(seminarGroup.getId().intValue(), seminarGroup.getClassInfo().getId()+"-"+seminarGroup.getId(), leader, members, topics);
			System.out.println(studentGroupVO);
			 return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentGroupVO);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSeminarClassAttendance(@PathVariable("seminarId") int seminarId,
                                                              @PathVariable("classId") int classId,
													@RequestParam BigInteger userId) throws IllegalArgumentException, ClassesNotFoundException {
       try {
			List<Attendance> list1=userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			int presentNum=0;
			for (Attendance attendance : list1) {
			    if (attendance.getAttendanceStatus() == 0) {
					presentNum++;
				}
            }
			List<User> userList = userService.listUserByClassId(BigInteger.valueOf(classId), "", "");
			int numStudent = userList.size();
			Location location = new Location();
			if (classService.getCallStatusById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId)) == null) {
				ClassInfo classInfo = classService.getClassByClassId(BigInteger.valueOf(classId));
				Seminar seminar = seminarService.getSeminarBySeminarId(BigInteger.valueOf(seminarId));
				location = new Location(BigInteger.valueOf(0), classInfo, seminar, 0.0, 0.0, 0);
				classMapper.CallInRollById(location);
			} else {
				location = classService.getCallStatusById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			}
			SeminarClassAttendanceVO seminarClassAttendanceVO = new SeminarClassAttendanceVO();
	        seminarClassAttendanceVO.setNumPresent(presentNum);
	        seminarClassAttendanceVO.setNumStudent(userList.size());
	        if(location.getStatus()==1)
	        {
	        	seminarClassAttendanceVO.setStatus("calling");
	        	seminarClassAttendanceVO.setGroup("grouping");
	        }
	        else
	        {
	        	seminarClassAttendanceVO.setStatus("callend");
	        	seminarClassAttendanceVO.setGroup("groupend");
	        }

			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarClassAttendanceVO);

       }
		catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
		catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/present", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSeminarClassPresent(@PathVariable("seminarId") int seminarId,
												 @PathVariable("classId") int classId) throws IllegalArgumentException, ClassesNotFoundException {

		try {
			List<User> list1=userService.listPresentStudent(BigInteger.valueOf(seminarId), BigInteger.valueOf(classId));
			List<MemberVO> members = new ArrayList<MemberVO>();
			for (User t : list1) {
				members.add(new MemberVO(t.getNumber(),t.getName()));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(members);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/late", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSeminarClassLate(@PathVariable("seminarId") int seminarId,
                                              @PathVariable("classId") int classId) throws IllegalArgumentException, ClassesNotFoundException {
    	try {
			List<User> list1=userService.listLateStudent(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			List<MemberVO> members = new ArrayList<MemberVO>();
			for (User t : list1) {
				members.add(new MemberVO(t.getId().toString(),t.getName()));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(members);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/absent", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSeminarClassAbsent(@PathVariable("seminarId") int seminarId,
                                              @PathVariable("classId") int classId) throws IllegalArgumentException, ClassesNotFoundException {
    	try {
			List<User> list1=userService.listAbsenceStudent(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			List<MemberVO> members = new ArrayList<MemberVO>();
			for (User t : list1) {
				members.add(new MemberVO(t.getNumber(),t.getName()));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(members);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/{studentId}", method = PUT)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity updateAttendance(@PathVariable("seminarId") int seminarId,
                                   @PathVariable("classId") int classId,
                                   @PathVariable("studentId") int studentId,
                                   @RequestParam String latitude,
                                   @RequestParam String longitude,
                                   @RequestParam String elevation,
                                   HttpServletRequest httpServletRequest) throws NumberFormatException, IllegalArgumentException, ClassesNotFoundException, UserNotFoundException {
    	try {
			System.out.println(userService.insertAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId), BigInteger.valueOf(studentId), Double.valueOf(longitude), Double.valueOf(latitude)));
			Location location=classService.getCallStatusById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			AttendanceStatusVO temp;
			if(location.getStatus()==1) {
				temp=new AttendanceStatusVO("ontime");
			} else {
				temp=new AttendanceStatusVO("late");
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(temp);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }
    
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/{studentId}", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getAttendance(@PathVariable("seminarId") int seminarId,
                                   @PathVariable("classId") int classId,
                                   @PathVariable("studentId") int studentId,
                                   HttpServletRequest httpServletRequest) throws NumberFormatException, IllegalArgumentException, ClassesNotFoundException, UserNotFoundException {
    	try {
			List<Attendance> list= userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
			AttendanceStatusVO temp=null;
			for(Attendance attendance:list)
			{
				System.out.println(attendance);
				if(attendance.getStudent().getId().equals(BigInteger.valueOf(studentId))) {
					if(attendance.getAttendanceStatus()==0)
					{
						temp=new AttendanceStatusVO("ontime");
						break;
					}
					else
					{
						temp=new AttendanceStatusVO("late");
						break;
					}
				}
				temp=new AttendanceStatusVO("");
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(temp);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
    }
}
