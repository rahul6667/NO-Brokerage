package com.example.demo.all_services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.all_controller.UserController;
import com.example.demo.all_dao.CityDao;
import com.example.demo.all_dao.OwnerDao;
import com.example.demo.all_dao.OwnerPropertyDao;
import com.example.demo.all_modal.City;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;

@Service
public class OwnerServiceImpl implements OwnerService {

	
	private static final Logger LOGGER= LoggerFactory.getLogger(OwnerServiceImpl.class);
	
	@Autowired
	private OwnerDao owner;

	@Autowired
	OwnerPropertyDao ownerPropertyDao;
	@Autowired
	CityDao cityDao;

	@Override
	public Owner findByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	// for first time registration
	@Override
	public String registration(Owner own) {
		LOGGER.info("OwnerServiceImpl - registration - started");
		Owner status = null;
		try {
			status = owner.save(own);

		} catch (Exception e) {
			LOGGER.info("OwnerServiceImpl - registration - "+e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
		if (status != null) {
			LOGGER.info("OwnerServiceImpl - registration - end");
			return "Registration Successful";
		} else {
			LOGGER.info("OwnerServiceImpl - registration - end to fail");
			return "Registration failed";
		}

	}

	@Override
	public List<City> getCity() {
		LOGGER.info("OwnerServiceImpl - getCity - started");
		Set<City> response = new HashSet<>();
		LOGGER.info("OwnerServiceImpl - getCity - end");
		return cityDao.findAll();
	}

	@Override
	public List<OwnerProperty> getProperty1(String ownerName) {
		
		return ownerPropertyDao.findByOwnerName(ownerName);
	}

	@Override
	public List<OwnerProperty> getownerPropertyById(Long id) {
		// TODO Auto-generated method stub
		return ownerPropertyDao.PropertyId(id);
	}

	@Override
	public Owner getownerDetails(String name) {

		return owner.findByName(name);
	}

	@Override
	public String deletePropertyById(Long propertyId) {
		LOGGER.info("OwnerServiceImpl - deletePropertyById - started");
		String result = null;

		ownerPropertyDao.deleteById(propertyId);
		LOGGER.info("OwnerServiceImpl - deletePropertyById - end");
		return result;
	}

	@Override
	public OwnerProperty addOwnerProperty(OwnerProperty ownerProperty) {
		LOGGER.info("OwnerServiceImpl - addOwnerProperty - started");
		OwnerProperty result = ownerPropertyDao.save(ownerProperty);

		if (result != null) {
			LOGGER.info("OwnerServiceImpl - addOwnerProperty - end");
			return result;
		} else {
			LOGGER.info("OwnerServiceImpl - addOwnerProperty - end with null");
			return result;
		}
	}

	// get role of user
	@Override
	public String getRoll(String username) {
		LOGGER.info("OwnerServiceImpl - getRoll - started");
		// TODO Auto-generated method stub
		Owner details = owner.findByName(username);
		LOGGER.info("OwnerServiceImpl - getRoll - end");
		return details.getRoll();
	}

	@Override
	public Owner getownerDetailsById(Long id) {
		// TODO Auto-generated method stub
		return owner.getById(id);
	}

}
