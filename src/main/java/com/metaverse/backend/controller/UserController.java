package com.metaverse.backend.controller;

import com.metaverse.backend.domain.User;
import com.metaverse.backend.dto.UserRegisterDto;
import com.metaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setBirthday(dto.getBirthday());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 비번 인코딩

        userRepository.save(user);
        return "User registered successfully!";
    }
}
