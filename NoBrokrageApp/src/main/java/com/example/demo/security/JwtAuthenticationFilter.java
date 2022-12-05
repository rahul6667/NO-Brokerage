package com.example.demo.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.helper.JwtUtil;

//any request will come here first
//validate all request here
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private static final Logger LOGGER= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get jwt token
		LOGGER.info("JwtAuthenticationFilter - doFilterInternal - started");
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			if (jwtToken.contains("OWENER")) {
				String orignalToken = jwtToken.replace("OWENER", " ");

				jwtToken = orignalToken;
			} else if (jwtToken.contains("USER")) {
				String orignalToken = jwtToken.replace("USER", " ");

				jwtToken = orignalToken;
			}

			try {
				boolean b = this.jwtUtil.isTokenExpired(jwtToken);

				username = this.jwtUtil.getUsername(jwtToken);

			} catch (Exception e) {
				LOGGER.info("JwtAuthenticationFilter - doFilterInternal - "+e.getMessage());
				// TODO: handle exception
				e.printStackTrace();
			}

			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			// validation code

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// credential insted of null
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				request.setAttribute("OWNER_NAME", username);
			} else {
				System.out.println("Token is no validate !!");
			}

		}
		LOGGER.info("JwtAuthenticationFilter - doFilterInternal - end ");
		filterChain.doFilter(request, response);

	}

}
