package com.example.demo.all_controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.all_modal.City;
import com.example.demo.all_modal.ImageModel;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;
import com.example.demo.all_services.OwnerService;
import com.example.demo.helper.ResponseBack;

//Using Cross Origin to give the access to end User
@RestController
@CrossOrigin("*")

public class OwnerController {

	@Autowired
	OwnerService ownerService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger LOGGER= LoggerFactory.getLogger(OwnerController.class);
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<ResponseBack> registration(@RequestBody Owner owner) {
		LOGGER.info("OwnerController - registration - started");
		//encoding password with bCryptPasswordEncoder
		owner.setPassword(this.bCryptPasswordEncoder.encode(owner.getPassword()));
		
		// send the owner registration date to data base
		String status = ownerService.registration(owner);
		LOGGER.info("OwnerController - registration - end");
		return ResponseEntity.ok(new ResponseBack(status));
	}

	// to get the cite which is added by admin
	@RequestMapping("/getallcity")
	public Set<City> getAllCity() {
		LOGGER.info("OwnerController - getAllCity - started");
		List<City> returnCity = new ArrayList<>();
		returnCity = ownerService.getCity();
		Set<City> hSet = new HashSet<City>();
		for (City x : returnCity) {
			hSet.add(x);
		}
		LOGGER.info("OwnerController - getAllCity - end");
		return hSet;
	}

	@RequestMapping(value = "/deletebyid/{propertyId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseBack> deletebyid(@PathVariable("propertyId") Long propertyId) {
		LOGGER.info("OwnerController - deletebyid - started");
		// delete data using id
		String result = ownerService.deletePropertyById(propertyId);
		LOGGER.info("OwnerController - deletebyid - end");
		return ResponseEntity.ok(new ResponseBack(result));
	}

	// get all property using id
	@RequestMapping(value = "/getAllPropertyById/{propertyId}", method = RequestMethod.GET)
	public List<OwnerProperty> getAllPropertyById(@PathVariable("propertyId") Long propertyId) {
		LOGGER.info("OwnerController - getAllPropertyById - started");
		List<OwnerProperty> returnProperty1 = new ArrayList<>();
		try {
			returnProperty1 = ownerService.getownerPropertyById(propertyId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("OwnerController - getAllPropertyById - end");
		return returnProperty1;
	}

	@RequestMapping("/getAllProperty")
	public List<OwnerProperty> getAllProperty(HttpServletRequest request) {
		LOGGER.info("OwnerController - getAllProperty - started");
		List<OwnerProperty> returnProperty = new ArrayList<>();
		String ownerName = (String) request.getAttribute("OWNER_NAME");
		try {
			returnProperty = ownerService.getProperty1(ownerName);
		} catch (Exception e) {
			LOGGER.info("OwnerController - getAllProperty - "+e.getMessage());
			e.printStackTrace();
		}
		LOGGER.info("OwnerController - getAllProperty - end");
		return returnProperty;
	}

	// here using MultiPart File to get all Images Value which coming from end user
	@RequestMapping(value = "/addownerproperty", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public OwnerProperty addownerproperty(HttpServletRequest request,
			@RequestPart("ownerProperty") OwnerProperty ownerProperty, @RequestPart("imageFile") MultipartFile[] file) {
		LOGGER.info("OwnerController - addownerproperty - start");
		String ownerName = (String) request.getAttribute("OWNER_NAME");
		Owner ownerDetails = ownerService.getownerDetails(ownerName);
		try {
			ownerProperty.setOwnerName(ownerName);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("OwnerController - addownerproperty - "+e.getMessage());
			e.printStackTrace();
		}

		OwnerProperty ownerProperty_Response = null;
		try {
			// calling upload method to set images
			List<ImageModel> image = uploadImage(file);
			ownerProperty.setPropertyImage(image);
			ownerProperty_Response = ownerService.addOwnerProperty(ownerProperty);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("OwnerController - addownerproperty - "+e.getMessage());
			e.printStackTrace();
		}
		LOGGER.info("OwnerController - addownerproperty - end");
		return ownerProperty_Response;
	}

	// geting image from multipartfile and adding in image model
	public List<ImageModel> uploadImage(MultipartFile[] multipartFile) throws IOException {
		LOGGER.info("OwnerController - uploadImage - start");
		List<ImageModel> imageModels = new ArrayList<>();
		for (MultipartFile file : multipartFile) {
			ImageModel imagemodel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());

			imageModels.add(imagemodel);
		}
		LOGGER.info("OwnerController - uploadImage - end");
		return imageModels;
	}

}
