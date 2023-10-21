package com.example.fastpayapi.fastpayapi.domain.User;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private Integer CPF;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal accountBalance;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
