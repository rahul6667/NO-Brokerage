package com.example.demo.all_services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.all_modal.City;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;

public interface OwnerService {

	Owner findByName(String username);

	String registration(Owner owner);

	OwnerProperty addOwnerProperty(OwnerProperty ownerProperty);

	List<City> getCity();

	String getRoll(String username);

	String deletePropertyById(Long propertyId);

	Owner getownerDetails(String name);

	List<OwnerProperty> getProperty1(String ownerName);

	Owner getownerDetailsById(Long id);

	List<OwnerProperty> getownerPropertyById(Long id);

}
