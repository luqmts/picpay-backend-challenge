package com.luq.picpay_backend_challenge.services;

import com.luq.picpay_backend_challenge.domain.User;
import com.luq.picpay_backend_challenge.dto.request.UserRequest;
import com.luq.picpay_backend_challenge.dto.response.UserResponse;
import com.luq.picpay_backend_challenge.exceptions.InvalidDocumentException;
import com.luq.picpay_backend_challenge.exceptions.InvalidMailException;
import com.luq.picpay_backend_challenge.exceptions.NotFoundException;
import com.luq.picpay_backend_challenge.exceptions.PasswordDontMatchException;
import com.luq.picpay_backend_challenge.mapper.UserMapper;
import com.luq.picpay_backend_challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse register(UserRequest request){
        if(!request.password().equals(request.confirmPassword()))
            throw new PasswordDontMatchException("Passwords don't match");

        if(userRepository.existsByDocument(request.document()))
            throw new InvalidDocumentException("This document is already registered");

        if(userRepository.existsByMail(request.mail()))
            throw new InvalidMailException("This mail is already registered");

        User entity = userMapper.toEntity(request);
        User entitySaved = userRepository.save(entity);
        return userMapper.toDTO(entitySaved);
    }

    public UserResponse update(Integer id, UserRequest request){
        if(!request.password().equals(request.confirmPassword()))
            throw new PasswordDontMatchException("Passwords don't match");

        if(userRepository.existsByDocumentAndIdNot(request.document(), id))
            throw new InvalidDocumentException("This document is already registered");

        if(userRepository.existsByMailAndIdNot(request.mail(), id))
            throw new InvalidMailException("This mail is already registered");

        User entity = userMapper.toEntity(request);
        User savedEntity = userRepository.save(entity);
        return userMapper.toDTO(savedEntity);
    }

    public UserResponse getById(Integer id){
        User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.toDTO(entity);
    }

    public List<UserResponse> getAll(){
        return userMapper.toDTOList(userRepository.findAll());
    }

    public void delete(Integer id){
        User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.deleteById(id);
    }
}
