package com.luq.picpay_backend_challenge.mapper;

import com.luq.picpay_backend_challenge.domain.User;
import com.luq.picpay_backend_challenge.domain.UserType;
import com.luq.picpay_backend_challenge.dto.request.UserRequest;
import com.luq.picpay_backend_challenge.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User toEntity(UserRequest data){
        return new User(
            data.fullName(),
            UserType.valueOf(data.userType()),
            data.mail(),
            data.document(),
            data.password(),
            data.balance()
        );
    }

    public UserResponse toDTO(User entity){
        return new UserResponse(
            entity.getId(),
            entity.getFullName(),
            entity.getUserType(),
            entity.getDocument(),
            entity.getMail(),
            entity.getBalance()
        );
    }

    public List<UserResponse> toDTOList(List<User> entityList){
        return entityList.stream().map(this::toDTO).toList();
    }
}
