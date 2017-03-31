package Qly_dn.service;

import Qly_dn.DTO.TypeContactDTO;
import Qly_dn.model.Contract;
import Qly_dn.model.TypeContract;
import Qly_dn.repository.ContractRepository;
import Qly_dn.repository.TypeContractRepository;
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

    @Autowired
    public TypeContractService(TypeContractRepository typeContractRepository, ContractRepository contractRepository) {
        this.typeContractRepository = typeContractRepository;
        this.contractRepository = contractRepository;
    }

    public TypeContract createTypeContract(TypeContactDTO typeContractDTO) {
        TypeContract typeContract = typeContractRepository.findByTypeContract(typeContractDTO.getTypeContract());
        if (typeContract == null){
            if (typeContractDTO.getTypeContract() != null){
                TypeContract typeContract1 = new TypeContract();
                typeContract1.setTypeContract(typeContractDTO.getTypeContract());
                return typeContractRepository.save(typeContract1);
            } else {
                throw new NullPointerException("Có lỗi xảy ra khi tạo loại hợp đồng!");
            }
        } else {
            throw new NullPointerException("Loại hợp đồng đã tồn tại!");
        }
    }

    public TypeContract editTypeContract(TypeContactDTO typeContractDTO){
        TypeContract typeContract = typeContractRepository.findOne(typeContractDTO.getId());
        if(typeContract != null){
            if (typeContractDTO.getTypeContract() != null){
                typeContract.setTypeContract(typeContractDTO.getTypeContract());
                return typeContractRepository.save(typeContract);
            } else {
                throw new NullPointerException("Có lỗi xảy ra khi sửa loại hợp đồng!");
            }
        } else {
            throw new NullPointerException("Không tìm thấy loại hợp đồng");
        }
    }

    public void deleteTypeContract(int typeContractId){
        if (typeContractRepository.findById(typeContractId) != null){
            if(!contractRepository.findByTypeContractId(typeContractId).isEmpty()){
//                return contractRepository.findByTypeContractId(typeContractId);
                throw new NullPointerException("Bạn không thể xóa loại hợp đồng này vì 1 số bản hợp đồng đang sử dụng loại hợp đồng này");
            } else {
                typeContractRepository.delete(typeContractId);
//                throw new NullPointerException("ko co trong contract");
            }
        } else {
            throw new NullPointerException("Không tìm thấy loại hợp đồng này " + typeContractId);
        }
    }

    public List<TypeContract> getAll() {
        return (List<TypeContract>) typeContractRepository.findAll();
    }
}
