package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author YellowPure
 * @date 2017/12/19
 */
@Mapper
@Component
public interface SeminarMapper {
    /**
     * for ali根据courseId获取seminar列表
     *
     * @param courseId 课程Id
     * @return List<Seminar> 讨论课列表
     * @author YellowPure
     * @date 20:20 2017/12/19
     */
    List<Seminar> listSeminarByCourseId(BigInteger courseId);

    /**
     * for ali根据讨论课id获取讨论课
     *
     * @param seminarId 讨论课Id
     * @return Seminar
     * @author YellowPure
     * @date 14:11 2017/12/20
     */
    Seminar getSeminarBySeminarId(BigInteger seminarId);

    /**
     * for ali根据讨论课id删除讨论课
     *
     * @param seminarId 讨论课Id
     * @author YellowPure
     * @date 14:23 2017/12/20
     */
    void deleteSeminarByCourseId(BigInteger seminarId);

    /**
     * for ali根据讨论课id删除话题
     *
     * @param seminarId 讨论课id
     * @author YellowPure
     * @date 14:32 2017/12/20
     */
    void deleteTopicBySeminarId(BigInteger seminarId);

    /**
     * for ali根据讨论课id删除讨论课小组
     *
     * @param seminarId 讨论课Id
     * @author YellowPure
     * @date 14:33 2017/12/20
     */
    void deleteSeminarGroupBySeminarId(BigInteger seminarId);

    /**
     * for ali按讨论课id修改讨论课.
     *
     * @param seminar 讨论课信息
     * @author YellowPure
     * @date 14:50 2017/12/20
     */
    void updateSeminarBySeminarId(Seminar seminar);

    /**
     * for ali根据user_id查询小组
     *
     * @param studentId 学生id
     * @author YellowPure
     * @date 22:03 2017/12/20
     * @return SeminarGroupMember
     */
    SeminarGroupMember getSeminarGroupMemberByStudentId(BigInteger studentId);
    
    /**
     * for ali根据seminar_group_id获得seminar_group
     *
     * @param id
     * @return SeminarGroup
     * @author YellowPure
     * @date 22:08 2017/12/20
     */
    SeminarGroup getSeminarGroupById(BigInteger id);

    /**
     * for ali根据courseId获得course
     *
     * @param id
     * @return Course
     * @author YellowPure
     * @date 22:16 2017/12/20
     */
    Course getCourseById(BigInteger id);

    /**
     * for ali根据id获得teacher
     *
     * @param id
     * @author YellowPure
     * @date 22:18 2017/12/20
     * @return User
     */
    User getUserById(BigInteger id);

    /**
     * for ali新增讨论课.
     *
     * @param seminar 讨论课信息
     * @author YellowPure
     * @date 22:23 2017/12/20
     * @return int
     */
    int insertSeminarByCourseId(Seminar seminar);

    /**
     * for ali根据ID删除讨论课
     *
     * @param id 讨论课ID
     * @author YellowPure
     * @date 14:08 2017/12/21
     */
    void deleteSeminarById(BigInteger id);

    /**
     * for ali根据Id查询学校
     *
     * @param id 学校Id
     * @return School
     * @author YellowPure
     * @date 15:56 2017/12/21
     */
    School getSchoolById(BigInteger id);
}
