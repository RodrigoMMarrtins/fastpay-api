package com.example.fastpayapi.fastpayapi.repository;

 import com.example.fastpayapi.fastpayapi.domain.User.User;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.Optional;
 
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByCPF(Long cpf);

    Optional<User> findUserById(Long id);

}
