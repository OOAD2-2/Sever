package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xmu.crms.dao.UserDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.UserDuplicatedException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.UserMapper;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.service.UserService;
import xmu.crms.util.MD5Utils;
import xmu.crms.view.vo.*;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MeController {

	@Autowired
	UserService userService;
	
    @Autowired
	UserDAO userDAO;
	
    @RequestMapping(value = "/me", method = GET)
    @ResponseBody
    public ResponseEntity getCurrentUser(@RequestParam(value = "id") String id) {
    	try{
			User user = userService.getUserByUserId(BigInteger.valueOf(Integer.valueOf(id)));
			System.out.println(user.toString());
			if(user != null){
				UserDetailVO userDetailVO = new UserDetailVO(user);
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userDetailVO);
			}else{
				return ResponseEntity.status(404).build();
			}
		}catch (UserNotFoundException e){
			System.out.println(e.getMessage());
			return ResponseEntity.status(400).build();
		}
    }

    @RequestMapping(value = "/me", method = PUT)
	public ResponseEntity updateCurrentUser(@RequestParam(value="id",required=false) String id,@RequestParam(value="typeId",required=false) Integer type,@RequestParam(value="openid") String openid,HttpServletRequest request) {
		try {
			if (null == userService.getUserByUserId(BigInteger.valueOf(Integer.valueOf(id)))) {
				
				BufferedReader br = request.getReader();
				String str, wholeStr = "";
				while((str = br.readLine()) != null){
					wholeStr += str;
				}
				Map<String, Object> object = new ObjectMapper().readValue(wholeStr, Map.class);
				User user = new User(object);
				System.out.println(user.toString());
				userDAO.insertUser(user);
				return ResponseEntity.status(204).contentType(MediaType.APPLICATION_JSON_UTF8).body(user.getId());
			}else{
				BufferedReader br = request.getReader();
				String str, wholeStr = "";
				while((str = br.readLine()) != null){
					wholeStr += str;
				}
				Map<String, Object> object = new ObjectMapper().readValue(wholeStr, Map.class);
				User user = new User(object);
				System.out.println(user.toString());
				userService.updateUserByUserId(BigInteger.valueOf(Integer.valueOf(id)), user);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}catch (IOException e){
			e.printStackTrace();
		}
		return ResponseEntity.status(204).build();
	}

    @RequestMapping(value = "/signin", method = GET)
    @ResponseBody
    public ResponseEntity signinWechat(@RequestParam(value="openid") String openid) {
    	try {
    	UserDetailsImpl userDetailsImpl=userDAO.getUserByOpenid(openid);
    	return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userDetailsImpl);
    	}
    	catch(UserNotFoundException e)
    	{
    		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(0);
    	}
    	
    }

    @RequestMapping(value = "/signin", method = POST)
    @ResponseBody
    public ResponseEntity signinPassword(HttpServletRequest httpServletRequest) throws IOException {
    	BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        @SuppressWarnings("unchecked")
		Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
        User user_temp=new User();
        user_temp.setPhone((String)o.get("phone"));
        user_temp.setPassword((String)o.get("password"));
        User user = userDAO.getUserByPhoneAndPassword(user_temp);	
        if(user==null)
    	{
    		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(0);
    	}
    	return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user.getId());
    }

    @SuppressWarnings("unused")
	@RequestMapping(value = "/register", method = POST)
    @ResponseBody
    public ResponseEntity registerPassword(HttpServletRequest httpServletRequest) throws IOException {
    	BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        @SuppressWarnings("unchecked")
		Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
        User user_temp=new User();
        user_temp.setPhone((String)o.get("phone"));
        user_temp.setPassword((String)o.get("password"));
        User user = userDAO.getUserByPhoneAndPassword(user_temp);	
        if(user==null)
    	{
        	userDAO.insertUserByPhoneAndPassword(user);
        	return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user.getId());
    	}
        else
        	return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(0);
    }
    
}
