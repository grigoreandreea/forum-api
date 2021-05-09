package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.config.JwtRequest;
import com.unibuc.forumApi.config.JwtResponse;
import com.unibuc.forumApi.config.JwtTokenUtil;
import com.unibuc.forumApi.config.JwtUserDetailsService;
import com.unibuc.forumApi.dto.UserRequest;
import com.unibuc.forumApi.mapper.UserMapper;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final UserService service;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationController(UserService service, UserMapper mapper, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.service = service;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody UserRequest userRequest) {
        User mappedUser = mapper.userRequestToUser(userRequest);
        User savedUser = service.create(mappedUser);
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @GetMapping("/userDetails")
    public Optional<User> getUserDetails(String username) {
        return service.getUserByUsername(username);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}