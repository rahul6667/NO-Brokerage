package com.example.demo.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.all_dao.OwnerDao;
import com.example.demo.all_modal.CustonOwnerDetails;
import com.example.demo.all_modal.Owner;
import com.example.demo.helper.JwtController;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private OwnerDao owner;

	
	private static final Logger LOGGER= LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// get data from database using username
		LOGGER.info("CustomUserDetailsService - loadUserByUsername - started");
		Owner ownername = owner.findByName(username);
		if (ownername == null) {
			// throw exception
			throw new UsernameNotFoundException(ownername.getName());
		} else {
			// throw new UsernameNotFoundException("User Not Found !!");
			CustonOwnerDetails returnobjcet = new CustonOwnerDetails(ownername);
			// return returnobjcet;
			try {
				LOGGER.info("CustomUserDetailsService - loadUserByUsername - end");
				return new CustonOwnerDetails(ownername);
			} catch (Exception e) {
				LOGGER.info("CustomUserDetailsService - loadUserByUsername - "+e.getMessage());
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		LOGGER.info("CustomUserDetailsService - loadUserByUsername - end  with null");
		return null;
	}

}
