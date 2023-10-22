package com.example.fastpayapi.fastpayapi.domain.User;

import com.example.fastpayapi.fastpayapi.dto.UserDTO;
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
    private Long CPF;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal accountBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.CPF = userDTO.CPF();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.userType = userDTO.userType();
    }
}
