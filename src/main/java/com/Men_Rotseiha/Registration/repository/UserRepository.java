package com.Men_Rotseiha.Registration.repository;

import com.Men_Rotseiha.Registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByUsernameOrEmail(String username, String email);
    //Optional<User> findByEmail(String username);
    Optional<User> findByEmail(String email);

}
