package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import xmu.crms.service.SchoolService;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.*;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MeController {

	/**
	 * @author: LUWEIW
	 */

	@Autowired
	UserService userService;
	@Autowired
	SchoolService schoolService;
	
    @Autowired
	UserDAO userDAO;

    @Autowired
	UserMapper userMapper;
	
    @RequestMapping(value = "/me", method = GET)
    @ResponseBody
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getCurrentUser() {
    	BigInteger id = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	try{
    		System.out.println(id);
			User user = userService.getUserByUserId(id);
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
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	public ResponseEntity updateCurrentUser(@RequestParam(value="typeId",required=false) Integer type,
											@RequestParam(value="phone",required=false) String phone,
											@RequestParam(value = "schoolId", required = false) Integer schoolId,
											@RequestParam(value = "password", required = false) String password,
											@RequestParam(required = false) String name,
											@RequestParam(required = false) String number) throws UserNotFoundException, NoSuchAlgorithmException {

    	BigInteger id = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (type == -1) {
			User user=userService.getUserByUserId(id);
			user.setOpenid("0");
			userService.updateUserByUserId(id, user);
		} else {
			School school = schoolService.getSchoolBySchoolId(BigInteger.valueOf(schoolId));
			password=md5Hex(password.substring(8,24));
			userService.updateUserByUserId(id,new User(phone,password, type, school, name, number));
		}
		return ResponseEntity.status(204).build();

	}

    @RequestMapping(value = "/signin", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
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
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity signinPassword(HttpServletRequest httpServletRequest) throws IOException {
    	BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        @SuppressWarnings("unchecked")
		Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
        User userTemp=new User();
        userTemp.setPhone((String)o.get("phone"));
        userTemp.setPassword((String)o.get("password"));
        User user = userDAO.getUserByPhoneAndPassword(userTemp);
        if(user==null)
    	{
    		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(0);
    	}
    	return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user.getId());
    }

    @SuppressWarnings("unused")
	@RequestMapping(value = "/register", method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity registerPassword(HttpServletRequest httpServletRequest) throws IOException {
		BufferedReader br = httpServletRequest.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
		User userTemp = new User();
		userTemp.setPhone((String) o.get("phone"));
		userTemp.setPassword((String) o.get("password"));
		User user = userDAO.getUserByPhoneAndPassword(userTemp);
		if (user == null) {
			userDAO.insertUserByPhoneAndPassword(user);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user.getId());
		} else {
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(0);
		}
	}
    
    private String md5Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] res = md5.digest(input.getBytes());
        return toHex(res);
    }

    private String toHex(byte[] bytes) {

        final char[] theHexDigits = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(theHexDigits[(bytes[i] >> 4) & 0x0f]);
            ret.append(theHexDigits[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
