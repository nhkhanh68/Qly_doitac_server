package Qly_dn.controller;

import Qly_dn.DTO.ContinentDTO;
import Qly_dn.DTO.NationDTO;
import Qly_dn.model.Continent;
import Qly_dn.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@RestController
public class NationController {
    private final
    NationService nationService;

    @Autowired
    public NationController(NationService nationService) {
        this.nationService = nationService;
    }
//    @RequestMapping(value="/partner/{partnerId}/post",method = RequestMethod.POST)
//    public Post createPost(@PathVariable("partnerId") int partnerId, @RequestBody PostDTO postDTO, HttpServletRequest request) throws IOException {
//        String token = request.getHeader("auth-token");
//        return postService.createPost(partnerId, postDTO, token);
//    }
    //create continent
    @RequestMapping(value = "/continent/create", method = RequestMethod.POST)
    public Continent createContinent(@RequestBody ContinentDTO continentDTO){
        return nationService.createContinent(continentDTO);
    }

    //create nation
    @RequestMapping(value = "/continent/{continentId}/nation/create", method = RequestMethod.POST)
    public void createNation(@RequestBody List<NationDTO> List, @PathVariable("continentId") int continentId){
        nationService.createNation(List, continentId);
    }

    //show all continent
    @RequestMapping(value = "/continent", method = RequestMethod.GET)
    public List<Continent> getAllContinent(){
        return nationService.getAllContinent();
    }

    //show all nation of continent
    // continent/{continentId}

    //delele nation
    @RequestMapping(value = "/nation/{nationId}/delete", method = RequestMethod.DELETE)
    public void deleteNation(@PathVariable("nationId") int nationId){
        nationService.deleteNation(nationId);
    }
}