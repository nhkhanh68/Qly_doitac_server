package Qly_dn.controller;

import Qly_dn.DTO.ContractDTO;
import Qly_dn.model.Contract;
import Qly_dn.service.ContractService;
import Qly_dn.stereotype.RequiedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
