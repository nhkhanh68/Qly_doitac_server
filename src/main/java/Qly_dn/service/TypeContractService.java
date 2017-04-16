package Qly_dn.service;

import Qly_dn.DTO.TypeContactDTO;
import Qly_dn.model.*;
import Qly_dn.repository.ActivityLogRepository;
import Qly_dn.repository.ContractRepository;
import Qly_dn.repository.TypeContractRepository;
import Qly_dn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nhkha on 26/03/2017.
 */
@Service
public class TypeContractService {
    private final
    TypeContractRepository typeContractRepository;

    private final
    ContractRepository contractRepository;

    private final
    UserRepository userRepository;

    private final
    ActivityLogRepository activityLogRepository;

    @Autowired
    public TypeContractService(TypeContractRepository typeContractRepository, ContractRepository contractRepository, UserRepository userRepository, ActivityLogRepository activityLogRepository) {
        this.typeContractRepository = typeContractRepository;
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.activityLogRepository = activityLogRepository;
    }

    public TypeContract createTypeContract(TypeContactDTO typeContractDTO, String token) {
        TypeContract typeContract = typeContractRepository.findByTypeContract(typeContractDTO.getTypeContract());
        if (typeContract == null){
            if (typeContractDTO.getTypeContract() != null){
                TypeContract typeContract1 = new TypeContract();
                typeContract1.setTypeContract(typeContractDTO.getTypeContract());
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("createTypeContract");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " thêm loại Hợp đồng " +
                            typeContractDTO.getTypeContract() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
                return typeContractRepository.save(typeContract1);
            } else {
                throw new NullPointerException("Có lỗi xảy ra khi tạo loại hợp đồng!");
            }
        } else {
            throw new NullPointerException("Loại hợp đồng đã tồn tại!");
        }
    }

    public TypeContract editTypeContract(TypeContactDTO typeContractDTO, String token){
        TypeContract typeContract = typeContractRepository.findOne(typeContractDTO.getId());
        if(typeContract != null){
            if (typeContractDTO.getTypeContract() != null){
                typeContract.setTypeContract(typeContractDTO.getTypeContract());
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("editTypeContract");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa loại Hợp đồng " +
                            typeContract.getTypeContract() + " thành: " + typeContractDTO.getTypeContract() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
                return typeContractRepository.save(typeContract);
            } else {
                throw new NullPointerException("Có lỗi xảy ra khi sửa loại hợp đồng!");
            }
        } else {
            throw new NullPointerException("Không tìm thấy loại hợp đồng");
        }
    }

    public void deleteTypeContract(int typeContractId, String token){
        if (typeContractRepository.findById(typeContractId) != null){
            if(!contractRepository.findByTypeContractId(typeContractId).isEmpty()){
                throw new NullPointerException("Bạn không thể xóa loại hợp đồng này vì 1 số bản hợp đồng đang sử dụng loại hợp đồng này");
            } else {
                typeContractRepository.delete(typeContractId);
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("deleteTypeContract");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa loại Hợp đồng " +
                            typeContractRepository.findById(typeContractId).getTypeContract() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
            }
        } else {
            throw new NullPointerException("Không tìm thấy loại hợp đồng này " + typeContractId);
        }
    }

    public List<TypeContract> getAll() {
        return (List<TypeContract>) typeContractRepository.findAll();
    }
}
