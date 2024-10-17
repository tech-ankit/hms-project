package com.hsm.controller;

import com.hsm.entity.AppUser;
import com.hsm.payload.AppUserDto;
import com.hsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(
            @RequestBody AppUserDto appUserDto
    ){
        return userService.createAccount(appUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestParam String username,
            @RequestParam String password
    ){
        return userService.loginUser(username , password);
    }

}
