package com.example.demo.helper;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.all_controller.UserController;
import com.example.demo.all_dao.OwnerDao;
import com.example.demo.all_modal.Owner;
import com.example.demo.all_services.OwnerService;
import com.example.demo.security.CustomUserDetailsService;

//Cross Origin is used to access for End User 
@RestController
@CrossOrigin(origins = "*")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private OwnerDao owner;

	@Autowired
	OwnerService ownerService;

	@Autowired
	private UserDetailsService customUserDetailsService;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(JwtController.class);

	// to genrate the JWT and Retunr the Response
	@RequestMapping(value = "/genrateToken", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		LOGGER.info("JwtController - generateToken - started");
		System.out.println(jwtRequest);
		try {
			// Authenticate User Based on UserName And Password
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.Password));
		} catch (UsernameNotFoundException e) {
			// TODO: handle exception
			LOGGER.info("JwtController - generateToken - "+e.getMessage());
			e.printStackTrace();
			throw new Exception("bad credentials");
		}
		// get user Details based on UserName
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		Owner ownerDetails = ownerService.getownerDetails(jwtRequest.getUsername());
		String Genrated_Token = this.jwtUtil.generateToken(userDetails);
		System.out.println("This is genrated Token =" + Genrated_Token);

		String roll = ownerService.getRoll(jwtRequest.getUsername());
		LOGGER.info("JwtController - generateToken - end");
		//return ResponseEntity.ok(new JwtResponse(Genrated_Token + roll));
		 return ResponseEntity.ok(new JwtResponse(ownerDetails,Genrated_Token));

	}

}
