package com.example.CRUDbasis.controller;

import com.example.CRUDbasis.dto.request.ApiResponse;
import com.example.CRUDbasis.dto.request.UserCreationRequest;
import com.example.CRUDbasis.dto.request.UserUpdateRequest;
import com.example.CRUDbasis.dto.response.UserResponse;
import com.example.CRUDbasis.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {
     UserService userService;
    @PostMapping( )
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
         apiResponse.setResult(userService.createRequest(request));
        return apiResponse;

    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String id){
        return userService.viewRequest(id);
    }

    @GetMapping()
    ApiResponse<List<UserResponse>> getAllUser(){
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAll());
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUserById(
            @PathVariable("userId") String id,
            @RequestBody UserUpdateRequest request){

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateRequest(request,id));

        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable("userId") String id){
        return userService.deleteRequest(id);
    }
}
