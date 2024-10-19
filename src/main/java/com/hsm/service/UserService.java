package com.hsm.service;

import com.hsm.entity.AppUser;
import com.hsm.payload.AppUserDto;
import com.hsm.payload.LoginDto;
import com.hsm.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private AppUserRepository appUserRepository;
    private ModelMapper mapper;
    private JwtService jwtService;


    public ResponseEntity<?> createAccount(AppUserDto appUser) {
        Optional<AppUser> email = appUserRepository.findByEmail(appUser.getEmail());
        if (email.isPresent()){
            return new ResponseEntity<>("Email already Exists",HttpStatus.FOUND);
        }
        Optional<AppUser> userName = appUserRepository.findByUsername(appUser.getUsername());
        if (userName.isPresent()){
            return new ResponseEntity<>("Username Already Exists" , HttpStatus.FOUND);
        }
        AppUser appUserEn = mapToEntity(appUser);
        appUserEn.setPassword(BCrypt.hashpw(appUser.getPassword(),BCrypt.gensalt(4)));
        AppUser saved = appUserRepository.save(appUserEn);
        AppUserDto appUserDto = mapToDto(saved);
        return new ResponseEntity<>(appUserDto , HttpStatus.CREATED);
    }

    AppUserDto mapToDto(AppUser appUser){
        return mapper.map(appUser , AppUserDto.class);
    }

    AppUser mapToEntity(AppUserDto appUser){
        return mapper.map(appUser , AppUser.class);
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            AppUser user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                String token = jwtService.generateToken(loginDto.getUsername());
                return token;
            }
            return null;
        }else {
            return null;
        }
    }
}
