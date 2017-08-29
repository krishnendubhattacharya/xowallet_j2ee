package com.sp.service;

import java.util.List;


import com.sp.model.User;


public interface UserService {

	public Iterable<User> getAllUsers();
	
	public User createUser(User user);
	
	public User findByMail(String email);
	
	public User emailExists(String email);
	public User Usercheck(int userId, String pin);
	
	public User Google(String email); 
	public User Googlecheck(String email,String gId);
	
	public List<User> getallUser(int usertypeId);

	public void delete(int userId);
	
	public User emailcheck(String email);
	
	public User logincheck(String email, String pwd);

	
}