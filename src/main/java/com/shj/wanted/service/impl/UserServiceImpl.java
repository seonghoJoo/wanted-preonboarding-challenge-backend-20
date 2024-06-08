package com.shj.wanted.service.impl;

import com.shj.wanted.dto.UserDto;
import com.shj.wanted.entity.User;
import com.shj.wanted.repository.UserRepository;
import com.shj.wanted.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .username(userDto.getUsername())
                //.password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(User.Role.USER)
                .build();

        userRepository.save(user);
    }
}
