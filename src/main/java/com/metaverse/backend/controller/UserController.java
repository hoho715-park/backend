package com.metaverse.backend.controller;

import com.metaverse.backend.domain.User;
import com.metaverse.backend.dto.UserRegisterDto;
import com.metaverse.backend.dto.UserLoginDto;
import com.metaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        user.setUserId(dto.getUserId());       // 로그인 아이디
        user.setUsername(dto.getUsername());   // 이름
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setBirthday(dto.getBirthday());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 비번 암호화

        userRepository.save(user);
        return "User registered successfully!";
    }

    // 로그인
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginDto dto) {
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> response = new HashMap<>();

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            response.put("status", "success");
            response.put("id", user.getId());            // ✅ DB PK (Long)
            response.put("userId", user.getUserId());    // 로그인 아이디 (String)
            response.put("username", user.getUsername());// 이름
        } else {
            response.put("status", "fail");
        }

        return response;
    }
}
