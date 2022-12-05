package com.example.demo.all_controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.all_modal.ImageModel;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_modal.OwnerProperty;
import com.example.demo.all_services.UserService;

//Using Cross Origin to give the access to end User
@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/getAllPropertyForUser")
	public List<OwnerProperty> getAllProperty(HttpServletRequest request) {
		LOGGER.info("UserController - getAllProperty - started");
		List<OwnerProperty> returnProperty = new ArrayList<>();
		try {
			returnProperty = userService.getAllPropertyForUser();
		} catch (Exception e) {
			LOGGER.info("UserController - getAllProperty - "+e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
		LOGGER.info("UserController - getAllProperty - end");
		return returnProperty;
	}

	// searching the propert based on city and location
	@RequestMapping(value = "/getPropertyByLocation/{location}", method = RequestMethod.GET)
	public List<OwnerProperty> getPropertyByLocation(@PathVariable("location") String location) {
		LOGGER.info("UserController - getPropertyByLocation - started");
		List<OwnerProperty> returnProperty1 = new ArrayList<>();
		// split image and location from object
		String[] arrOfStr = location.split(",");
		String loca = arrOfStr[0];
		String city = arrOfStr[1];
		try {
			returnProperty1 = userService.getPropertyByLocation(loca, city);
		} catch (Exception e) {
			LOGGER.info("UserController - getPropertyByLocation -"+e.getMessage());
			e.printStackTrace();
		}
		// return the property
		LOGGER.info("UserController - getPropertyByLocation - end");
		return returnProperty1;
	}
	
	
	// get all property using id
		@RequestMapping(value = "/getOwnerDetailsById/{propertyId}", method = RequestMethod.GET)
		public Owner getOwnerDetailsById(@PathVariable("propertyId") Long propertyId) {
			LOGGER.info("UserController - getOwnerDetailsById - started");
			Owner returnOwnerDetails = new Owner();
			try {
				returnOwnerDetails = userService.getownerDetailsById(propertyId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			LOGGER.info("UserController - getOwnerDetailsById - end");
			return returnOwnerDetails;
		}
	
}
