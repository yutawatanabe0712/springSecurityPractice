package com.example.its.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //  認可設定
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //  認可設定
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(String username, String password, String authority){
        //ハッシュ化させるための記述
        var encodedPassword = passwordEncoder.encode(password);
        userRepository.insert(username, encodedPassword, authority);
    }
}
