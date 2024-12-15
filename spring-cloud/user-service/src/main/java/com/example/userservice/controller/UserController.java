package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestLogin;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userByAll = userService.getUserByAll();
        List<ResponseUser> users = new ArrayList<>();
        userByAll.forEach(userEntity -> {
            ResponseUser responseUser = new ModelMapper().map(userEntity, ResponseUser.class);
            users.add(responseUser);
        });
        return ResponseEntity.status(200)
                .body(users);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        System.out.println("requestUser = " + user);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        UserDto responseUserDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(responseUserDto, ResponseUser.class);

        return ResponseEntity.status(201)
                .body(responseUser);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUser);
    }

    /*@GetMapping("/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        System.out.println("requestLogin = " + requestLogin);

        return null;
    }*/

}
