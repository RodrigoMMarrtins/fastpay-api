package com.example.fastpayapi.fastpayapi.dto;

import com.example.fastpayapi.fastpayapi.domain.User.UserType;

public record UserDTO(String name, String email, String password, Long CPF, UserType userType) {
}
