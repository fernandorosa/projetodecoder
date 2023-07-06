package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){
        log.debug("POST registerUser userDto received {}", userDto.toString());
        if(userService.existByUserName(userDto.getUserName())){
            log.warn("UserName {} is Already taken ", userDto.getUserName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("UserName is Already taken!");
        }

        if(userService.existByEmail(userDto.getEmail())){
            log.warn("Email {} is Already taken", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userService.save(userModel);
        log.debug("POST registerUser userModel saved {}", userModel.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}

