package com.bootcamp.bootcamp17.service.impl;

import com.bootcamp.bootcamp17.dto.request.UserRequestDto;
import com.bootcamp.bootcamp17.dto.response.CreateUserResponse;
import com.bootcamp.bootcamp17.entity.UserEntity;
import com.bootcamp.bootcamp17.exception.BadRequestException;
import com.bootcamp.bootcamp17.exception.DataNotFoundException;
import com.bootcamp.bootcamp17.repository.UserRepository;
import com.bootcamp.bootcamp17.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j // lebih gampang buat log
//@RequiredArgsConstructor // alternatif lain
public class UserServiceImpl implements UserService {
//    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); // cara manual
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(UserRequestDto req) {
        // practice: tambahin penjagaan email sudah ada (print aja)
        // kalo udah global exception handler, ga perlu try catch lagi
//        try{
//            if (userRepository.existsByEmail(req.getEmail())) {
//                throw new BadRequestException("Email sudah terdaftar");
//            }
//            UserEntity user = new UserEntity();
//            user.setFirstName(req.getFirstName());
//            user.setLastName(req.getLastName());
//            user.setEmail(req.getEmail());
//
//            userRepository.save(user);
//            return new CreateUserResponse(
//                    user.getFirstName() + " " + user.getLastName()
//            );
//        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("Terjadi error saat create user: " + e.getMessage());
//            log.error("[LOG] Terjadi error saat create user: " + e.getMessage());
////            logger.error("[LOGGER] Terjadi error saat create user: " + e.getMessage());
//            return new CreateUserResponse("Gagal membuat user");
//        }

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email sudah terdaftar");
        }
        UserEntity user = new UserEntity();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());

        userRepository.save(user);
        return new CreateUserResponse(
                user.getFirstName() + " " + user.getLastName()
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CreateUserResponse getUserById(Long id) {
//        Optional<UserEntity> userOpt = userRepository.findById(id);
//        if (userOpt.isEmpty()){
//            return new CreateUserResponse("kosong"); // cara handle yang lebih baik di day 3
//        }
//        UserEntity user = userOpt.get();

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User tidak ditemukan"));

        return new CreateUserResponse(
                user.getFirstName() + " " + user.getLastName()
        );
    }

    @Override
    public CreateUserResponse updateUser(Long id, UserRequestDto request) {
        // exercise: buat penjagaan sebelum update user
        // kalo user ga ada, throw DataNotFoundException
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()){
            throw new RuntimeException("Error"); // cara handle yang lebih baik di day 3
        }

        // practice: tambahin penjagaan email sudah ada (print aja)
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
        // exercise: buat penjagaan sebelum delete user
        // kalo user ga ada, throw DataNotFoundException
        try{
            Optional<UserEntity> userOpt = userRepository.findById(id);
//            if (userOpt.isEmpty()){
//                return false;
//            }
            UserEntity user = userOpt.get();
            userRepository.delete(user);
            return true;
        } catch (Exception e){
            System.out.println("Error saat delete user: " + e.getMessage());
            return false;
        } finally {
            System.out.println("Delete user selesai dijalankan");
        }
    }
}
