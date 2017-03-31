package Qly_dn.controller;

import Qly_dn.DTO.UnitNameDTO;
import Qly_dn.model.Contract;
import Qly_dn.model.UnitName;
import Qly_dn.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nhkha on 26/03/2017.
 */
@RestController
public class UnitController {
    private final
    UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    //create Unit
    @RequestMapping(value = "unit/create", method = RequestMethod.POST)
    public UnitName createUnit(@RequestBody UnitNameDTO unitNameDTO){
        return unitService.createUnit(unitNameDTO);
    }

    //edit Unit
    //edit Unit
    @RequestMapping(value = "unit/edit", method = RequestMethod.PUT)
    public void editTypeContract(@RequestBody UnitNameDTO unitNameDTO){
        unitService.editUnit(unitNameDTO);
    }

    //delete unit
    @RequestMapping(value = "unit/{unitId}/delete", method = RequestMethod.DELETE)
    public void deleteUnit(@PathVariable("unitId") int unitId){
        unitService.deleteUnit(unitId);
    }

    //show all unit
    @RequestMapping(value = "unit", method = RequestMethod.GET)
    public List<UnitName> getAll(){
        return unitService.getAll();
    }
}
