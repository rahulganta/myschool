package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.ContactForm;
import com.myschool.adminservice.security.JwtUtil;
import com.myschool.adminservice.security.LoginRequest;
import com.myschool.adminservice.security.LoginResponse;
import com.myschool.adminservice.security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Inconrrect username or password", e);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        String username = myUserDetails.getUsername();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        final String jwt = jwtUtil.generateJwtToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt, username, myUserDetails.getFirstName(), myUserDetails.getLastName(), myUserDetails.getEmail(),
                roles, myUserDetails.getLastLogin(), myUserDetails.getLang(), myUserDetails.getTheme(), myUserDetails.getSchool() ));
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        //Invalidate the jwt token and perform necessary actions needed on logout

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.ok(null);
        }

        SecurityContextHolder.clearContext();

        new SecurityContextLogoutHandler().logout(request, response, authentication);
        SecurityContextHolder.getContext().setAuthentication(null);

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "contact")
    @ResponseBody
    public ContactForm postContact(@RequestBody ContactForm contactForm) {
        /*fileHandler.writeToJsonFile(contactUsFile, contactForm);*/
        /*emailService.sendMail(sendToEmail, "myschool Contact Info", " " + contactForm);*/
        return contactForm;
    }

}
