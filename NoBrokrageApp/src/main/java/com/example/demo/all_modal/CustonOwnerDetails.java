package com.example.demo.all_modal;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import antlr.collections.List;

public class CustonOwnerDetails implements UserDetails {

	private Owner owner;

	public CustonOwnerDetails(Owner owner) {
		// super();
		this.owner = owner;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(owner.getRoll());
		return java.util.List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return owner.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return owner.getName();
	}

	// @Override
//	public String getRoll() {
//		// TODO Auto-generated method stub
//		return owner.getRoll();
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
