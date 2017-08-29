package com.sp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	
	User findByEmail(String useremail);
		
	User findByEmailAndPassword(String email,String pwd);
	
    User findByUserIdAndPin(int userId,String pin);

    User findByEmailAndGoogleId(String email,String gId);

}

