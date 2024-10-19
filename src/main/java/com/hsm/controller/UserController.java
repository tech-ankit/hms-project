package com.hsm.controller;

import com.hsm.payload.AppUserDto;
import com.hsm.payload.LoginDto;
import com.hsm.payload.TokenDto;
import com.hsm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> loginUser(
            @RequestBody LoginDto loginDto
            ){
        String token = userService.verifyLogin(loginDto);
        if (token != null) {
            TokenDto tokenDto = TokenDto.builder().token(token).type("JWT").build();
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Username And Password" ,HttpStatus.FORBIDDEN);
        }
    }

}
