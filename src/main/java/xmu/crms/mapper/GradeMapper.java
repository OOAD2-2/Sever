package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.view.vo.SeminarGradeDetail;

import java.math.BigInteger;
import java.util.List;

/**
 * Grade Mapper
 * @author wang
 */
@Mapper
@Component
public interface GradeMapper {

    /**
     * for ali 删除学生打分表
     * @param topicId
     */
    void deleteStudentScoreGroupByTopicId(BigInteger topicId);

    /**
     * for ali 查看组的讨论课成绩
     * @param seminarGroupId
     * @return
     */
    SeminarGroup getSeminarGradeBySeminarGroupId(BigInteger seminarGroupId);


    /**
     * for ali 获取FixGroup
     * @param fixGroupId
     * @return
     */
    FixGroup getFixGroupByFixGroupId(BigInteger fixGroupId);


    /**
     * for ali 获取seminarGroupTopicId
     * @param topicId
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupTopicId(@Param("topicId")BigInteger topicId,
                                      @Param("groupId")BigInteger groupId);
    /**
     * for ali 插入学生打分表
     * @param userId
     * @param seminarGroupTopicId
     * @param grade
     */
    void insertGroupGradeByUserId(@Param("userId")BigInteger userId,
                                  @Param("seminarGroupTopicId")BigInteger seminarGroupTopicId,
                                  @Param("grade")BigInteger grade);


    /**
     * for ali 更新设置小组报告分
     * @param seminarGroupId
     * @param grade
     */
    void updateGroupByGroupId(@Param("seminarGroupId")BigInteger seminarGroupId,
                              @Param("grade")BigInteger grade);

    /**
     * for ali 获取SeminarGroupId
     * @param userId
     * @return
     */
    List<BigInteger> getSeminarGroupIdByStudentId(BigInteger userId);

    /**
     * for ali 获取学生所有讨论课成绩
     * @param userId
     * @return
     */
    List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId);


    /**
     * for ali 获取所有讨论课成绩
     * @param userId
     * @param courseId
     * @return List<SeminarGroup>
     */
    List<SeminarGroup> listSeminarGradeByCourseId(@Param("userId") BigInteger userId,
                                                  @Param("courseId") BigInteger courseId);

    /**
     * for ali 获取课程的所有讨论课
     * @param courseId
     * @return
     */
    List<Seminar> listSeminarByCourseId(BigInteger courseId);

    /**
     * for ali 获取学生对该组的打分
     * @param seminarGroupId
     * @return
     */
    List<BigInteger> listGrade(BigInteger seminarGroupId);

    /**
     * for ali 更新展示得分
     * @param seminarGroupId
     * @param grade
     */
    void updatePresentationGradeByGroupId(@Param("seminarGroupId") BigInteger seminarGroupId,
                                          @Param("grade") BigInteger grade);

    /**
     * for ali 获取ClassId
     * @param seminarGroupId
     * @param seminarId
     * @return
     */
    BigInteger getClassId(@Param("seminarGroupId") BigInteger seminarGroupId,
                             @Param("seminarId") BigInteger seminarId);

    /**
     * for ali 获取报告分百分比
     * @param classId
     * @return
     */
    BigInteger getReportPresentationPercentage(BigInteger classId);

    /**
     * for ali 获取报告分
     * @param seminarGroupId
     * @return
     */
    BigInteger getReportGrade(BigInteger seminarGroupId);

    /**
     * for ali 获取展示分
     * @param seminarGroupId
     * @return
     */
    BigInteger getPresentationGrade(BigInteger seminarGroupId);

    /**
     * for ali 更新最终成绩
     * @param seminarGroupId
     * @param grade
     */
    void updateFinalGrade(@Param("seminarGroupId") BigInteger seminarGroupId,
                          @Param("grade") BigInteger grade);

    /**
     * for ali 获取用户
     * @param id
     * @return
     */
    User getUserByUserId(BigInteger id);

    /**
     * for ali 获取学校
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);


    /**
     * for ali 获取班级信息
     * @param id
     * @return
     */
    ClassInfo getClassInfoByClassId(BigInteger id);

    /**
     * for ali 获取Seminar
     * @param id
     * @return
     */
    Seminar getSeminarBySeminarId(BigInteger id);

    /**
     * for ali 获取学生姓名
     * @param leaderId
     * @return
     */
    String getLeaderNameByLeaderId(BigInteger leaderId);

    /**
     * for ali 获取讨论课名称
     * @param seminarId
     * @return
     */
    String getSeminarNameBySeminarId(BigInteger seminarId);           //按courseId获取所有讨论课成绩

}
