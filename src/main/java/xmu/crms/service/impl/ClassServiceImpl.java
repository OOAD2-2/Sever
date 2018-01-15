package xmu.crms.service.impl;

import java.math.BigInteger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import xmu.crms.dao.ClassDao;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.ClassMapper;
import xmu.crms.service.*;
import org.springframework.web.bind.annotation.*;

import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import java.math.BigInteger;
/** 
 * 
 * @author lsqlh
 *
 */
@Service
@Component
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassDao classDao;
	
	@Autowired
	SeminarGroupServiceImpl seminarGroupService;
	@Autowired
	FixGroupService fixGroupService;
	@Autowired
	SeminarService seminarService;
	@Override
	public void deleteClassSelectionByClassId(BigInteger classId){
		 classDao.deleteClassSelectionByClassId(classId);
	}


	@Override
	public ClassInfo getClassByClassId(BigInteger classId) throws ClassesNotFoundException {
		return classDao.getClassByClassId(classId);
	}

	@Override
	public void updateClassByClassId(BigInteger classId, ClassInfo newClass) {
		 classDao.updateClassByClassId(classId, newClass);
	}

	@Override
	public void deleteClassByClassId(BigInteger classId) {
		classDao.deleteClassByClassId(classId);
	}

	@Override
	public BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException {
		return classDao.insertCourseSelectionById(userId, classId);
	}

	@Override
	public void deleteCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException, IllegalArgumentException, ClassesNotFoundException{
		FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
		try{
			fixGroupService.deleteFixGroupUserById(fixGroup.getId(), userId);
		}catch (FixGroupNotFoundException e){
			e.printStackTrace();
		}
		classDao.deleteCourseSelectionById(userId, classId);
	}

	@Override
	public Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException {
		return classDao.getCallStatusById(classId, seminarId);
	}

	@Override
	public BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws CourseNotFoundException {
		return classDao.insertClassById(courseId, classInfo);
	}

	@Override
	public void deleteClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		classDao.deleteClassByCourseId(courseId);
	}



	@Override
	public BigInteger callInRollById(Location location) throws SeminarNotFoundException, ClassesNotFoundException {
		 return classDao.callInRollById(location);


	}
	@Override
	public void endCallRollById(BigInteger seminarId, BigInteger classId) throws SeminarNotFoundException, ClassesNotFoundException {
		classDao.endCallRollById(seminarId, classId);
		Seminar seminar = seminarService.getSeminarBySeminarId(seminarId);
		if (seminar.getFixed() == false) {
			seminarGroupService.automaticallyGrouping(seminarId, classId);
		}
	}

	@Override
	public List<ClassInfo> listClassByUserId(BigInteger userId)
			throws IllegalArgumentException, ClassesNotFoundException {
		return classDao.listClassByUserId(userId);
	}


	@Override
	public List<ClassInfo> listClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		return classDao.listClassByCourseId(courseId);
	}

}
