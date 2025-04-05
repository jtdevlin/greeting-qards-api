package com.greetingQards.service;

import com.greetingQards.domain.User;
import com.greetingQards.mapper.UserMapper;
import com.greetingQards.repository.UserEntity;
import com.greetingQards.repository.UserRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        encoder = new BCryptPasswordEncoder();
    }
    public User createUser(User user) {
        String hashedPassword = encoder.encode(user.getPassword());
        user.addRole("USER");

        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setPassword(hashedPassword);
        userEntity.setCreatedTimestamp(LocalDateTime.now());

        userRepository.save(userEntity);

        return userMapper.toDomain(userEntity);
    }

    public User getUser(String userName) {
        UserEntity userEntity = userRepository.findById(userName)
                .orElseThrow(() -> new NotFoundException("User not found for userName: " + userName));
        return userMapper.toDomain(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userDetail = userRepository.findByEmail(username);

        return userDetail.map(userMapper::toDomain)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + username));
    }
}
