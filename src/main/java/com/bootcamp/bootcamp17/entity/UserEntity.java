package com.bootcamp.bootcamp17.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "mst_user")
@Data // setter getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @CreationTimestamp // Digunakan agar createdAt otomatis terisi saat pertama kali data di-insert
    private LocalDateTime createdAt;
}
