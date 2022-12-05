package com.example.demo.all_modal;

import javax.persistence.Entity;
import javax.persistence.Table;

public class Searchproperty {

	private String city;
	private String location;

	@Override
	public String toString() {
		return "Searchproperty [city=" + city + ", location=" + location + "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
