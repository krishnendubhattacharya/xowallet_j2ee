package com.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.model.UserInfo;



public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);
}
