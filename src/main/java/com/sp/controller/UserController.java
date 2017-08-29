package com.sp.controller;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.sp.CustomType;
import com.sp.SmtpMailSender;
import com.sp.model.GoogleLogin;
import com.sp.model.Login;
import com.sp.model.User;
import com.sp.model.UserPin;
import com.sp.repository.UserRepository;
import com.sp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")
	public ResponseEntity<?> addUser(@Validated @RequestBody User user) throws MessagingException {

		User userObj = userService.emailExists(user.getEmail());

		if (userObj == null) {

			try {
				final Context ctx = new Context();
				ctx.setVariable("subject", "Registration");
				ctx.setVariable("email", user.getEmail());
				ctx.setVariable("password", user.getPassword());
				userService.createUser(user);

				smtpMailSender.send("info@natit.us", user.getEmail(), "Registration", ctx, "email-simple");

			} catch (Exception e) {

				e.printStackTrace();
				return new ResponseEntity<>(new CustomType(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

			}
			
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new CustomType("Email Already Exist"), HttpStatus.CONFLICT);

		}
	}	
	

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")
	public ResponseEntity<?> addlogin(@Validated @RequestBody Login login) {

		
		User loginDetail = userService.logincheck(login.getEmail(), login.getPassword());
		
		
		if (loginDetail != null) {
			
			loginDetail.setDeviceType(login.getDeviceType());
			loginDetail.setDeviceToken(login.getDeviceToken());
			userRepository.save(loginDetail);
			JSONObject jsonInfo = new JSONObject();
			try {
				jsonInfo.put("userId",loginDetail.getUserId());
				jsonInfo.put("name",loginDetail.getName());
				jsonInfo.put("email",loginDetail.getEmail());
				jsonInfo.put("contactNo",loginDetail.getContactNo());
				jsonInfo.put("deviceToken",loginDetail.getDeviceToken());
				jsonInfo.put("deviceType",loginDetail.getDeviceType());
				jsonInfo.put("isActive",loginDetail.getIsActive());
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			String abc=jsonInfo.toString();
		
			return new ResponseEntity<>(abc, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>(new CustomType("User Not Found"), HttpStatus.NOT_FOUND);

		}

	}		
	
		
		@RequestMapping(value = "/googleLogin", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")
		public ResponseEntity<?> googlelogin(@RequestBody User user) throws MessagingException {
		    
			User userObj = userService.emailExists(user.getEmail());
			if(userObj==null){
				
		//user.setPassword(user.getGoogleId());
		 	userRepository.save(user);	
			}else
			
			if (user != null) {

				 user = userService.Googlecheck(user.getEmail(),user.getGoogleId());
			
				if(user!=null) {
			
				JSONObject jsonInfo = new JSONObject();
				try {
					jsonInfo.put("userId",user.getUserId());
					jsonInfo.put("isActive",user.getIsActive());
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				String abc=jsonInfo.toString();
				
				return new ResponseEntity<>(abc, HttpStatus.OK);
				
			}else {
				
				return new ResponseEntity<>(new CustomType("User Not Found"), HttpStatus.NOT_FOUND);
			}
				}
			
			JSONObject jsonInfo = new JSONObject();
			try {
				jsonInfo.put("userId",user.getUserId());
				jsonInfo.put("isActive",user.getIsDeleted());
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			String abc=jsonInfo.toString();
			
			return new ResponseEntity<>(abc, HttpStatus.OK);
			
			}
		 	
		
	/*@RequestMapping(value = "/googleLogin", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")
	public ResponseEntity<?> googlelogin(@RequestBody GoogleLogin glogin) {

		
		User userDetail = userService.Google(glogin.getEmail());
		
		if (userDetail != null) {

		if(userDetail.getName().equals(glogin.getName())) {
			
		if(userDetail.getGoogleId()==null)	{
			userDetail.setGoogleId(glogin.getGoogleId());
			userRepository.save(userDetail);
		}
			
			User user = userService.Googlecheck(glogin.getEmail(),glogin.getGoogleId());
		
			if(user!=null) {
		
			JSONObject jsonInfo = new JSONObject();
			try {
				jsonInfo.put("userId",user.getUserId());
				jsonInfo.put("isActive",user.getIsActive());
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			String abc=jsonInfo.toString();
			
			return new ResponseEntity<>(abc, HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<>(new CustomType("User Not Found"), HttpStatus.NOT_FOUND);
		}
			}else {
			
			return new ResponseEntity<>(new CustomType("User Not Found"), HttpStatus.NOT_FOUND);
		}
		}

		else {
			return new ResponseEntity<>(new CustomType("User Not Found"), HttpStatus.NOT_FOUND);

		}

	}	
	*/
	
	@RequestMapping(value = "/alluserDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public Iterable<User> getAllUsers() {

		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/setpin", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")	
	public ResponseEntity<?> setpin(@RequestBody UserPin userpin) throws MessagingException {
		
		User userDetails = userRepository.findOne(userpin.getUserId());
		
		if (userDetails != null) {

			userDetails.setPin(userpin.getPin());
			
			userRepository.save(userDetails);

			return new ResponseEntity<>(new CustomType("Successfully Save"), HttpStatus.OK);
		}
		
		
		else {
			return new ResponseEntity<>(new CustomType("Not Found"), HttpStatus.NOT_FOUND);

		}

	}	
	@RequestMapping(value = "/usercheck", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")	
	public ResponseEntity<?> usercheck(@RequestBody UserPin userpin) throws MessagingException {
		
		User userexist = userService.Usercheck(userpin.getUserId(), userpin.getPin());
		
		if (userexist != null) {

	    return new ResponseEntity<>(new CustomType("User Verified"), HttpStatus.OK);
		}
			
		else {
			return new ResponseEntity<>(new CustomType("Not Found"), HttpStatus.NOT_FOUND);

		}

	}		
	
		
}
