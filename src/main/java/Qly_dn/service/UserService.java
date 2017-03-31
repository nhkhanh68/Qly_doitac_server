package Qly_dn.service;

import Qly_dn.DTO.UserDTO;
import Qly_dn.model.User;
import Qly_dn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by nhkha on 25/03/2017.
 */
@Service
public class UserService {
    private final
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User Login(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        if(user != null){
            if (userDTO.getPassword().equals(user.getPassword())) {
                if (user.getToken() == null) {
                    user.setToken(UUID.randomUUID().toString());
                }
                return userRepository.save(user);
            } else{
                throw new NullPointerException("Wrong password");
            }
        } else{
            throw new NullPointerException("Worng username");
        }
    }

    public void Logout(String token) {
        User user = userRepository.findByToken(token);
        user.setToken(null);
        userRepository.save(user);
    }
}
