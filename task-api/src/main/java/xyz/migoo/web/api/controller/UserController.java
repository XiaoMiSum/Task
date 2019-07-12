package xyz.migoo.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.migoo.web.api.service.UserService;
import xyz.migoo.web.dto.UserDTO;
import xyz.migoo.web.pojo.ResponseBean;

import java.util.UUID;

/**
 * @author xiaomi
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseBean register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }
}