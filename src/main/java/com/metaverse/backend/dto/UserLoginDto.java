package com.metaverse.backend.dto;

public class UserLoginDto {
    private String userId;   // 로그인용 아이디
    private String password; // 비밀번호

    // Getter & Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
