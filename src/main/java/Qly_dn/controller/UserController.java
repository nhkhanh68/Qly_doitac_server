package Qly_dn.controller;

import Qly_dn.DTO.UserDTO;
import Qly_dn.model.ActivityLog;
import Qly_dn.model.Role;
import Qly_dn.model.User;
import Qly_dn.service.UserService;
import Qly_dn.stereotype.NoAuthentication;
import Qly_dn.stereotype.RequiredRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void Logout(HttpServletRequest request){
        String token = request.getHeader("auth-token");
        userService.Logout(token);
    }

    //create unit account
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "unit/{unitNameId}/account/create", method = RequestMethod.POST)
    public void createUnitAccount(@RequestBody UserDTO userDTO, @PathVariable("unitNameId") int unitNameId){
        userService.createUnitAccount(userDTO, unitNameId);
    }

    //check all activity log
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "activityLog", method = RequestMethod.GET)
    public Set<ActivityLog> getAllActiviYyLog(){
        return userService.getAllActiviYyLog();
    }

    //get acivity log one user
    @RequiredRoles({Role.ADMIN})
    @RequestMapping(value = "activityLog/{userId}", method = RequestMethod.GET)
    public Set<ActivityLog> getActivityLogOfUser(@PathVariable("userId") int userId){
        return userService.getActivityLogOfUser(userId);
    }
}
