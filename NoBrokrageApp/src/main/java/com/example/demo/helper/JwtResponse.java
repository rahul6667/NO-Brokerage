package com.example.demo.helper;

import com.example.demo.all_modal.Owner;

public class JwtResponse {

	Owner user;
	String Token;

	public Owner getUser() {
		return user;
	}

	public void setUser(Owner user) {
		this.user = user;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public JwtResponse(Owner user, String token) {
		super();
		this.user = user;
		Token = token;
	}

	public JwtResponse(String token) {
		Token = token;
	}

}
