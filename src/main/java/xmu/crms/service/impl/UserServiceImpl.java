package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.UserMapper;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService Impl
 * @author mads
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public BigInteger insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude, double latitude) {
        // 判断签到地点是否正确
        Location location = userMapper.getLocationById(classId, seminarId);
        System.out.println(location);
        System.out.println(location);
        System.out.println(location);
        System.out.println(location);
        System.out.println(location);
        // Location有三种状态， CALLING 1 表示正在签到 END 0 表示结束签到 BREAK 2 表示讨论课结束
        // Attendance有三种状态 PRESENT 0 正常签到 LATE 1 迟到 ABSENCE 2 缺勤
        if(location.getStatus().equals(Location.CALLING)){
            // 正常签到
        	return BigInteger.valueOf(userMapper.insertAttendanceById(classId, seminarId, userId, Attendance.PRESENT));
        }else if(location.getStatus().equals(Location.END)){
            // 迟到
            return BigInteger.valueOf(userMapper.insertAttendanceById(classId, seminarId, userId, Attendance.LATE));
        }else {
            // 缺勤
            return BigInteger.valueOf(userMapper.insertAttendanceById(classId, seminarId, userId, Attendance.ABSENCE));
        }
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId){
        List<Attendance> attendances = userMapper.listAttendanceById(seminarId, classId);
        return attendances;
    }

    
    

    @Override
    public User getUserByUserId(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userMapper.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        return fakeUser;
    }

    @Override
    public List<BigInteger> listUserIdByUserName(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {
        User fakeUser = userMapper.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户没找到");
        }
        userMapper.updateUserByUserId(userId, user);
    }

    @Override
    public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith) {
        try{
            List<User> users = userMapper.listUserByClassId(BigInteger.valueOf(1), numBeginWith, nameBeginWith);
            return users;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public List<User> listUserByUserName(String userName) throws UserNotFoundException {
        List<User> fakeUsers = userMapper.getUsersByName(userName);
        if(fakeUsers == null){
            throw new UserNotFoundException("没有叫"+userName+"的用户");
        }
        return fakeUsers;
    }

    @Override
    public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId) {
        List<User> users = userMapper.listPresentStudent(seminarId, classId);
        return users;
    }

    @Override
    public List<User> listAbsenceStudent(BigInteger seminarId, BigInteger classId){
        List<User> users = userMapper.listUnCallingStudent(seminarId, classId);
        return users;
    }

    @Override
    public List<Course> listCourseByTeacherName(String teacherName) throws UserNotFoundException, CourseNotFoundException{
        List<User> users = userMapper.getUsersByName(teacherName);
        if(users == null){
            throw new UserNotFoundException("没有叫"+teacherName+"的老师");
        }
        List<Course> courses = userMapper.listCourseByTeacherId(users.get(0).getId());
        if(courses == null){
            throw new CourseNotFoundException("老师还没有课程");
        }
        return courses;
    }

	@Override
	public User getUserByUserNumber(String userNumber) throws IllegalArgumentException, UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listLateStudent(BigInteger seminarId, BigInteger classId)
			throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException {
		// TODO Auto-generated method stub
        List<Attendance> attendanceList = userMapper.listAttendanceById(classId, seminarId);
        List<User> userList = new ArrayList<User>();
        for (Attendance attendance : attendanceList) {
            if (attendance.getAttendanceStatus() == 1) {
                userList.add(attendance.getStudent());
            }
        }
		return userList;
	}
}
