package Qly_dn.service;

import Qly_dn.DTO.UserDTO;
import Qly_dn.model.ActivityLog;
import Qly_dn.model.Role;
import Qly_dn.model.UnitName;
import Qly_dn.model.User;
import Qly_dn.repository.ActivityLogRepository;
import Qly_dn.repository.UnitNameRepository;
import Qly_dn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * Created by nhkha on 25/03/2017.
 */
@Service
public class UserService {
    private final
    UserRepository userRepository;
    private final
    UnitNameRepository unitNameRepository;
    private final
    ActivityLogRepository activityLogRepository;

    @Autowired
    public UserService(UserRepository userRepository, UnitNameRepository unitNameRepository, ActivityLogRepository activityLogRepository) {
        this.userRepository = userRepository;
        this.unitNameRepository = unitNameRepository;
        this.activityLogRepository = activityLogRepository;
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

    public void createUnitAccount(UserDTO userDTO, int unitNameId) {
        if(userDTO.getPassword() != null && userDTO.getUserName() != null){
            UnitName unitName = unitNameRepository.findById(unitNameId);
            if(unitName != null){
                User user = new User(unitName);
                user.setPassword(userDTO.getPassword());
                user.setRole(Role.UNIT);
                user.setUserName(userDTO.getUserName());
                unitName.setUser(user);
                unitNameRepository.save(unitName);
//                userRepository.save(user);
            } else {
                throw new NullPointerException("Không tìm thấy đơn vị cần tạo tài khoản!");
            }
        } else {
            throw new NullPointerException("Có lỗi khi tạo tài khoản, hãy thử lại!");
        }
    }

    public Set<ActivityLog> getAllActiviYyLog() {
        return activityLogRepository.findAll();
    }

    public Set<ActivityLog> getActivityLogOfUser(int userId) {
        User user = userRepository.findById(userId);
        if (user != null){
            return user.getActivityLog();
        } else {
            throw new NullPointerException("Không tìm thấy user!");
        }
    }
}
