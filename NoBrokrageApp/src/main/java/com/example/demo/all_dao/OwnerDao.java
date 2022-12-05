package com.example.demo.all_dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.all_modal.Owner;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Long>{

	Owner findByName(String username);

	
}
