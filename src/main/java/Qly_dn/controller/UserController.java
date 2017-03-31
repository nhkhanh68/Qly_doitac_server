package Qly_dn.controller;

import Qly_dn.DTO.UserDTO;
import Qly_dn.model.User;
import Qly_dn.service.UserService;
import Qly_dn.stereotype.NoAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nhkha on 25/03/2017.
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //login
    @NoAuthentication
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User Login(@RequestBody UserDTO userDTO) {
        return userService.Login(userDTO);
    }

    //logout
    @Required
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void Logout(HttpServletRequest request){
        String token = request.getHeader("auth-token");
        userService.Logout(token);
    }
}
