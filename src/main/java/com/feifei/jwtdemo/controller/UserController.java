package com.feifei.jwtdemo.controller;

import com.feifei.jwtdemo.annotations.JwtIgnore;
import com.feifei.jwtdemo.dto.AddUserDTO;
import com.feifei.jwtdemo.dto.UserLoginDTO;
import com.feifei.jwtdemo.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserController {

    UserService userService;

    @JwtIgnore
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO request) {
        // 分配jwt web token
        return ResponseEntity.ok(userService.login(request));
    }

    @JwtIgnore
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AddUserDTO request) {
        // 分配jwt web token
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok(userService.index());
    }
}
