package Qly_dn.controller;

import Qly_dn.DTO.TypeContactDTO;
import Qly_dn.model.Contract;
import Qly_dn.model.TypeContract;
import Qly_dn.service.TypeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by nhkha on 26/03/2017.
 */
@RestController
public class TypeContractController {
    private final
    TypeContractService typeContractService;

    @Autowired
    public TypeContractController(TypeContractService typeContractService) {
        this.typeContractService = typeContractService;
    }

    //create type contract
    @RequestMapping(value = "typeContract/create", method = RequestMethod.POST)
    public TypeContract createTypeContract(@RequestBody TypeContactDTO typeContactDTO){
        return typeContractService.createTypeContract(typeContactDTO);
    }

    //edit type contract
    @RequestMapping(value = "typeContract/edit", method = RequestMethod.PUT)
    public TypeContract editTypeContract(@RequestBody TypeContactDTO typeContactDTO){
        return typeContractService.editTypeContract(typeContactDTO);
    }

    //delete type contract
    @RequestMapping(value = "typeContract/{typeContractId}/delete", method = RequestMethod.DELETE)
    public void deleteTypeContract(@PathVariable("typeContractId") int typeContractId){
        typeContractService.deleteTypeContract(typeContractId);
    }

    //show all type contract
    @RequestMapping(value = "typeContract", method = RequestMethod.GET)
    public List<TypeContract> getAll(){
        return typeContractService.getAll();
    }
}
