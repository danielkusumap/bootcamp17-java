package com.bootcamp.bootcamp17.repository;

import com.bootcamp.bootcamp17.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
