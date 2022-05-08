package com.project.hire.controllers;

import com.project.hire.dto.user.JwtTokenDto;
import com.project.hire.dto.user.LoginDto;
import com.project.hire.dto.user.RegistrationDto;
import com.project.hire.dto.user.UserDto;
import com.project.hire.services.JwtUserDetailsService;
import com.project.hire.services.UserService;
import com.project.hire.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid RegistrationDto dto) {
        return userService.createUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto login) throws Exception {
        authenticate(login.getEmail(), login.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenDto(token));
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
