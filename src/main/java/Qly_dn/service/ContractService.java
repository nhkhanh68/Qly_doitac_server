package Qly_dn.service;

import Qly_dn.DTO.CheckContractDTO;
import Qly_dn.DTO.ContractDTO;
import Qly_dn.DTO.CooperateActivityDTO;
import Qly_dn.DTO.ExcelContractDTO;
import Qly_dn.model.*;
import Qly_dn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 27/03/2017.
 */
@Service
public class ContractService {
    private final
    ContractRepository contractRepository;
    private final
    UetManRepository uetManRepository;
    private final
    UnitNameRepository unitNameRepository;
    private final
    PartnerContactRepository partnerContactRepository;
    private final
    PartnerRepository partnerRepository;
    private final
    TypeContractRepository typeContractRepository;
    private final
    PartnerInfoRepository partnerInfoRepository;
    private final
    CooperateActivityRepository cooperateActivityRepository;
    private final
    UserRepository userRepository;

    private final
    ActivityLogRepository activityLogRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, UetManRepository uetManRepository,
                           UnitNameRepository unitNameRepository, PartnerContactRepository partnerContactRepository,
                           PartnerRepository partnerRepository, TypeContractRepository typeContractRepository,
                           PartnerInfoRepository partnerInfoRepository, CooperateActivityRepository cooperateActivityRepository, UserRepository userRepository, ActivityLogRepository activityLogRepository) {
        this.contractRepository = contractRepository;
        this.uetManRepository = uetManRepository;
        this.unitNameRepository = unitNameRepository;
        this.partnerContactRepository = partnerContactRepository;
        this.partnerRepository = partnerRepository;
        this.typeContractRepository = typeContractRepository;
        this.partnerInfoRepository = partnerInfoRepository;
        this.cooperateActivityRepository = cooperateActivityRepository;
        this.userRepository = userRepository;
        this.activityLogRepository = activityLogRepository;
    }

