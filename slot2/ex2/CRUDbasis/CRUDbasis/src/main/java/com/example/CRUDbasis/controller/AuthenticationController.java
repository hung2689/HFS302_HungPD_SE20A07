package com.example.CRUDbasis.controller;

import com.example.CRUDbasis.dto.request.ApiResponse;
import com.example.CRUDbasis.dto.request.AuthenticationRequest;
import com.example.CRUDbasis.dto.response.AuthenticationResponse;
import com.example.CRUDbasis.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticated (@RequestBody AuthenticationRequest request){
       var result = authenticationService.authenticationService(request);
       return ApiResponse.<AuthenticationResponse>builder().
               result(result).build();
    }
}
