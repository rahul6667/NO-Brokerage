package com.example.demo.all_services;

import java.util.List;

import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;

public interface UserService {

	List<OwnerProperty> getAllPropertyForUser();

	List<OwnerProperty> getPropertyByLocation(String location, String city);

	//List getownerDetailsById(Long propertyId);
	Owner getownerDetailsById(Long propertyId);

}
