package Qly_dn.controller;

import Qly_dn.DTO.UetManDTO;
import Qly_dn.model.UetMan;
import Qly_dn.service.UetManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nhkha on 26/03/2017.
 */
@RestController
public class UetManController {
    private final
    UetManService uetManService;

    @Autowired
    public UetManController(UetManService uetManService) {
        this.uetManService = uetManService;
    }

    //create uet man
    @RequestMapping(value = "uetMan/create", method = RequestMethod.POST)
    public UetMan createUnit(@RequestBody UetManDTO uetManDTO){
        return uetManService.createUetMan(uetManDTO);
    }

    //edit Unit
    //edit uet man
    @RequestMapping(value = "uetMan/edit", method = RequestMethod.PUT)
    public void editTypeContract(@RequestBody UetManDTO uetManDTO){
        uetManService.editUetMan(uetManDTO);
    }

    //delete unit
    @RequestMapping(value = "uetMan/{uetManId}/delete", method = RequestMethod.DELETE)
    public void deleteUnit(@PathVariable("uetManId") int uetManId){
        uetManService.deleteUetMan(uetManId);
    }

    //show all uet man
    @RequestMapping(value = "uetMan", method = RequestMethod.GET)
    public List<UetMan> getAll(){
        return uetManService.getAll();
    }
}
