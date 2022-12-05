package com.example.demo.all_dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.all_modal.OwnerProperty;

public interface OwnerPropertyDao extends JpaRepository<OwnerProperty, Long>{

	List<OwnerProperty> findByOwnerName(String ownerName);

	List<OwnerProperty> PropertyId(Long id);
	
	

	//List<OwnerProperty> findByLocationContaining(String location);

	List<OwnerProperty> findByLocationContainingAndCity(String loc, String city);

}
