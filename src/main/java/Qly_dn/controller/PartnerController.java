package Qly_dn.controller;

import Qly_dn.DTO.PartnerContactDTO;
import Qly_dn.DTO.PartnerDTO;
import Qly_dn.DTO.PartnerInfoDTO;
import Qly_dn.model.Partner;
import Qly_dn.model.PartnerContact;
import Qly_dn.service.PartnerSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 26/03/2017.
 */
@RestController
public class PartnerController {
    private final
    PartnerSerivce partnerSerivce;

    @Autowired
    public PartnerController(PartnerSerivce partnerSerivce) {
        this.partnerSerivce = partnerSerivce;
    }

    //create partner
    @RequestMapping(value = "partner/create", method = RequestMethod.POST)
    public Partner createPartner(@RequestBody PartnerDTO partnerDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerSerivce.createPartner(partnerDTO, token);
    }

    //delete partner
    @RequestMapping(value = "partner/{partnerId}/delete", method = RequestMethod.DELETE)
    public void deleteParner(@PathVariable("partnerId") int partnerId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        partnerSerivce.deletePartner(partnerId, token);
    }

    //edit partner info and nation
    @RequestMapping(value = "partner/edit", method = RequestMethod.PUT)
    public Partner editPartnerInfo(@RequestBody PartnerInfoDTO partnerInfoDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerSerivce.editPartnerInfo(partnerInfoDTO, token);
    }

    //create partner contact
    @RequestMapping(value = "partner/{partnerId}/contact/create", method = RequestMethod.POST)
    public Set<PartnerContact> createPartnerContact(@PathVariable("partnerId") int partnerId,
                                                    @RequestBody PartnerContactDTO partnerContactDTO,
                                                    HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return partnerSerivce.createPartnerContact(partnerId, partnerContactDTO, token);
    }

    //edit partner contact
    @RequestMapping(value = "partner/contact/edit", method = RequestMethod.PUT)
    public void editPartnerContact(@RequestBody PartnerContactDTO partnerContactDTO, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        partnerSerivce.editPartnerContact(partnerContactDTO, token);
    }

    //delete partner contact
    @RequestMapping(value = "partner/contact/{contactId}/delete", method = RequestMethod.DELETE)
    public void deletePartnerContact(@PathVariable("contactId") int contactId, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        partnerSerivce.deletePartnerContact(contactId, token);
    }

    //show all partner
    @RequestMapping(value = "partner", method = RequestMethod.GET)
    public List<Partner> showAllPartner(){
        return partnerSerivce.showAllPartner();
    }

    //show one partner
    @RequestMapping(value = "partner/{partnerId}", method = RequestMethod.GET)
    public Partner showAllPartner(@PathVariable("partnerId") int partnerId){
        return partnerSerivce.showPartner(partnerId);
    }

    //show all contacts of partner
    @RequestMapping(value = "partner/{partnerId}/contact", method = RequestMethod.GET)
    public Set<PartnerContact> showAllPartnerContact(@PathVariable("partnerId") int partnerId){
        return partnerSerivce.showAllPartnerContact(partnerId);
    }
}
