package com.example.rsserver.security;

import com.example.rsserver.security.dto.JwtRequest;
import com.example.rsserver.security.dto.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    private JwtTokenUtil jwtTokenUtil;
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private PasswordEncoder passwordEncoder;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil,
                                       InMemoryUserDetailsManager inMemoryUserDetailsManager,
                                       PasswordEncoder passwordEncoder) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(authenticationRequest.getUsername());
        if (authenticate(userDetails, authenticationRequest.getPassword())) {
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Authentication failed");

    }

    private boolean authenticate(UserDetails userDetails, String providedPassword) {
        boolean isAccountOk = userDetails.isEnabled()
                && userDetails.isAccountNonExpired()
                && userDetails.isAccountNonLocked()
                && userDetails.isCredentialsNonExpired();
        return  isAccountOk &&
                passwordEncoder.matches(providedPassword, userDetails.getPassword());
    }
}

