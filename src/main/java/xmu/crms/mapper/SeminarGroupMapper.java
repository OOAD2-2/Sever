package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zw
 * @date 2017/12/20 0020
 * @time 14:19
 * @email 493703217@qq.com
 */
@Mapper
@Component
public interface SeminarGroupMapper {

    /**
     * for ali 删除某小组所有成员
     * @param seminarGroupId
     * @return boolean
     * @author zhouwei
     * @date 18:00 2017/12/21 0021
     */

    /**
     * for ali 删除某小组所有成员
     * @param userId
     * @param groupId
     */
    void insertSeminarGroupMemberById(@Param("userId") BigInteger userId, @Param("groupId") BigInteger groupId);
    /**
     * for ali  根据小组的id找到小组的组长
     * @param groupId
     * @return BigInteger
     * @author zhouwei
     * @date 21:08 2017/12/21 0021
     */

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @param seminarGroupMember
     * @return
     */
    int insertSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId, @Param("seminarGroupMember") SeminarGroupMember seminarGroupMember);

    /**
     * for ali 删除某小组所有成员
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    List<SeminarGroupTopic> listGroupByTopicId(@Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    String insertTopicByGroupId(@Param("groupId") BigInteger groupId, @Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @param userId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    boolean resignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @return
     * @throws GroupNotFoundException
     */
    boolean deleteTopicByGroupId(@Param("groupId") BigInteger groupId) throws GroupNotFoundException;
/*完成的*/

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @param userId
     */
    void assignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarId
     * @param classId
     */
    void automaticallyGrouping(@Param("seminarId") BigInteger seminarId, @Param("classId") BigInteger classId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarGroupId
     */
    void deleteSeminarGroupByGroupId(@Param("seminarGroupId") BigInteger seminarGroupId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarId
     */
    void deleteSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarGroupId
     */
    void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId);

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @return
     */
    SeminarGroup getSeminarGroupByGroupId(@Param("groupId") BigInteger groupId);

    /**
     * for ali 删除某小组所有成员
     * @param userId
     * @return
     */
    List<BigInteger> getSeminarGroupIdBySeminarIdAndUserId(@Param("userId") BigInteger userId);

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupLeaderByGroupId(@Param("groupId") BigInteger groupId);

    /**
     * for ali 删除某小组所有成员
     * @param groupId
     * @return
     */
    List<SeminarGroupMember> listSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarGroup
     * @return
     */
    int insertSeminarGroupBySeminarId(SeminarGroup seminarGroup);

    /**
     * for ali 删除某小组所有成员
     * @param seminarId
     * @return
     */
    List<SeminarGroup> listSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * for ali 删除某小组所有成员
     * @param userId
     * @return
     */
    BigInteger getUserIdByUserId(@Param("userId") BigInteger userId);

    /**
     * for ali 删除某小组所有成员
     * @param grouId
     * @param userId
     */
    void deleteSeminarGroupMemberByuId(@Param("groupId")BigInteger grouId, @Param("userId")BigInteger userId);

    /**
     * for ali 删除某小组所有成员
     * @param seminarId
     * @return
     */
    BigInteger getSeminarIdBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * for ali 删除某小组所有成员
     * @param SeminarId
     * @param ClassId
     * @return
     */
    List<BigInteger> listStudentIdBySeminarIdAndClassId(@Param("seminarId")BigInteger SeminarId,@Param("classId")BigInteger ClassId);
    /**
     * for ali获取某学生所有的讨论课小组.
     *
     * @param studentId 学生id
     * @return
     * @author: YellowPure
     * @date: 21:34 2017/12/22
     */
    List<SeminarGroupMember> listSeminarGroupIdByStudentId(BigInteger studentId);

    /**
     * for ali 删除某小组所有成员
     * @param topicId
     * @return
     */
    List<BigInteger> listGroupIdByTopicId(BigInteger topicId);
    //throw

    /**
     * for ali 删除某小组所有成员
     * @param id
     * @return
     */
    ClassInfo getSeminarById(BigInteger id);

    /**
     * for ali 删除某小组所有成员
     * @param id
     * @return
     */
    ClassInfo getClassInfoById(BigInteger id);

    /**
     * for ali 删除某小组所有成员
     * @param id
     * @return
     */
    Course getCourseById(BigInteger id);

    /**
     * for ali 删除某小组所有成员
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);

    /**
     * for ali 删除某小组所有成员
     * @param id
     * @return
     */
    Topic getTopicById(BigInteger id);
    /**
     * for ali根据id获取seminarGroup
     *
     * @param id 小组id
     * @return
     * @author: YellowPure
     * @date: 20:57 2017/12/22
     */
    SeminarGroup getSeminarGroupById(BigInteger id);

    /**
     * for ali根据id获取user
     *
     * @param id 用户id
     * @return
     * @author: YellowPure
     * @date: 21:00 2017/12/22
     */
    User getUserById(BigInteger id);

    /**
     * for ali查询该学生是否已经在小组里
     *
     * @param studentId 学生id
     * @param seminarGroupId 讨论组id
     * @return
     * @author: YellowPure
     * @date: 21:02 2017/12/22
     */
    SeminarGroupMember getSeminarGroupMemberByStudentIdAndSeminarGroupId(@Param("studentId") BigInteger studentId, @Param("seminarGroupId") BigInteger seminarGroupId);

    /**
     * for ali小组按id选择话题.
     *
     * @param topicId 话题id
     * @param seminarGroupId 讨论组id
     * @return
     * @author: YellowPure
     * @date: 22:10 2017/12/22
     */
    void insertSeminarGroupTopicByTopicIdAndSeminarGroupId(@Param("topicId") BigInteger topicId, @Param("seminarGroupId") BigInteger seminarGroupId);



}