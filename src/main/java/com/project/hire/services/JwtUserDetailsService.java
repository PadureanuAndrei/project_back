package com.project.hire.services;

import com.project.hire.dto.user.UserDetailsDto;
import com.project.hire.exceptions.NotFoundException;
import com.project.hire.models.User;
import com.project.hire.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);

        return UserDetailsDto.builder()
                .username(email)
                .password(user.getPassword())
                .build();
    }
}
