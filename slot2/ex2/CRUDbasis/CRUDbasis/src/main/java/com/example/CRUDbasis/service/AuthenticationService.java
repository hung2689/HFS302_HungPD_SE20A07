package com.example.CRUDbasis.service;

import com.example.CRUDbasis.dto.request.AuthenticationRequest;
import com.example.CRUDbasis.dto.response.AuthenticationResponse;
import com.example.CRUDbasis.exception.AppException;
import com.example.CRUDbasis.exception.ErrorCode;
import com.example.CRUDbasis.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    protected static  final  String SIGNER_KEY= "9a7993e79ec963e3723823945ec39a9fa27b2501fd8e13abcff0127125bccac7";
    public AuthenticationResponse authenticationService(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated= passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.AUTHENTICATED_FAILD);
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    public String generateToken(String username){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("learning.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("CustomClaim","custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.seriawwwwwwlize();
        }catch(JOSEException e){
        log.error("cannot create token",e);
        throw  new RuntimeException(e);
        }


    }
}



