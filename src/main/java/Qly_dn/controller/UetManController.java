package Qly_dn.controller;

import Qly_dn.DTO.UetManDTO;
import Qly_dn.model.UetMan;
import Qly_dn.service.UetManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public UetMan createUnit(@RequestBody UetManDTO uetManDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return uetManService.createUetMan(uetManDTO, token);
    }

    //edit Unit
    //edit uet man
    @RequestMapping(value = "uetMan/edit", method = RequestMethod.PUT)
    public void editTypeContract(@RequestBody UetManDTO uetManDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        uetManService.editUetMan(uetManDTO, token);
    }

    //delete unit
    @RequestMapping(value = "uetMan/{uetManId}/delete", method = RequestMethod.DELETE)
    public void deleteUnit(@PathVariable("uetManId") int uetManId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        uetManService.deleteUetMan(uetManId, token);
    }

    //show all uet man
    @RequestMapping(value = "uetMan", method = RequestMethod.GET)
    public List<UetMan> getAll(){
        return uetManService.getAll();
    }
}
