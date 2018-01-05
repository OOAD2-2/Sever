package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Course;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.mapper.CourseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ZengNan
 * @Description:
 * @Data: 2017/12/24 1:33
 */
@Component
public class CourseDAO {
    @Autowired
    CourseMapper courseMapper;

	public List<Course> listCourseByUserId(BigInteger userId)
			throws IllegalArgumentException, CourseNotFoundException {
		// TODO Auto-generated method stub
		if(!(userId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
		List<Course> courseList = courseMapper.listCourseByUserId(userId);
		if(courseList == null) {
            throw new CourseNotFoundException("没有找到课程！");
        }
		return courseList;
	}

    public BigInteger insertCourseByUserId(BigInteger userId, Course course)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        if(!(userId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        course.getTeacher().setId(userId);
        courseMapper.insertCourseByUserId(course);
        return BigInteger.valueOf(course.getId().intValue());
    }

    public Course getCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException, CourseNotFoundException {
        // TODO Auto-generated method stub
        if(!(courseId.intValue() > 0)) {
            throw new IllegalArgumentException("课程ID格式错误！");
        }
        Course course = courseMapper.getCourseByCourseId(courseId);
        if(course == null) {
            throw new CourseNotFoundException("没有找到课程！");
        }
        return course;
    }

    public int updateCourseByCourseId(BigInteger courseId, Course course)
    {
        // TODO Auto-generated method stub
        course.setId(courseId);
        int matchUpdateLines = courseMapper.updateCourseByCourseId(course);
        return matchUpdateLines;
    }

    public int deleteCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        if(!(courseId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        int matchDeleteLines = courseMapper.deleteCourseByCourseId(courseId);
		/*if(matchDeleteLines == 0)
			throw new CourseNotFoundException("没有找到课程！");*/
        return matchDeleteLines;
    }

    public List<Course> listCourseByCourseName(String courseName) {
        // TODO Auto-generated method stub
        List<Course> courseList = courseMapper.listCourseByCourseName(courseName);
        return courseList;
    }

}
