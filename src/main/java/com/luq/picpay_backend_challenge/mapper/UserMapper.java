package com.luq.picpay_backend_challenge.mapper;

import com.luq.picpay_backend_challenge.domain.User;
import com.luq.picpay_backend_challenge.dto.request.UserRequest;
import com.luq.picpay_backend_challenge.dto.response.UserResponse;

import java.util.List;

public class UserMapper {
    public User toEntity(UserRequest data){
        return new User(
            data.fullName(),
            data.mail(),
            data.document(),
            data.password(),
            data.balance()
        );
    }

    public UserResponse toDTO(User entity){
        return new UserResponse(
            entity.getId(),
            entity.getUserType(),
            entity.getFullName(),
            entity.getDocument(),
            entity.getMail(),
            entity.getPassword()
        );
    }

    public List<UserResponse> toDTOList(List<User> entityList){
        return entityList.stream().map(this::toDTO).toList();
    }
}
