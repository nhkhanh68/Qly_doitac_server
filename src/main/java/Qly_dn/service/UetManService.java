package Qly_dn.service;

import Qly_dn.DTO.UetManDTO;
import Qly_dn.model.ActivityLog;
import Qly_dn.model.Role;
import Qly_dn.model.UetMan;
import Qly_dn.model.User;
import Qly_dn.repository.ActivityLogRepository;
import Qly_dn.repository.ContractRepository;
import Qly_dn.repository.UetManRepository;
import Qly_dn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nhkha on 27/03/2017.
 */
@Service
public class UetManService {
    private final
    UetManRepository uetManRepository;

    private final
    ContractRepository contractRepository;

    private final
    UserRepository userRepository;

    private final
    ActivityLogRepository activityLogRepository;

    @Autowired
    public UetManService(UetManRepository uetManRepository, ContractRepository contractRepository, UserRepository userRepository, ActivityLogRepository activityLogRepository) {
        this.uetManRepository = uetManRepository;
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.activityLogRepository = activityLogRepository;
    }

    public UetMan createUetMan(UetManDTO uetManDTO, String token) {
        UetMan uetMan = uetManRepository.findByUetManName(uetManDTO.getUetManName());
        if (uetMan == null){
            if (uetManDTO.getUetManName() != null){
                UetMan uetMan1 = new UetMan();
                uetMan1.setUetManName(uetManDTO.getUetManName());
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("createUetMan");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " tạo thêm Người kí (VNU-UET) " +
                            uetManDTO.getUetManName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
                return uetManRepository.save(uetMan1);
            } else {
                throw new NullPointerException("Tên Người ký (VNU-UET) trống");
            }

        } else {
            throw new NullPointerException("Người ký (VNU-UET) đã tồn tại!");
        }
    }

    public void editUetMan(UetManDTO uetManDTO, String token) {
        UetMan uetMan = uetManRepository.findOne(uetManDTO.getId());
        if (uetMan != null){
            if (uetManDTO.getUetManName() != null){
                uetMan.setUetManName(uetManDTO.getUetManName());
                uetManRepository.save(uetMan);
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("editUetMan");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa tên Người kí (VNU-UET) " +
                            uetMan.getUetManName() + " thành: " + uetManDTO.getUetManName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
            } else {
                throw new NullPointerException("Có lỗi khi sửa Người ký (VNU-UET)");
            }
        } else {
            throw new NullPointerException("Người ký (VNU-UET) không tồn tại!");
        }
    }

    public void deleteUetMan(int uetManId, String token) {
        if (uetManRepository.findOne(uetManId) != null){
            if (!contractRepository.findByUetManId(uetManId).isEmpty()){
                throw new NullPointerException("Không thể xóa Người ký (VNU-UET) này vì Người ký (VNU-UET) đang nằm trong 1 số hợp đồng!");
            } else {
                uetManRepository.delete(uetManId);
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("deleteUetMan");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa Người kí (VNU-UET) " +
                            uetManRepository.findOne(uetManId).getUetManName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
            }
        }
        else {
            throw new NullPointerException("Không tồn tại Người ký (VNU-UET) này!");
        }
    }

    public List<UetMan> getAll() {
        return (List<UetMan>) uetManRepository.findAll();
    }
}
