package Qly_dn.service;

import Qly_dn.DTO.UetManDTO;
import Qly_dn.model.UetMan;
import Qly_dn.repository.ContractRepository;
import Qly_dn.repository.UetManRepository;
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

    @Autowired
    public UetManService(UetManRepository uetManRepository, ContractRepository contractRepository) {
        this.uetManRepository = uetManRepository;
        this.contractRepository = contractRepository;
    }

    public UetMan createUetMan(UetManDTO uetManDTO) {
        UetMan uetMan = uetManRepository.findByUetManName(uetManDTO.getUetManName());
        if (uetMan == null){
            if (uetManDTO.getUetManName() != null){
                UetMan uetMan1 = new UetMan();
                uetMan1.setUetManName(uetManDTO.getUetManName());
                return uetManRepository.save(uetMan1);
            } else {
                throw new NullPointerException("Tên Người ký (VNU-UET) trống");
            }

        } else {
            throw new NullPointerException("Người ký (VNU-UET) đã tồn tại!");
        }
    }

    public void editUetMan(UetManDTO uetManDTO) {
        UetMan uetMan = uetManRepository.findOne(uetManDTO.getId());
        if (uetMan != null){
            if (uetManDTO.getUetManName() != null){
                uetMan.setUetManName(uetManDTO.getUetManName());
                uetManRepository.save(uetMan);
            } else {
                throw new NullPointerException("Có lỗi khi sửa Người ký (VNU-UET)");
            }
        } else {
            throw new NullPointerException("Người ký (VNU-UET) không tồn tại!");
        }
    }

    public void deleteUetMan(int uetManId) {
        if (uetManRepository.findOne(uetManId) != null){
            if (!contractRepository.findByUetManId(uetManId).isEmpty()){
                throw new NullPointerException("Không thể xóa Người ký (VNU-UET) này vì Người ký (VNU-UET) đang nằm trong 1 số hợp đồng!");
            } else {
                uetManRepository.delete(uetManId);
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
