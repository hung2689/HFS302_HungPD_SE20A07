package com.example.CRUDbasis.mapper;

import com.example.CRUDbasis.dto.request.UserCreationRequest;
import com.example.CRUDbasis.dto.request.UserUpdateRequest;
import com.example.CRUDbasis.dto.response.UserResponse;
import com.example.CRUDbasis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    User updateUser(@MappingTarget User user , UserUpdateRequest request);
}
