package xmu.crms.dao;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xmu.crms.entity.User;
import xmu.crms.exception.UserDuplicatedException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.UserMapper;
import xmu.crms.security.UserDetailsImpl;

@Component
public class UserDAO {

	@Autowired
	UserMapper userMapper;

    public User getUserByUserId(BigInteger userId){
        return userMapper.getUserByUserId(userId);
    }

    /**
     * 微信登录后用户绑定（创建用户）.
     * <p>User中只有phone和password，userId是注册后才有并且在数据库自增<br>
     * @param user 用户信息
     * @author lhl
     * @return 
     */
    public BigInteger insertUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    /**
     * 登陆用户名和密码检验
     * <p>手机号登录 (.Net使用),User中只有phone和password，用于判断用户名密码是否正确<br>
     * @param user 用户信息(手机号Phone和密码Password)
     * @return user 该用户信息
     * @author lhl
     */
    public User getUserByPhoneAndPassword(User user) {
        User user1 = userMapper.getUserByUserId(userMapper.getUserByNumber(user.getPhone()).getId());
        if(user1.getPassword().equals(user.getPassword()))
        {
        	return user1;}
        else
        {
        	return null;}
    }


    /**
     * 手机号注册（创建用户）.
     * <p>手机号注册 (.Net使用),User中只有phone和password，userId是注册后才有并且在数据库自增<br>
     * @author lhl
     * @param user 用户信息(手机号Phone和密码Password)
     */
    public void insertUserByPhoneAndPassword(User user) {
        userMapper.insertUser(user);
    }


    /**
     * 获得用户信息.
     * @author lhl
     * @param user 用户信息(手机号Phone和密码Password)
     */
    public User getUser(User user) {
    	 User user1 = userMapper.getUserByUserId(userMapper.getUserByNumber(user.getPhone()).getId());
         if(user1.getPassword().equals(user.getPassword())) {
         	return user1;}
         else {
         	return null;}
    }

    /**
     * 用户解绑(删除用户).
     * @author lhl
     * @param userId 用户id
     */
    public void deleteUser(BigInteger userId)
    {
    	User user=userMapper.getUserByUserId(userId);
    	user.setNumber(null);
        userMapper.updateUserByUserId(userId, user);
    }

    /**
     * 通过用户名获取用户id
     * @author LUWEIW
     * @param UserName
     * @return BigInteger
     */
    public BigInteger getUserIdByUserName(String UserName) {
        return userMapper.getUsersByName(UserName).get(0).getId();
    }

    public User signUpPhone(User user) throws UserDuplicatedException {
        if(user.getPhone() != null){
            UserDetailsImpl fakeUser = userMapper.getUserByPhone(user.getPhone());
            if(fakeUser != null){
                throw new UserDuplicatedException("学号已存在");
            }
        }
        userMapper.insertUser(user);
        return userMapper.getUserByUserId(user.getId());
    }
    
    public void deleteTeacherAccount(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userMapper.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        userMapper.unbindUserById(userId);
    }

    public void deleteStudentAccount(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userMapper.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        userMapper.unbindUserById(userId);
    }
    
    public UserDetailsImpl getUserByOpenid(String openid) throws UserNotFoundException {
    	UserDetailsImpl fakeUser = userMapper.getUserByOpenId(openid);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        return fakeUser;
    }
}
