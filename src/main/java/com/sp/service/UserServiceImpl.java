package com.sp.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.MD5;

import com.sp.model.User;

import com.sp.repository.UserRepository;


@Component("UserService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		User userRes = new User();
		try {

			String newPassword = MD5.ConvertToMD5(user.getPassword());
			user.setPassword(newPassword);
			userRes = userRepository.save(user);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return userRes;

	}


	
	public User findByMail(String email) {

		return userRepository.findByEmail(email);
	}


	public User findById(int userid) {
		return this.userRepository.findOne(userid);
	}

	

	

	public User logincheck(String email, String pwd) {
		User ob = new User();
		try {

			String newpwd = MD5.ConvertToMD5(pwd);

			ob = userRepository.findByEmailAndPassword(email, newpwd);
		}

		catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return ob;

	}
	
	
	public User Google(String email) {
		User ob = new User();
        ob = userRepository.findByEmail(email);
        return ob;

	}	
	
	public User Googlecheck(String email,String gId) {
		User ob = new User();
        ob = userRepository.findByEmailAndGoogleId(email, gId);
        return ob;

	}	
	

	public User Usercheck(int userId, String pin) {
		User ob = new User();
        ob = userRepository.findByUserIdAndPin(userId, pin);
        return ob;

	}	
	
	
	
	public User emailcheck(String email) {
		User ob = new User();
		try {

			ob = userRepository.findByEmail(email);

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}

		return ob;
	}

	
	@Override
	public User emailExists(String email) {

		return userRepository.findByEmail(email);
	}	
	
	
	
	@Override
	public void delete(int userId) {

		userRepository.delete(userId);
	}

	@Override
	public List<User> getallUser(int usertypeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
