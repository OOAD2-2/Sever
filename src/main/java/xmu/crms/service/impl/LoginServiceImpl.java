package xmu.crms.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.LoginService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author mads
 */
@Service
@Component
public class LoginServiceImpl implements LoginService {

    @Override
    public User signInPhone(User user) throws UserNotFoundException {
        return null;
    }

	@Override
	public Map<String, Object> signInWeChat(BigInteger userId, String code, String state, String successUrl)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signUpWeChat(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User signUpPhone(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTeacherAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudentAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
