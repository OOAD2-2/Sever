package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.security.UserDetailsImpl;

import java.math.BigInteger;
import java.util.List;

/**
 * User的mapper
 * @author mads123123
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * for ali 根据用户名查找用户
     *
     * @param id
     * @return
     */
    User getUserByUserId(BigInteger id);

    /**
     * for ali 更新用户
     *
     * @param id
     * @param user
     */
    void updateUserByUserId(@Param("id") BigInteger id, @Param("user") User user);

    /**
     * for ali 新增用户
     * @param user
     * @return int
     */
    int insertUser(User user);

    /**
     * for ali 根据id解绑用户，就是置手机号为空
     * @param id
     */
    void unbindUserById(BigInteger id);

    /**
     * for ali 根据老师id查找老师的课程
     * @param id
     * @return
     */
    List<Course> listCourseByTeacherId(BigInteger id);

    /**
     * for ali 根据学生id查找学生选的课程
     * @param id
     * @return
     */
    List<Course> getCoursesByStudentId(BigInteger id);

    /**
     * for ali 根据学校名字获得学校
     * @param name
     * @return
     */
    School getSchoolByName(String name);

    /**
     * for ali 根据学校id获取学校
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);

    /**
     * for ali 根据学号拿学生
     * @param number
     * @return
     */
    User getUserByNumber(String number);

    /**
     * for ali 根据姓名拿老师，老师可能重名
     * @param name
     * @return
     */
    List<User> getUsersByName(String name);

    /**
     * for ali 返回用户列表
     * @param classId
     * @param numberBeginWith
     * @param nameBeginWith
     * @return List<User>
     */
    List<User> listUserByClassId(@Param("classId") BigInteger classId,
                                 @Param("numberBeginWith") String numberBeginWith,
                                 @Param("nameBeginWith") String nameBeginWith);

    /**
     * for ali 小程序： 学生签到
     * @param classId
     * @param seminarId
     * @param userId
     * @param status
     * @return int
     */
    int insertAttendanceById(@Param("classId") BigInteger classId,
                              @Param("seminarId") BigInteger seminarId,
                              @Param("userId") BigInteger userId,
                              @Param("status") int status);

    /**
     * for ali 根据classId和seminarId获取位置信息
     * @param classId
     * @param seminarId
     * @return
     */
    Location getLocationById(@Param("classId")BigInteger classId,
                             @Param("seminarId")BigInteger seminarId);

    /**
     * for ali 查找某一节讨论课迟到的学生列表
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listAbsenceStudentById(@Param("seminarId") BigInteger seminarId,
                                      @Param("classId") BigInteger classId);

    /**
     * for ali 查找某一节讨论课的签到名单
     * @param seminarId
     * @param classId
     * @return
     */
    List<Attendance> listAttendanceById(@Param("seminarId") BigInteger seminarId,
                                        @Param("classId") BigInteger classId);

    /**
     * for ali 查找正常签到的学生
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listPresentStudent(@Param("seminarId") BigInteger seminarId,
                                  @Param("classId") BigInteger classId);

    /**
     * for ali 查找正在上课的时候还没签到的同学
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listUnCallingStudent(@Param("seminarId")BigInteger seminarId,
                                    @Param("classId") BigInteger classId);

    /**
     * for ali 根据手机号登陆用
     * @param phone
     * @return
     */
    UserDetailsImpl getUserByPhone(@Param("phone") String phone);

    /**
     * for ali 根据openid登录用
     * @param openid
     * @return
     */
    UserDetailsImpl getUserByOpenId(@Param("openid") String openid);

    /**
     * for ali 删除用户
     * @param openid
     */
    void deleteUserByOpenId(@Param("openid") String openid);
}