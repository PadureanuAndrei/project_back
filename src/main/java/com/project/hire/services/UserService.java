package com.project.hire.services;

import com.project.hire.converters.UserConverter;
import com.project.hire.dto.user.RegistrationDto;
import com.project.hire.dto.user.UpdateUserDto;
import com.project.hire.dto.user.UserDto;
import com.project.hire.exceptions.NotFoundException;
import com.project.hire.models.File;
import com.project.hire.models.User;
import com.project.hire.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto getUser(long id) {
        User user = findUserById(id);

        return userConverter.convert(user);
    }

    @Transactional
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userConverter::convert)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public UserDto updateUser(UpdateUserDto userDto) {
        User user = findUserById(userDto.getId());
        mergeUserAndDto(user, userDto);
        user = userRepository.save(user);

        return userConverter.convert(user);
    }

    @Transactional
    public UserDto createUser(RegistrationDto dto) {
        User user = createUserFromDto(dto);
        user = userRepository.save(user);

        return userConverter.convert(user);
    }

    User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private void mergeUserAndDto(User user, UpdateUserDto dto) {
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getPhoto() != null) {
            File file = fileService.saveFile(dto.getPhoto());
            user.setPhoto(file);
        }
    }

    private User createUserFromDto(RegistrationDto dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return user;
    }
}
