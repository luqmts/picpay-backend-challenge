package com.luq.picpay_backend_challenge.controllers;

import com.luq.picpay_backend_challenge.dto.request.UserRequest;
import com.luq.picpay_backend_challenge.dto.response.UserResponse;
import com.luq.picpay_backend_challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    protected final UserService UserService;

    @Autowired
    public UserController(UserService UserService){
        this.UserService = UserService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> UserResponseList = UserService.getAll();
        return ResponseEntity.ok().body(UserResponseList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Integer id){
        UserResponse UserResponse = UserService.getById(id);
        return ResponseEntity.ok().body(UserResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest data){
        UserResponse UserResponse = UserService.register(data);
        return ResponseEntity.ok().body(UserResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Integer id, @RequestBody UserRequest data){
        UserResponse UserResponse = UserService.update(id, data);
        return ResponseEntity.ok().body(UserResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Integer id){
        UserService.delete(id);

        return ResponseEntity.ok().body(Map.of("message", "Successfully deleted User with id: " + id.toString()));
    }
}
