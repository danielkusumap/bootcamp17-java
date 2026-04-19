package com.bootcamp.bootcamp17.controller;

import com.bootcamp.bootcamp17.dto.request.UserRequestDto;
import com.bootcamp.bootcamp17.dto.response.BaseResponse;
import com.bootcamp.bootcamp17.dto.response.CreateUserResponse;
import com.bootcamp.bootcamp17.entity.UserEntity;
import com.bootcamp.bootcamp17.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CreateUserResponse>> create(
            @Valid @RequestBody UserRequestDto request
    ) {

//        UserEntity result = userService.create(request);
//
//        BaseResponse<UserEntity> response = new BaseResponse<>();
//        response.setMessage("Success create user");
//        response.setData(result);

        CreateUserResponse user = userService.createUser(request);
        BaseResponse<CreateUserResponse> response = new BaseResponse<>();
        response.setMessage("Success create user");
        response.setData(user);


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserEntity>>> getAll() {
        List<UserEntity> users = userService.getAllUsers();
        BaseResponse<List<UserEntity>> response = new BaseResponse<>();
        response.setMessage("Success get all users");
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CreateUserResponse>> getUserById(
            @PathVariable Long id
    ) {

        CreateUserResponse result = userService.getUserById(id);

        BaseResponse<CreateUserResponse> response = new BaseResponse<>();
        response.setMessage("Success get user by id");
        response.setData(result);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CreateUserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request
    ) {

        CreateUserResponse result = userService.updateUser(id, request);

        BaseResponse<CreateUserResponse> response = new BaseResponse<>();
        response.setMessage("Success update user");
        response.setData(result);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteUser(
            @PathVariable Long id
    ) {

        boolean isDeleted = userService.deleteUser(id);

        BaseResponse<String> response = new BaseResponse<>();

        if (isDeleted) {
            response.setMessage("Success delete user");
            response.setData("User berhasil dihapus");
        } else {
            response.setStatus("F");
            response.setMessage("User tidak ditemukan");
            response.setData("Delete gagal");
        }

        return ResponseEntity.ok(response);
    }
}
