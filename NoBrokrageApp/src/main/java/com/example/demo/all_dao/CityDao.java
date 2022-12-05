package com.example.demo.all_dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.all_modal.City;

@Repository
public interface CityDao extends JpaRepository<City, Long>{

	

	
}

