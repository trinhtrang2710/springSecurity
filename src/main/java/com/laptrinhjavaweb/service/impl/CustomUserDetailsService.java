package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;

@Service
//implements UserDetailService(framework)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		
		if (userEntity == null) {
			//when login fail will call authentication-failure : security.xml -> redirect to login page
			throw new UsernameNotFoundException("User not found");
		}
		//if login success call CustomSuccessHandler
		//put user data to security and remain user login website

		List<GrantedAuthority> authorities = new ArrayList<>();
		//get role from listRole of UserEntity
		for (RoleEntity role: userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),
							true, true, true, true, authorities);
		myUser.setFullName(userEntity.getFullName());
		//this user only get username and password, somtimes we need more information from user so we custom it to user MyUser to has full name DTO(Data transfer Object)

//		User user = new User(userEntity.getUserName(), userEntity.getPassword(),
//				true, true, true,true, authorities);

		return myUser;
	}

}
