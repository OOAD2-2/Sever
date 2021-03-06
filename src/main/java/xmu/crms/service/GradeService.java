package xmu.crms.service;

import java.math.BigInteger;
import java.util.List;

import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.GroupNotFoundException;

/**
 * @author Yexiaona
 * @version 2.20
 */
public interface GradeService {

    /**
     * for ali 按seminarGroupTopicId删除学生打分表.
     *
     * @param seminarGroupTopicId  小组话题表的Id
     * @throws IllegalArgumentException topicId格式错误时抛出
     * @author zhouzhongjun
     */
    void deleteStudentScoreGroupByTopicId(BigInteger seminarGroupTopicId) throws IllegalArgumentException;

    /**
     * for ali 获取学生某一堂讨论课的小组信息.
     * <p>获取某学生一堂讨论课的小组信息（包括成绩）<br>
     *
     * @param seminarGroupId 讨论课小组id
     * @return seminarGroup 讨论课小组信息（包括成绩）
     * @throws xmu.crms.exception.GroupNotFoundException 无此小组
     * @throws IllegalArgumentException  seminarGrouopId格式错误
     * @throws GroupNotFoundException
     * @author qinlingyun
     */
    SeminarGroup getSeminarGroupBySeminarGroupId(BigInteger seminarGroupId)
            throws GroupNotFoundException, IllegalArgumentException;

    
    /**
     * for ali 删除!.
     * semimarGroupService 中已存在该方法。
     * 获取某学生所有的讨论课小组. List<SeminarGroup> listSeminarGroupIdByStudentId(BigInteger userId)
     */
    /**
     * for ali 获取某学生所有讨论课的所有成绩
     * <p>获取某学生所有讨论课的详细信息（包括成绩）<br>
     * @param userId 用户id
     * @return list 学生历史讨论课小组列表（包含成绩）
     * @throws IllegalArgumentException userId格式错误
     * @author qinlingyun
     * @see SeminarGroupService#listSeminarGroupIdByStudent(BigInteger) 
     * @see GradeService#getSeminarGroupBySeminarGroupId(BigInteger)
     */
    //List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId) throws IllegalArgumentException;


    /**
     * for ali 按课程id获取学生该课程所有讨论课
     * <p>通过课程id获取该课程下学生所有讨论课详细信息（包括成绩）<br>
     *
     * @param userId 用户id
     * @param courseId 课程id
     * @return List 该课程下所有讨论课列表
     * @throws IllegalArgumentException courseId格式错误
     * @see SeminarService#listSeminarByCourseId(BigInteger)
     * @see SeminarGroupService#listSeminarGroupBySeminarId(BigInteger)
     * @see GradeService#listSeminarGradeByUserId(BigInteger) 
     */
    List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId,BigInteger courseId) throws IllegalArgumentException;

    /**
     * for ali 提交对其他小组的打分.
     *
     * @param userId    用户id
     * @param groupId   小组Id
     * @param grade     分数
     * @param topicId   话题id
     * @throws IllegalArgumentException topicId或userId或seminarId或groupId或grade格式错误
     * @author Huhui
     */
    void insertGroupGradeByUserId(BigInteger topicId, BigInteger userId, BigInteger groupId, BigInteger grade)
            throws IllegalArgumentException;

    /**
     * for ali 按ID设置小组报告分.
     *
     * @param seminarGroupId 讨论课组id
     * @param grade            分数
     * @throws GroupNotFoundException   未找到小组
     * @throws IllegalArgumentException seminar_group_id或grade格式错误
     * @author Huhui
     */
    void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade)
            throws GroupNotFoundException, IllegalArgumentException;

    /**
     * for ali 仅作为普通方法，被下面的定时器方法调用.
     * 讨论课结束后计算展示得分.
     * <p>条件: 讨论课已结束<br>
     *
     * @param seminarId      讨论课ID
     * @throws IllegalArgumentException seminarId或seminarGroupId格式错误
     * @author qinlingyun
     */
    void countPresentationGrade(BigInteger seminarId) throws IllegalArgumentException;


    /**
     * for ali 定时器方法.
     * 讨论课结束后本次讨论课最终得分.
     * <p>条件: 讨论课已结束，先计算展示得分<br>
     *
     * @param seminarId      讨论课ID
     * @see GradeService #countPresentationGrade(BigInteger seminarId)
     * @throws IllegalArgumentException seminarId或seminarGroupId格式错误
     * @author qinlingyun
     */
    void countGroupGradeBySeminarId(BigInteger seminarId) throws IllegalArgumentException;


}
