package Qly_dn.service;

import Qly_dn.DTO.UnitNameDTO;
import Qly_dn.model.UnitName;
import Qly_dn.repository.ContractRepository;
import Qly_dn.repository.UnitNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by nhkha on 26/03/2017.
 */
@Service
public class UnitService {
    private final
    UnitNameRepository unitNameRepository;

    private final
    ContractRepository contractRepository;

    @Autowired
    public UnitService(UnitNameRepository unitNameRepository, ContractRepository contractRepository) {
        this.unitNameRepository = unitNameRepository;
        this.contractRepository = contractRepository;
    }

    public UnitName createUnit(UnitNameDTO unitNameDTO) {
        UnitName unit = unitNameRepository.findByUnitName(unitNameDTO.getUnitName());
        if (unit == null){
            if (unitNameDTO.getUnitName() != null){
                UnitName unitName = new UnitName();
                unitName.setUnitName(unitNameDTO.getUnitName());
                return unitNameRepository.save(unitName);
            } else {
                throw new NullPointerException("Có lỗi xảy ra khi tạo đơn vị!");
            }
        } else {
            throw new NullPointerException("Đơn vị này đã tồn tại!");
        }
    }

    public void editUnit(UnitNameDTO unitNameDTO){
        UnitName unit = unitNameRepository.findOne(unitNameDTO.getId());
        if (unit != null){
            if (unitNameDTO.getUnitName() != null){
                unit.setUnitName(unitNameDTO.getUnitName());
                unitNameRepository.save(unit);
            }else {
                throw new NullPointerException("Tên đơn vị trống!");
            }
        } else {
            throw new NullPointerException("Đơn vị không tồn tại!");
        }
    }

    public void deleteUnit(int unitId){
        if (unitNameRepository.findOne(unitId) != null){
            if (!contractRepository.findByUnitNameId(unitId).isEmpty()){
//                return contractRepository.findByUnitNameId(unitId);
                throw new NullPointerException("Không thể xóa đơn vị này vì đơn vị đang nằm trong 1 số hợp đồng!");
            } else {
//                throw new NullPointerException("Không thể xóa đơn vị này vì đơn vị đang nằm trong 1 số hợp đồng!");
                unitNameRepository.delete(unitId);
            }
        }
        else {
            throw new NullPointerException("Không tồn tại đơn vị này!");
        }
    }

    public List<UnitName> getAll() {
        return (List<UnitName>) unitNameRepository.findAll();
    }

    //create
}
