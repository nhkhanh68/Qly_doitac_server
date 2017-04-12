package Qly_dn.service;

import Qly_dn.DTO.CheckContractDTO;
import Qly_dn.DTO.ContractDTO;
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

    @Autowired
    public ContractService(ContractRepository contractRepository, UetManRepository uetManRepository, UnitNameRepository unitNameRepository, PartnerContactRepository partnerContactRepository, PartnerRepository partnerRepository, TypeContractRepository typeContractRepository, PartnerInfoRepository partnerInfoRepository) {
        this.contractRepository = contractRepository;
        this.uetManRepository = uetManRepository;
        this.unitNameRepository = unitNameRepository;
        this.partnerContactRepository = partnerContactRepository;
        this.partnerRepository = partnerRepository;
        this.typeContractRepository = typeContractRepository;
        this.partnerInfoRepository = partnerInfoRepository;
    }

//    private

    public Contract createContract(ContractDTO contractDTO) {
        if ((contractDTO.getPartnerContactId() + contractDTO.getPartnerId() + contractDTO.getUetManId() +
                contractDTO.getUnitNameId()) != 0 ){
            UetMan uetMan = uetManRepository.findById(contractDTO.getUetManId());
            UnitName unitName = unitNameRepository.findById(contractDTO.getUnitNameId());
            Partner partner = partnerRepository.findById(contractDTO.getPartnerId());
            PartnerContact partnerContact = partnerContactRepository.findById(contractDTO.getPartnerContactId());
//            TypeContract typeContract = typeContractRepository.findById(contractDTO.getTypeContractId());
            if (uetMan != null && unitName != null && partner != null && partnerContact != null){
                Contract contract = new Contract(partner, partnerContact, uetMan, unitName);

                contract.setContentContract(contractDTO.getContentContract());
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

                partnerRepository.save(partner);
                partnerContactRepository.save(partnerContact);
                unitNameRepository.save(unitName);
                uetManRepository.save(uetMan);
                return contract;
            } else {
                throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
            }
        } else {
            throw new NullPointerException("Có lỗi xảy ra, kiểm tra lại các trường trong hợp đồng!");
        }
    }

    public void editContract(ContractDTO contractDTO, int contractId) {
        Contract contract = contractRepository.findById(contractId);
        if (contract != null) {
            if (!contractDTO.getContentContract().equals("")) {
                contract.setContentContract(contractDTO.getContentContract());
            }
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
            contractRepository.save(contract);
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

    public void delete(int contractId) {
        Contract contract = contractRepository.findById(contractId);
        if (contract != null){
            contractRepository.delete(contract);
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

    public Set<ExcelContractDTO> importExcel(Set<ExcelContractDTO> List) {
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

                    contract.setContentContract(contractDTO.getContentContract());
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

                    partnerRepository.save(partner);
                    partnerContactRepository.save(partnerContact);
                    unitNameRepository.save(unitName);
//                    typeContractRepository.save(typeContract);
                    uetManRepository.save(uetMan);
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
}
