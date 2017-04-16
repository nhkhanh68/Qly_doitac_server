package Qly_dn.controller;

import Qly_dn.DTO.CheckContractDTO;
import Qly_dn.DTO.ContractDTO;
import Qly_dn.DTO.CooperateActivityDTO;
import Qly_dn.DTO.ExcelContractDTO;
import Qly_dn.model.Contract;
import Qly_dn.service.ContractService;
import Qly_dn.stereotype.RequiedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 27/03/2017.
 */
@RestController
public class ContractController {
    private final
    ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    //create  contract
    @RequestMapping(value = "contract/create", method = RequestMethod.POST)
    public Contract createContract(@RequestBody ContractDTO contractDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return contractService.createContract(contractDTO, token);
    }

    //edit contract
    @RequestMapping(value = "contract/{contractId}/edit", method = RequestMethod.PUT)
    public void editContract(@PathVariable("contractId") int contractId, @RequestBody ContractDTO contractDTO,
                             HttpServletRequest request){
        String token = request.getHeader("auth-token");
        contractService.editContract(contractDTO, contractId, token);
    }

    //delete contract
    @RequestMapping(value = "contract/{contractId}/delete", method = RequestMethod.DELETE)
    public void deleteContract(@PathVariable("contractId") int contractId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        contractService.deleteContract(contractId, token);
    }

    //show all contract
    @RequiedToken
    @RequestMapping(value = "contract", method = RequestMethod.GET)
    public List<Contract> getAllContract(){
        return contractService.getAllContract();
    }

    //check contract
    @RequestMapping(value = "checkContract", method = RequestMethod.POST)
    public CheckContractDTO checkContract(@RequestBody CheckContractDTO checkContractDTO){
         return contractService.checkContract(checkContractDTO);
    }

    //import excel
    @RequestMapping(value = "contract/excel", method = RequestMethod.POST)
    public Set<ExcelContractDTO> importExcel(@RequestBody Set<ExcelContractDTO> list, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return contractService.importExcel(list, token);
    }

    //edit cooperate activity
    @RequestMapping(value = "cooperateActivity/edit", method = RequestMethod.PUT)
    public void editCooperateActivity(@RequestBody CooperateActivityDTO cooperateActivityDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        contractService.editCooperateActivity(cooperateActivityDTO, token);
    }

    //delete cooperate activity
    @RequestMapping(value = "cooperateActivity/{cooperateActivityId}/delete", method = RequestMethod.DELETE)
    public void deleteCooperateActivity(@PathVariable("cooperateActivityId") int cooperateActivityId,
                                        HttpServletRequest request){
        String token = request.getHeader("auth-token");
        contractService.deleteCooperateActivity(cooperateActivityId, token);
    }

    //add cooperate activity
    @RequestMapping(value = "contract/{contractId}/cooperateActivity/add", method = RequestMethod.POST)
    public void addCooperateActivity(@RequestBody CooperateActivityDTO cooperateActivityDTO,
                                     @PathVariable("contractId") int contractId,
                                     HttpServletRequest request){
        String token = request.getHeader("auth-token");
        contractService.addCooperateActivity(cooperateActivityDTO, contractId, token);
    }

    //get all contract of unit
    @RequestMapping(value = "unit/{userId}/contract", method = RequestMethod.GET)
        public Set<Contract> getContractOfUnit(@PathVariable("userId") int userId){
            return contractService.getContractOfUnit(userId);
        }

}
