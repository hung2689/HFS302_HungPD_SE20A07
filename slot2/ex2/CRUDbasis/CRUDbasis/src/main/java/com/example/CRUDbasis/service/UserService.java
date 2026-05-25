package com.example.CRUDbasis.service;

import com.example.CRUDbasis.dto.request.UserCreationRequest;
import com.example.CRUDbasis.dto.request.UserUpdateRequest;
import com.example.CRUDbasis.dto.response.UserResponse;
import com.example.CRUDbasis.entity.User;
import com.example.CRUDbasis.exception.AppException;
import com.example.CRUDbasis.exception.ErrorCode;
import com.example.CRUDbasis.mapper.UserMapper;
import com.example.CRUDbasis.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
       UserRepository userRepository;
       UserMapper userMapper;

    public UserResponse createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.UserExists);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse viewRequest(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateRequest(UserUpdateRequest request, String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        // mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public String deleteRequest(String id) {
        userRepository.deleteById(id);
        return "user has been delete";
    }

}
