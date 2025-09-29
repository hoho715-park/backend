package com.metaverse.backend.controller;

import com.metaverse.backend.domain.User;
import com.metaverse.backend.dto.UserRegisterDto;
import com.metaverse.backend.dto.UserLoginDto;
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

    // 회원가입
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto dto) {
        User user = new User();
        user.setUserId(dto.getUserId());       // 로그인 ID
        user.setUsername(dto.getUsername());   // 회원 이름
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setBirthday(dto.getBirthday());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
        return "User registered successfully!";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto dto) {
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return "Login failed: Invalid password";
        }

        return "Login successful!";
    }
}
