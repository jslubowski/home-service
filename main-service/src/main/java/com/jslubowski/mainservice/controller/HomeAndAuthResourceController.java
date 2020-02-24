package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.model.AuthenticationRequest;
import com.jslubowski.mainservice.model.AuthenticationResponse;
import com.jslubowski.mainservice.model.MyUserDetails;
import com.jslubowski.mainservice.service.MyUserDetailService;
import com.jslubowski.mainservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeAndAuthResourceController {

    /*
        TODO
        Prototype welcome screens
     */

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping
    public String home(){
        return ("<h1>Welcome</h1");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome user</h1");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome admin</h1");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails myUserDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(myUserDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
