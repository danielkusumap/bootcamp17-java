package com.bootcamp.bootcamp17.service.impl;

import com.bootcamp.bootcamp17.dto.request.UserRequestDto;
import com.bootcamp.bootcamp17.dto.response.CreateUserResponse;
import com.bootcamp.bootcamp17.entity.UserEntity;
import com.bootcamp.bootcamp17.repository.UserRepository;
import com.bootcamp.bootcamp17.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor // alternatif lain
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(UserRequestDto req) {
        UserEntity user =new UserEntity();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());

        // cara lain
//        UserEntity user = UserEntity.builder()
//                .fullName(request.getFullName())
//                .email(request.getEmail())
//                .phoneNumber(request.getPhoneNumber())
//                .createdAt(LocalDateTime.now())
//                .build();

        userRepository.save(user);
        return new CreateUserResponse(
                user.getFirstName() + user.getLastName()
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CreateUserResponse getUserById(Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()){
            return new CreateUserResponse("kosong"); // cara handle yang lebih baik di day 3
        }
        UserEntity user = userOpt.get();
        return new CreateUserResponse(
                user.getFirstName() + user.getLastName()
        );
    }

    @Override
    public CreateUserResponse updateUser(Long id, UserRequestDto request) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()){
            return new CreateUserResponse("kosong"); // cara handle yang lebih baik di day 3
        }
        UserEntity user = userOpt.get();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
        return new CreateUserResponse(
                user.getFirstName() + user.getLastName()
        );
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()){
            return false;
        }
        UserEntity user = userOpt.get();
        userRepository.delete(user);
        return true;
    }
}
