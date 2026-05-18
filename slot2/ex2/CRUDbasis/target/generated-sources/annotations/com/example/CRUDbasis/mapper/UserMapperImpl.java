package com.example.CRUDbasis.mapper;

import com.example.CRUDbasis.dto.request.UserCreationRequest;
import com.example.CRUDbasis.dto.request.UserUpdateRequest;
import com.example.CRUDbasis.dto.response.UserResponse;
import com.example.CRUDbasis.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-15T10:30:41+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.fullname( request.getFullname() );
        user.password( request.getPassword() );
        user.dob( request.getDob() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.fullname( user.getFullname() );
        userResponse.password( user.getPassword() );
        userResponse.dob( user.getDob() );

        return userResponse.build();
    }

    @Override
    public User updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return user;
        }

        user.setFullname( request.getFullname() );
        user.setPassword( request.getPassword() );
        user.setDob( request.getDob() );

        return user;
    }
}
