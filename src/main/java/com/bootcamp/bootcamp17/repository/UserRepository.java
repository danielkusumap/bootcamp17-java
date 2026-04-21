package com.bootcamp.bootcamp17.repository;

import com.bootcamp.bootcamp17.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 1. DERIVED QUERY (Method Naming Convention)
    // Cukup buat method dengan nama yang sesuai, JPA auto-implement

    // Cari user by email (exact match)
    Optional<UserEntity> findByEmail(String email);

    // Cari user yang firstName-nya mengandung kata tertentu (case insensitive)
    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);

    // Cari user by lastName (exact match)
    List<UserEntity> findByLastName(String lastName);

    // Cari user by firstName DAN lastName (AND condition)
    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    // Cek apakah user dengan email tertentu sudah ada
    boolean existsByEmail(String email);

    // Hapus user by email
    void deleteByEmail(String email);


    // 2. JPQL (Java Persistence Query Language)
    // Query berorientasi ENTITY (bukan tabel)

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findUserByEmailJPQL(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%")
    List<UserEntity> searchByNameJPQL(@Param("keyword") String keyword);

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<UserEntity> findUserByEmailCaseInsensitive(@Param("email") String email);

    @Query("SELECT u.firstName, u.lastName, u.email FROM UserEntity u WHERE u.id = :id")
    Object[] findUserProjectionById(@Param("id") Long id);  // return array of fields


    // 3. NATIVE QUERY (SQL Murni)
    // Query SQL sesuai database (PostgreSQL)

    @Query(value = "SELECT * FROM mst_user WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findUserByEmailNative(@Param("email") String email);

    @Query(value = "SELECT * FROM mst_user WHERE first_name ILIKE %:keyword% OR last_name ILIKE %:keyword%",
            nativeQuery = true)
    List<UserEntity> searchByNameNative(@Param("keyword") String keyword);

    @Query(value = "SELECT id, email, first_name, last_name, created_at FROM mst_user ORDER BY created_at DESC LIMIT :limit",
            nativeQuery = true)
    List<UserEntity> findTopNLatestUsers(@Param("limit") int limit);

    @Query(value = "INSERT INTO mst_user (email, first_name, last_name) VALUES (:email, :firstName, :lastName) RETURNING *",
            nativeQuery = true)
    UserEntity insertAndReturnUser(@Param("email") String email,
                                   @Param("firstName") String firstName,
                                   @Param("lastName") String lastName);

    @Query(value = "UPDATE mst_user SET first_name = :firstName WHERE email = :email",
            nativeQuery = true)
    @Modifying
    @Transactional
    int updateFirstNameByEmail(@Param("firstName") String firstName,
                               @Param("email") String email);
}
