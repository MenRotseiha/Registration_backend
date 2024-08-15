package com.Men_Rotseiha.Registration.config;

import com.Men_Rotseiha.Registration.entity.User;
import com.Men_Rotseiha.Registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class OurUserInfoUserDetailSevice implements UserDetailsService {
    @Autowired
    private UserRepository ourUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= ourUserRepository.findByEmail(username);
        return user.map(OurUserInfoUserDetail::new).orElseThrow(()->new UsernameNotFoundException("User Does not Exist"));
    }
}