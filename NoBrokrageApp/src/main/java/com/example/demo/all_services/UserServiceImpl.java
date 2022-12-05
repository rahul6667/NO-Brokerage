package com.example.demo.all_services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.all_dao.CityDao;
import com.example.demo.all_dao.OwnerDao;
import com.example.demo.all_dao.OwnerPropertyDao;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private OwnerDao owner;

	@Autowired
	OwnerPropertyDao ownerPropertyDao;
	@Autowired
	CityDao cityDao;

	@Override
	public List<OwnerProperty> getAllPropertyForUser() {
		return ownerPropertyDao.findAll();
	}

	@Override
	public List<OwnerProperty> getPropertyByLocation(String loc, String city) {
		return ownerPropertyDao.findByLocationContainingAndCity(loc, city);
	}

	@Override
	public Owner getownerDetailsById(Long id) {
		// TODO Auto-generated method stub
		List<OwnerProperty> getOwnerProperty = new ArrayList<>();
		getOwnerProperty=ownerPropertyDao.PropertyId(id);

		String ownerName=null;
	for(int i=0; i<getOwnerProperty.size();i++) 
	{
		 ownerName = getOwnerProperty.get(i).getOwnerName();
		//
	}
	//String ownerName=getOwnerProperty.getOwnerName();
	
	Owner ownerDetails = owner.findByName(ownerName);
	
	
		return  ownerDetails;
	}
	
}
