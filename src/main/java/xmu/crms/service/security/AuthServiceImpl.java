package xmu.crms.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Service;
import xmu.crms.mapper.AuthMapper;
import xmu.crms.security.FifcosAuthenticationProvider;
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.dao.UserDAO;
import xmu.crms.entity.User;
import xmu.crms.exception.UserDuplicatedException;
import xmu.crms.security.UserDetailsServiceImpl;
import xmu.crms.service.UserService;
import xmu.crms.util.JwtTokenUtil;
import xmu.crms.util.Md5Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/22 8:49
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    FifcosAuthenticationProvider fifcosAuthenticationProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired(required = false)
    private AuthMapper authMapper;

    private String tokenHead = "Bearer ";

    private String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxba25ff360af27cad&secret=8fae9e5f3ecc06fd6e4bfaa22813fccc&grant_type=authorization_code";

    public static final String ERRCODE = "errcode";  

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getNumber();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(Md5Utils.useMd5Encode(rawPassword));
        try{
            User user = userDAO.signUpPhone(userToAdd);
            return user;
        }catch (UserDuplicatedException e){
            System.out.println(e.toString());
            return null;
        }
    }


    @Override
    public String login(String username, String password) {
        FifcosAuthenticationToken upToken = new FifcosAuthenticationToken(username, password);
        final Authentication authentication = fifcosAuthenticationProvider.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> claims = new HashMap<String, Object>(0);
        claims.put("type", ((FifcosAuthenticationToken)authentication).getType());
        final String token = jwtTokenUtil.doGenerateToken(claims, ((FifcosAuthenticationToken)authentication).getPhone(),null);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }

    @Override
    public Map<String, Object> weChatLogin(String code, Integer type) throws IOException, UserDuplicatedException{
        String reqUrl = url + "&js_code=" + code;
        StringBuffer json = new StringBuffer();
        try{
            URL oracle = new URL(reqUrl);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while((inputLine = in.readLine()) != null){
                json.append(new String(inputLine.getBytes(),"utf-8"));
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(json.toString());
        Map<String, Object> auth = new ObjectMapper().readValue(json.toString(), Map.class);
        if(auth.get(ERRCODE) != null){
            throw new IllegalArgumentException("参数错误");
        }
        User user = authMapper.getUserByOpenId((String)auth.get("openid"));
        // 用户还没有注册， 帮他注册一个只有openid的账号，小程序端跳到绑定页面，然后补全其他信息
        System.out.println((String)auth.get("openid"));
        User userNew = new User((String)auth.get("openid"), type);

        if(user == null){
        	if(userDAO==null)
        	{
        		System.out.println(1);
        	}
        	if(userNew==null)
        	{
        		System.out.println(2);
        	}
            userDAO.signUpPhone(userNew);
            auth.put("status", "unbind");
        }
        User userF = authMapper.getUserByOpenId((String)auth.get("openid"));
        BigInteger userId = userF.getId();
        auth.put("userId", userId);

        FifcosAuthenticationToken upToken = new FifcosAuthenticationToken((String)auth.get("openid"), type);
        System.out.println(type);
        final Authentication authentication = fifcosAuthenticationProvider.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> claims = new HashMap<String, Object>(0);
        claims.put("type", ((FifcosAuthenticationToken)authentication).getType());
        final String token = jwtTokenUtil.doGenerateToken(claims, ((FifcosAuthenticationToken)authentication).getOpenid(),"miniprogram");
        auth.put("jwt", token);

        System.out.println(auth.toString());
        return auth;
    }
}
