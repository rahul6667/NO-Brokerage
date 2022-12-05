package com.example.demo.all_modal;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Owner {

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String password;
	private String mobileNo;
	private String roll;
	private String email;
	private String status;
	private String firstName;
	private String lastName;

	public Owner() {
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", password=" + password + ", mobileNo=" + mobileNo + ", roll="
				+ roll + ", email=" + email + ", status=" + status + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	public Owner(long id, String name, String password, String mobileNo, String roll, String email, String status,
			String firstName, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobileNo = mobileNo;
		this.roll = roll;
		this.email = email;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
