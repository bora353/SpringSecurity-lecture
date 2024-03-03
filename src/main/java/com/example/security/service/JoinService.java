package com.example.security.service;

import com.example.security.dto.JoinDTO;
import com.example.security.entity.UserEntity;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO){

        // DB에 동일한 회원 존재하는지 확인
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return;
        }

        UserEntity entity = new UserEntity();

        entity.setUsername(joinDTO.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        entity.setRole("ROLE_ADMIN");

        userRepository.save(entity);
    }
}
