package com.sp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sp.model.UserInfo;
import com.sp.repository.UserInfoRepository;


@Service
public class UserInfoService {
	 @Autowired
	    private UserInfoRepository repo;

	    @Bean
	    public PasswordEncoder getPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    public void save(UserInfo user){
	        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
	        repo.save(user);
	    }
}
