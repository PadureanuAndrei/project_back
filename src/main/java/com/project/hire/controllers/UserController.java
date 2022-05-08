package com.project.hire.controllers;

import com.project.hire.dto.user.UpdateUserDto;
import com.project.hire.dto.user.UserDto;
import com.project.hire.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/users")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable long id,
                              @RequestBody UpdateUserDto dto
    ) {
        dto.setId(id);
        return userService.updateUser(dto);
    }

}
