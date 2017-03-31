package Qly_dn.service;

import Qly_dn.DTO.PartnerContactDTO;
import Qly_dn.DTO.PartnerDTO;
import Qly_dn.DTO.PartnerInfoDTO;
import Qly_dn.model.Nation;
import Qly_dn.model.Partner;
import Qly_dn.model.PartnerContact;
import Qly_dn.model.PartnerInfo;
import Qly_dn.repository.NationRepository;
import Qly_dn.repository.PartnerContactRepository;
import Qly_dn.repository.PartnerInfoRepository;
import Qly_dn.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 26/03/2017.
 */
@Service
public class PartnerSerivce {
    private final
    NationRepository nationRepository;

    private final
    PartnerRepository partnerRepository;

    private final
    PartnerInfoRepository partnerInfoRepository;

    private final
    PartnerContactRepository partnerContactRepository;

    @Autowired
    public PartnerSerivce(NationRepository nationRepository, PartnerRepository partnerRepository, PartnerInfoRepository partnerInfoRepository, PartnerContactRepository partnerContactRepository) {
        this.nationRepository = nationRepository;
        this.partnerRepository = partnerRepository;
        this.partnerInfoRepository = partnerInfoRepository;
        this.partnerContactRepository = partnerContactRepository;
    }

    public Partner createPartner(PartnerDTO partnerDTO) {
        Nation nation = nationRepository.findOne(partnerDTO.getNationId());
        if(nation != null){
            Partner partner = new Partner(nation);
            partnerRepository.save(partner);
            PartnerInfo partnerInfo = new PartnerInfo(partner);
            partnerInfo.setPartnerName(partnerDTO.getPartnerName());
            partnerInfoRepository.save(partnerInfo);
            return partner;
        } else{
            throw new NullPointerException("Không tìm thấy Quốc gia!");
        }
    }

    public void deletePartner(int partnerId) {
        Partner partner = partnerRepository.findById(partnerId);
        if(partner != null){
            partnerInfoRepository.delete(partnerInfoRepository.findByPartnerId(partnerId));
            partnerRepository.delete(partner);
        } else {
            throw new NullPointerException("Không tìm thấy đối tác!");
        }
    }


    public Partner editPartnerInfo(PartnerInfoDTO partnerInfoDTO) {
        Partner partner = partnerRepository.findById(partnerInfoDTO.getPartnerId());
        if(partner != null){
            PartnerInfo partnerInfo = partner.getPartnerInfo();
            if (partnerInfoDTO.getPartnerName() != null){
                partnerInfo.setPartnerName(partnerInfoDTO.getPartnerName());
            }
            if (partnerInfoDTO.getTaxCode() != null){
                partnerInfo.setTaxCode(partnerInfoDTO.getTaxCode());
            }
            if (partnerInfoDTO.getDirector() != null){
                partnerInfo.setDirector(partnerInfoDTO.getDirector());
            }
            if (partnerInfoDTO.getFieldWork() != null){
                partnerInfo.setFieldWork(partnerInfoDTO.getFieldWork());
            }
            if (partnerInfoDTO.getWebsite() != null){
                partnerInfo.setWebsite(partnerInfoDTO.getWebsite());
            }
            if (partnerInfoDTO.getAddress() != null){
                partnerInfo.setAddress(partnerInfoDTO.getAddress());
            }
            if (partnerInfoDTO.getPhone() != null){
                partnerInfo.setPhone(partnerInfoDTO.getPhone());
            }
            if (partnerInfoDTO.getFax() != null){
                partnerInfo.setFax(partnerInfoDTO.getFax());
            }
            if (partnerInfoDTO.getEmail() != null){
                partnerInfo.setEmail(partnerInfoDTO.getEmail());
            }
            if (partnerInfoDTO.getNationId() != 0){
                Nation nation = nationRepository.findOne(partnerInfoDTO.getNationId());
                if(nation != null){
                    partner.setNation(nation);
//                    nation.set

                    partnerRepository.save(partner);
                } else {
                    throw new NullPointerException("Không tìm thấy Quóc gia! " + partnerInfoDTO.getNationId());
                }
            }
            partnerInfoRepository.save(partnerInfo);
            return partner;
        } else {
            throw new NullPointerException("Không tìm thấy Đối tác!");
        }
    }

    public Set<PartnerContact> createPartnerContact(int partnerId, PartnerContactDTO partnerContactDTO) {
        Partner partner = partnerRepository.findById(partnerId);
        if (partner != null){
            Set<PartnerContact> setPartnerContact = new HashSet<>();
            setPartnerContact.add(new PartnerContact(partnerContactDTO.getContactName(),
                partnerContactDTO.getPhone(), partnerContactDTO.getEmail(), partnerContactDTO.getSkype(),
                partnerContactDTO.getAbout(), partner));
            partner.setPartnerContacts(setPartnerContact);
            partnerRepository.save(partner);
            return setPartnerContact;
        } else {
            throw new NullPointerException("Không tìm thấy Đối tác!");
        }
    }

    public void editPartnerContact(PartnerContactDTO partnerContactDTO) {
        Partner partner = partnerRepository.findById(partnerContactDTO.getPartnerId());
        if (partner != null){
            PartnerContact partnerContact = partnerContactRepository.findById(partnerContactDTO.getId());
            partnerContact.setContactName(partnerContactDTO.getContactName());
            partnerContact.setEmail(partnerContactDTO.getEmail());
            partnerContact.setSkype(partnerContactDTO.getSkype());
            partnerContact.setAbout(partnerContactDTO.getAbout());
            partnerContact.setPhone(partnerContactDTO.getPhone());
            partnerContactRepository.save(partnerContact);
        } else {
            throw new NullPointerException("Không tìm thấy Đối Tác");
        }
    }

    public void deletePartnerContact(int contactId, int partnerId) {
        PartnerContact partnerContact = partnerContactRepository.findByIdAndPartnerId(contactId, partnerId);
        if (partnerContact != null){
            partnerContactRepository.delete(contactId);
        } else {
            throw new NullPointerException("Không tìm thấy Liên lạc cần xóa!");
        }
    }


    public List<Partner> showAllPartner() {
        return (List<Partner>) partnerRepository.findAll();
    }

    public Partner showPartner(int partnerId) {
        return partnerRepository.findById(partnerId);
    }

    public Set<PartnerContact> showAllPartnerContact(int partnerId) {
        return partnerRepository.findById(partnerId).getPartnerContacts();
    }
}