//    private

    public Contract createContract(ContractDTO contractDTO, String token) {
        if ((contractDTO.getPartnerContactId() + contractDTO.getPartnerId() + contractDTO.getUetManId() +
                contractDTO.getUnitNameId()) != 0 ){
            UetMan uetMan = uetManRepository.findById(contractDTO.getUetManId());
            User user = userRepository.findByToken(token);
//            if (user.getRole().equals(Role.UNIT)){
//                UnitName unitName = user.getUnitName();
//            } else{
                UnitName unitName = unitNameRepository.findById(contractDTO.getUnitNameId());
//            }
            Partner partner = partnerRepository.findById(contractDTO.getPartnerId());
            PartnerContact partnerContact = partnerContactRepository.findById(contractDTO.getPartnerContactId());
//            TypeContract typeContract = typeContractRepository.findById(contractDTO.getTypeContractId());
            if (uetMan != null && unitName != null && partner != null && partnerContact != null){
                Contract contract = new Contract(partner, partnerContact, uetMan, unitName);
                String[] contentContract = contractDTO.getContentContract().split("<br />");
                Set<CooperateActivity> setCooperateActivity = new HashSet<>();
//                contract.setContentContract(contractDTO.getContentContract());
                contract.setFunding(contractDTO.getFunding());
                contract.setEndDate(contractDTO.getEndDate());
                contract.setNotice(contractDTO.getNotice());
                contract.setNumber(contractDTO.getNumber());
                contract.setOrdinaryNumber(contractDTO.getOrdinaryNumber());
                contract.setStartDate(contractDTO.getStartDate());
                contract.setRenew(contractDTO.getRenew());
                contract.setResult(contractDTO.getResult());

                Set<Contract> setContract = new HashSet<>();
                setContract.add(contract);


                partner.setContracts(setContract);
                partnerContact.setContract(setContract);
                unitName.setContract(setContract);
                uetMan.setContract(setContract);
//                typeContract.setContract(setContract);
                contractRepository.save(contract);

                partnerContactRepository.save(partnerContact);
                unitNameRepository.save(unitName);
                uetManRepository.save(uetMan);
                for(String content : contentContract){
                    CooperateActivity cooperateActivity = new CooperateActivity(content, partner, contract);
                    cooperateActivityRepository.save(cooperateActivity);
                    setCooperateActivity.add(cooperateActivity);
                }
                contract.setCooperateActivity(setCooperateActivity);
                partner.setCooperateActivities(setCooperateActivity);
                partnerRepository.save(partner);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("createContract");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " tạo Hợp đồng cho đối tác: " +
                            partner.getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLog.setContractId(contract.getId());
                    activityLogRepository.save(activityLog);
                }
                return contract;
            } else {
                throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
            }
        } else {
            throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
        }
    }

    public void editContract(ContractDTO contractDTO, int contractId, String token) {
        Contract contract = contractRepository.findById(contractId);
        if (contract != null) {
            if (contractDTO.getFunding() != 0) {
                contract.setFunding(contractDTO.getFunding());
            }
            if (contractDTO.getEndDate() != null){
                contract.setEndDate(contractDTO.getEndDate());
            }
            if (contractDTO.getNotice() != null) {
                contract.setNotice(contractDTO.getNotice());
            }
            if (contractDTO.getNumber() != 0) {
                contract.setNumber(contractDTO.getNumber());
            }
            if (contractDTO.getOrdinaryNumber() != 0) {
                contract.setOrdinaryNumber(contractDTO.getOrdinaryNumber());
            }
            if (contractDTO.getStartDate() != null) {
                contract.setStartDate(contractDTO.getStartDate());
            }
            if (contractDTO.getRenew() != null) {
                contract.setRenew(contractDTO.getRenew());
            }
            if (contractDTO.getResult() != null) {
                contract.setResult(contractDTO.getResult());
            }
            if (contractDTO.getCooperateActivityValue() != null){
                Partner partner = contract.getPartner();
                contract.setContentContract(contract.getContentContract() + "<br />" + contractDTO.getCooperateActivityValue());
                String[] cooperateActivity = contractDTO.getCooperateActivityValue().split("<br />");
                Set<CooperateActivity> setCooperateActivity = new HashSet<>();
                for(String content : cooperateActivity){
                    CooperateActivity cooperateActivity1 = new CooperateActivity(content, partner, contract);
                    cooperateActivityRepository.save(cooperateActivity1);
                    setCooperateActivity.add(cooperateActivity1);
                }
                contract.setCooperateActivity(setCooperateActivity);
                partner.setCooperateActivities(setCooperateActivity);
                partnerRepository.save(partner);
            }
            contractRepository.save(contract);
            User user = userRepository.findByToken(token);
            if(user.getRole().equals(Role.UNIT)){
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("editContract");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa Hợp đồng của đối tác: " +
                        contract.getPartner().getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLog.setContractId(contract.getId());
                activityLogRepository.save(activityLog);
            }
//            if (contractDTO.getUetManId() != 0){
//                uet
//                if ( != null){
//
//                }
//            }
            // hien tai chua viet sua partner, partner contact, uetman, unit man vi hoi luoi
        } else {
            throw new NullPointerException("Không tìm thấy hợp đồng này hãy thử lại!");
        }

    }

    public void deleteContract(int contractId, String token) {
        Contract contract = contractRepository.findById(contractId);
        if (contract != null){
            contractRepository.delete(contract);
            User user = userRepository.findByToken(token);
            if(user.getRole().equals(Role.UNIT)){
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("createContract");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa Hợp đồng của đối tác: " +
                        contract.getPartner().getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
//                activityLog.setContractId(contract.getId());
                activityLogRepository.save(activityLog);
            }
        } else {
            throw new NullPointerException("Không tìm thấy hợp đồng cần xoám hãy thử lại!");
        }
    }

    public List<Contract> getAllContract() {
        return (List<Contract>) contractRepository.findAll();
    }

    public CheckContractDTO checkContract(CheckContractDTO checkContractDTO) {
        int countPartner = 0;
        String contactId = "";
        if(checkContractDTO.getPartnerName() != null){
            PartnerInfo partnerInfo = partnerInfoRepository.findByPartnerNameContaining(checkContractDTO.getPartnerName());
            if(partnerInfo != null){
                checkContractDTO.setPartnerName(String.valueOf(partnerInfo.getPartner().getId()));
                Set<PartnerContact> partners = partnerInfo.getPartner().getPartnerContacts();
                for(PartnerContact partnerContact : partners){
                    if (checkContractDTO.getContactName().contains(partnerContact.getContactName())){
                        countPartner++;
                        contactId = String.valueOf(partnerContact.getId());
                    }
                }
                if(countPartner == 1){
                    checkContractDTO.setContactName(contactId);
                } else if(countPartner == 0){
                    checkContractDTO.setContactName("nf");
                } else{
                    for(PartnerContact partnerContact : partners){
                        if (checkContractDTO.getContactName().contains(partnerContact.getAbout())){
                            checkContractDTO.setContactName(String.valueOf(partnerContact.getId()));
                        }
                    }
                }
            } else {
                checkContractDTO.setPartnerName("nf");
                checkContractDTO.setContactName("nf");
            }
        } else {
            checkContractDTO.setPartnerName("nf");
            checkContractDTO.setContactName("nf");
        }
        if(checkContractDTO.getUetMan() != null){
            UetMan uetMan = uetManRepository.findByUetManNameContaining(checkContractDTO.getUetMan());
            if(uetMan != null){
                checkContractDTO.setUetMan(String.valueOf(uetMan.getId()));
            } else {
                checkContractDTO.setUetMan("nf");
            }
        } else{
            checkContractDTO.setUetMan("nf");
        }
        if(checkContractDTO.getUnitName() != null){
            UnitName unitName = unitNameRepository.findByUnitNameContaining(checkContractDTO.getUnitName());
            if(unitName != null){
                checkContractDTO.setUnitName(String.valueOf(unitName.getId()));
            } else {
                checkContractDTO.setUnitName("nf");
            }
        } else {
            checkContractDTO.setUnitName("nf");
        }
        return checkContractDTO;
    }

    public Set<ExcelContractDTO> importExcel(Set<ExcelContractDTO> List, String token) {
        Set<ExcelContractDTO> listContract = new HashSet<>();
        for(ExcelContractDTO contractDTO : List){
            if ((contractDTO.getPartnerContactId() + contractDTO.getPartnerId() + contractDTO.getUetManId() +
                    contractDTO.getUnitNameId()) != 0 ){
                UetMan uetMan = uetManRepository.findById(contractDTO.getUetManId());
                UnitName unitName = unitNameRepository.findById(contractDTO.getUnitNameId());
                Partner partner = partnerRepository.findById(contractDTO.getPartnerId());
                PartnerContact partnerContact = partnerContactRepository.findById(contractDTO.getPartnerContactId());
//                TypeContract typeContract = typeContractRepository.findById(contractDTO.getTypeContractId());
                if (uetMan != null && unitName != null && partner != null && partnerContact != null){
                    Contract contract = new Contract(partner, partnerContact, uetMan, unitName);
                    String[] contentContract = contractDTO.getContentContract().split("<br />");
                    Set<CooperateActivity> setCooperateActivity = new HashSet<>();

//                    contract.setContentContract(contractDTO.getContentContract());
                    contract.setFunding(contractDTO.getFunding());
                    contract.setEndDate(contractDTO.getEndDate());
                    contract.setNotice(contractDTO.getNotice());
                    contract.setNumber(contractDTO.getNumber());
                    contract.setOrdinaryNumber(contractDTO.getOrdinaryNumber());
                    contract.setStartDate(contractDTO.getStartDate());
                    contract.setRenew(contractDTO.getRenew());
                    contract.setResult(contractDTO.getResult());

                    Set<Contract> setContract = new HashSet<>();
                    setContract.add(contract);

                    partner.setContracts(setContract);
                    partnerContact.setContract(setContract);
                    unitName.setContract(setContract);
                    uetMan.setContract(setContract);
//                    typeContract.setContract(setContract);
                    contractRepository.save(contract);
                    partnerContactRepository.save(partnerContact);
                    unitNameRepository.save(unitName);
//                    typeContractRepository.save(typeContract);
                    uetManRepository.save(uetMan);
                    for(String content : contentContract){
                        CooperateActivity cooperateActivity = new CooperateActivity(content, partner, contract);
                        cooperateActivityRepository.save(cooperateActivity);
                        setCooperateActivity.add(cooperateActivity);
                    }
                    contract.setCooperateActivity(setCooperateActivity);
                    partner.setCooperateActivities(setCooperateActivity);
                    partnerRepository.save(partner);
                    User user = userRepository.findByToken(token);
                    if(user.getRole().equals(Role.UNIT)){
                        ActivityLog activityLog = new ActivityLog(user);
                        userRepository.save(user);
                        activityLog.setActivityType("createContract");
                        activityLog.setAcvtivity(user.getUnitName().getUnitName() + " tạo Hợp đồng cho đối tác: " +
                                partner.getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                        activityLog.setStatus("NEW");
                        activityLog.setContractId(contract.getId());
                        activityLogRepository.save(activityLog);
                    }
//                    return contract;
                } else {
                    listContract.add(contractDTO);
//                    throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
                }
            } else {
                listContract.add(contractDTO);
//                throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
            }
        }
        return listContract;
    }

    public void editCooperateActivity(CooperateActivityDTO cooperateActivityDTO, String token) {
        CooperateActivity cooperateActivity = cooperateActivityRepository.findById(cooperateActivityDTO.getId());
        if(cooperateActivity != null){
            if(cooperateActivityDTO.getCooperateActivity() != null){
                Contract contract = cooperateActivity.getContract();
                contract.setContentContract(contract.getContentContract() + "<br />" + cooperateActivityDTO.getCooperateActivity());
                cooperateActivity.setCooperateActivity(cooperateActivityDTO.getCooperateActivity());
                cooperateActivityRepository.save(cooperateActivity);
                User user = userRepository.findByToken(token);
                if(user.getRole().equals(Role.UNIT)){
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("editCooperateActivity");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa hoạt động hợp tác Hợp đồng của đối tác: " +
                            cooperateActivity.getPartner().getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLog.setContractId(contract.getId());
                    activityLogRepository.save(activityLog);
                }
            } else {
                throw new NullPointerException("Hoạt động hợp tác rỗng!");
            }
        } else {
            throw new NullPointerException("Không tìm thấy hoạt động hợp tác!");
        }
    }

    public void deleteCooperateActivity(int cooperateActivityId, String token) {
        CooperateActivity cooperateActivity = cooperateActivityRepository.findById(cooperateActivityId);
        if(cooperateActivity != null){
            cooperateActivityRepository.delete(cooperateActivity);
            User user = userRepository.findByToken(token);
            if(user.getRole().equals(Role.UNIT)){
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("deleteCooperateActivity");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa hoạt động hợp tác: " + cooperateActivity.getCooperateActivity() + " của Hợp đồng của đối tác: " +
                        cooperateActivity.getPartner().getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLog.setContractId(cooperateActivity.getContract().getId());
                activityLogRepository.save(activityLog);
            }
        } else {
            throw new NullPointerException("Không tìm thấy hoạt động hợp tác!");
        }
    }

    public void addCooperateActivity(CooperateActivityDTO cooperateActivityDTO, int contractId, String token) {
        Contract contract = contractRepository.findById(contractId);
        if(contract != null){
            Partner partner = contract.getPartner();
            String[] cooperateActivity = cooperateActivityDTO.getCooperateActivity().split("<br />");
            Set<CooperateActivity> setCooperateActivity = new HashSet<>();
            for(String content : cooperateActivity){
                CooperateActivity cooperateActivity1 = new CooperateActivity(content, partner, contract);
                cooperateActivityRepository.save(cooperateActivity1);
                setCooperateActivity.add(cooperateActivity1);
            }
            contract.setCooperateActivity(setCooperateActivity);
            partner.setCooperateActivities(setCooperateActivity);
            partnerRepository.save(partner);
            contractRepository.save(contract);
            User user = userRepository.findByToken(token);
            if(user.getRole().equals(Role.UNIT)){
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("addCooperateActivity");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " thêm hoạt động hợp tác: " + cooperateActivityDTO.getCooperateActivity() + " của Hợp đồng của đối tác: " +
                        contract.getPartner().getPartnerInfo().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLog.setContractId(contract.getId());
                activityLogRepository.save(activityLog);
            }
        } else {
            throw new NullPointerException("Không tìm thấy hợp đồng!");
        }
    }


    public Set<Contract> getContractOfUnit(int userId) {
        return userRepository.findById(userId).getUnitName().getContract();
    }
}
