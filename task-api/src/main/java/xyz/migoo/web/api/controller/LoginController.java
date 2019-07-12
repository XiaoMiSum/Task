package xyz.migoo.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.migoo.web.api.service.UserService;
import xyz.migoo.web.dto.UserDTO;
import xyz.migoo.web.pojo.ResponseBean;
import xyz.migoo.web.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaomi
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(@RequestBody UserDTO userDTO) {
        try {
            UserVO userVO = userService.loginCheck(userDTO);
            Map<String, String> token = new HashMap<>(1);
            token.put("token", userService.getToken(userVO));
            return new ResponseBean().setStatus(200).setData(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean().setStatus(411).setData(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseBean logout(@RequestBody UserDTO userDTO) {
        return null;
    }
}