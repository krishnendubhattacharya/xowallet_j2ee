package com.sp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sp.config.CustomUserDetails;
import com.sp.model.Role;
import com.sp.model.UserInfo;
import com.sp.repository.UserInfoRepository;
import com.sp.service.UserInfoService;

@SpringBootApplication
public class XoApplication {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;	
	

	public static void main(String[] args) {
		SpringApplication.run(XoApplication.class, args);
	}
	
	@Bean(name = "messageSource")
	 public ReloadableResourceBundleMessageSource messageSource() {
  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
     messageBundle.setBasename("classpath:messages/messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	  }		
	
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserInfoRepository repository, UserInfoService service) throws Exception {
		//Setup a default user if db is empty
		if (repository.count()==0)
			service.save(new UserInfo("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}


	private UserDetailsService userDetailsService(final UserInfoRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
	
	
	
	
	
}
