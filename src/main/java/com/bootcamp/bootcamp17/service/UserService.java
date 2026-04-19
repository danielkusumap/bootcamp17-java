package com.bootcamp.bootcamp17.service;

import com.bootcamp.bootcamp17.dto.request.UserRequestDto;
import com.bootcamp.bootcamp17.dto.response.CreateUserResponse;
import com.bootcamp.bootcamp17.entity.UserEntity;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(UserRequestDto req);
    List<UserEntity> getAllUsers();
    CreateUserResponse getUserById(Long id);
    CreateUserResponse updateUser(Long id, UserRequestDto request);
    boolean deleteUser(Long id);
}
