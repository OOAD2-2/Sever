package xmu.crms.service.security;

import xmu.crms.entity.User;
import xmu.crms.exception.UserDuplicatedException;

import java.io.IOException;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/22 8:48
 * @group 1-1
 */
public interface AuthService {
    /**
     * for ali web端注册用户
     * @param userToAdd
     * @return
     */
    User register(User userToAdd);

    /**
     * for ali Web端登陆
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * for ali 刷新jwt ， 暂时用不上， jwt过期时间为一周
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);

    /**
     * for ali 微信端小程序登录
     * @param code
     * @param type
     * @return
     * @throws IOException
     * @throws UserDuplicatedException
     */
    Map<String, Object> weChatLogin(String code, Integer type) throws IOException, UserDuplicatedException;


}
