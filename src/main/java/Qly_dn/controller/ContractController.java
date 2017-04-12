package Qly_dn.controller;

import Qly_dn.DTO.CheckContractDTO;
import Qly_dn.DTO.ContractDTO;
import Qly_dn.DTO.ExcelContractDTO;
import Qly_dn.model.Contract;
import Qly_dn.service.ContractService;
import Qly_dn.stereotype.RequiedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Contract createContract(@RequestBody ContractDTO contractDTO){
        return contractService.createContract(contractDTO);
    }

    //edit contract
    @RequestMapping(value = "contract/{contractId}/edit", method = RequestMethod.PUT)
    public void editContract(@PathVariable("contractId") int contractId, @RequestBody ContractDTO contractDTO){
        contractService.editContract(contractDTO, contractId);
    }

    //delete contract
    @RequestMapping(value = "contract/{contractId}/delete", method = RequestMethod.DELETE)
    public void deleteContract(@PathVariable("contractId") int contractId){
        contractService.delete(contractId);
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
    public Set<ExcelContractDTO> importExcel(@RequestBody Set<ExcelContractDTO> list){
        return contractService.importExcel(list);
    }
}
