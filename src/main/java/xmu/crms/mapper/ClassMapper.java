package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.CourseSelection;
import xmu.crms.entity.Location;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.User;

/**
 * @author lhl
 */
@Mapper
@Component
public interface ClassMapper {
    /**
     * for ali for ali
     * @param classId
     * @return
     */
	public int deleteClassSelectionByClassId(BigInteger classId);

    /**
     * for ali for ali
     * @param courseName
     * @return
     */
	public List<BigInteger> getCourseIdByName(@Param("courseName")String courseName);

    /**
     * for ali for ali
     * @param teacherName
     * @return
     */
	public List<BigInteger> getUserIdByName(@Param("teacherName")String teacherName);

    /**
     * for ali
     * @param courseName
     * @param teacherName
     * @return
     */
    public List<ClassInfo> listClassByName(@Param("courseName")String courseName, @Param("teacherName")String teacherName);

    /**
     * for ali
     * @param courseId
     * @return
     */
    public Course selectCourseByCourseId(BigInteger courseId);

    /**
     * for ali
     * @param courseId
     * @return
     */
    public List<ClassInfo> listClassByCourseId(@Param("courseId")BigInteger courseId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public ClassInfo getClassByClassId(@Param("classId")BigInteger classId);

    /**
     * for ali
     * @param classId
     * @param newClass
     * @return
     */
    public int updateClassByClassId(@Param("classId")BigInteger classId, @Param("classInfo")ClassInfo newClass);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteLocationByClassId(BigInteger classId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public List<BigInteger> listFixGroupIdByClassId(BigInteger classId);

    /**
     * for ali
     * @param fixGroupIds
     * @return
     */
    public int deleteFixGroupMemberByFixGroupId(@Param("fixGroupIds")List<BigInteger> fixGroupIds);

    /**
     * for ali
     * @param fixGroupIds
     * @return
     */
    public int deleteFixGroupTopicByFixGroupId(@Param("fixGroupIds")List<BigInteger> fixGroupIds);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteFixGroupByClassId(BigInteger classId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public List<BigInteger> listSeminarGroupIdByClassId(BigInteger classId);

    /**
     * for ali
     * @param seminarGroupIds
     * @return
     */
    public int deleteSeminarGroupMemberBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);

    /**
     * for ali
     * @param seminarGroupIds
     * @return
     */
    public List<BigInteger> listSeminarGroupTopicIdBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);

    /**
     * for ali
     * @param seminarGroupTopicIds
     * @return
     */
    public int deleteStudentScoreGroupBySeminarGroupTopicId(@Param("seminarGroupTopicIds")List<BigInteger> seminarGroupTopicIds);

    /**
     * for ali
     * @param seminarGroupIds
     * @return
     */
    public int deleteSeminarGroupTopicBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteSeminarGroupByClassId(BigInteger classId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteAttendanceByClassId(BigInteger classId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteClassByClassId(@Param("classId")BigInteger classId);

    /**
     * for ali
     * @param userId
     * @return
     */
    public User selectUserByUserId(BigInteger userId);

    /**
     * for ali
     * @param courseSelection
     * @return
     */
    public int insertCourseSelection(CourseSelection courseSelection);

    /**
     * for ali
     * @param userId
     * @param classId
     * @return
     */
    public int insertCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);

    /**
     * for ali
     * @param userId
     * @param classId
     * @return
     */
    public int deleteCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);

    /**
     * for ali
     * @param seminarId
     * @return
     */
    public Seminar selectSeminarBySeminarId(BigInteger seminarId);

    /**
     * for ali
     * @param classId
     * @param seminarId
     * @return
     */
    public Location getCallStatusById(@Param("classId")BigInteger classId, @Param("seminarId")BigInteger seminarId);

    /**
     * for ali
     * @param courseId
     * @param classInfo
     * @return
     */
    public int insertClassById(@Param("courseId")BigInteger courseId,@Param("classInfo")ClassInfo classInfo);

    /**
     * for ali
     * @param courseId
     * @return
     */
    public int deleteClassByCourseId(@Param("courseId")BigInteger courseId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public int deleteScoreRuleById(@Param("classId")BigInteger classId);

    /**
     * for ali
     * @param classId
     * @return
     */
    public ClassInfo getScoreRule(@Param("classId")BigInteger classId);

    /**
     * for ali
     * @param classId
     * @param proportions
     * @return
     */
    public int insertScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);

    /**
     * for ali
     * @param classId
     * @param proportions
     * @return
     */
    public int updateScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);

    /**
     * for ali
     * @param location
     * @return
     */
    public int insertCallInRollById(@Param("location")Location location);

    /**
     * for ali
     * @param seminarId
     * @param classId
     * @return
     */
    public int startCallRollById(@Param("seminarId")BigInteger seminarId, @Param("classId")BigInteger classId);

    /**
     * for ali
     * @param seminarId
     * @param classId
     * @return
     */
    public int endCallRollById(@Param("seminarId")BigInteger seminarId, @Param("classId")BigInteger classId);

    /**
     * for ali
     * @param userId
     * @return
     */
    public List<BigInteger> listClassByUserId(BigInteger userId);

    /**
     * for ali
     * @param seminarId
     * @param classId
     * @param userId
     * @return
     */
    int getAttendanceById(@Param("seminarId") BigInteger seminarId,
                          @Param("classId") BigInteger classId,
                          @Param("userId") BigInteger userId);
}
