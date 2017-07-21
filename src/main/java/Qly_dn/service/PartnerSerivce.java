package Qly_dn.service;

import Qly_dn.DTO.PartnerContactDTO;
import Qly_dn.DTO.PartnerDTO;
import Qly_dn.DTO.PartnerInfoDTO;
import Qly_dn.model.*;
import Qly_dn.repository.*;
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
    PartnerContactRepository partnerContactRepository;

    private final
    UserRepository userRepository;

    private final
    ActivityLogRepository activityLogRepository;

    @Autowired
    public PartnerSerivce(NationRepository nationRepository, PartnerRepository partnerRepository,
                          PartnerContactRepository partnerContactRepository, UserRepository userRepository,
                          ActivityLogRepository activityLogRepository) {
        this.nationRepository = nationRepository;
        this.partnerRepository = partnerRepository;
        this.partnerContactRepository = partnerContactRepository;
        this.userRepository = userRepository;
        this.activityLogRepository = activityLogRepository;
    }

    public Partner createPartner(PartnerDTO partnerDTO, String token) {
        Nation nation = nationRepository.findOne(partnerDTO.getNationId());
        if (nation != null) {
            Partner partner = new Partner(nation);
            partner.setPartnerName(partnerDTO.getPartnerName());
            partnerRepository.save(partner);
            User user = userRepository.findByToken(token);
            if (user.getRole().equals(Role.UNIT)) {
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("createPartner");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " tạo Đối tác " +
                        partnerDTO.getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLogRepository.save(activityLog);
            }
            return partner;
        } else {
            throw new NullPointerException("Không tìm thấy Quốc gia!");
        }
    }

    public void deletePartner(int partnerId, String token) {
        Partner partner = partnerRepository.findById(partnerId);
        if (partner != null) {
            partnerRepository.delete(partner);
            User user = userRepository.findByToken(token);
            if (user.getRole().equals(Role.UNIT)) {
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("deletePartner");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa Đối tác " +
                        partner.getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLogRepository.save(activityLog);
            }
        } else {
            throw new NullPointerException("Không tìm thấy đối tác!");
        }
    }


    public Partner editPartnerInfo(PartnerInfoDTO partnerInfoDTO, String token) {
        Partner partner = partnerRepository.findById(partnerInfoDTO.getPartnerId());
        if (partner != null) {
            partner.setPartnerName(partnerInfoDTO.getPartnerName());
            partner.setTaxCode(partnerInfoDTO.getTaxCode());
            partner.setDirector(partnerInfoDTO.getDirector());
            partner.setFieldWork(partnerInfoDTO.getFieldWork());
            partner.setWebsite(partnerInfoDTO.getWebsite());
            partner.setAddress(partnerInfoDTO.getAddress());
            partner.setPhone(partnerInfoDTO.getPhone());
            partner.setFax(partnerInfoDTO.getFax());
            partner.setEmail(partnerInfoDTO.getEmail());
            partner.setDescription(partnerInfoDTO.getDescription());
            if (partnerInfoDTO.getNationId() != 0) {
                Nation nation = nationRepository.findOne(partnerInfoDTO.getNationId());
                if (nation != null) {
                    partner.setNation(nation);
//                    nation.set

                    partnerRepository.save(partner);
                } else {
                    throw new NullPointerException("Không tìm thấy Quóc gia! " + partnerInfoDTO.getNationId());
                }
            }
            partnerRepository.save(partner);
            User user = userRepository.findByToken(token);
            if (user.getRole().equals(Role.UNIT)) {
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("editPartnerInfo");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa thông tin Đối tác " +
                        partner.getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLogRepository.save(activityLog);
            }
            return partner;
        } else {
            throw new NullPointerException("Không tìm thấy Đối tác!");
        }
    }

    public Set<PartnerContact> createPartnerContact(int partnerId, PartnerContactDTO partnerContactDTO, String token) {
        Partner partner = partnerRepository.findById(partnerId);
        if (partner != null) {
            Set<PartnerContact> setPartnerContact = new HashSet<>();
            setPartnerContact.add(new PartnerContact(partnerContactDTO.getContactName(),
                    partnerContactDTO.getPhone(), partnerContactDTO.getEmail(), partnerContactDTO.getSkype(),
                    partnerContactDTO.getAbout(), partner));
            partner.setPartnerContacts(setPartnerContact);
            partnerRepository.save(partner);
            User user = userRepository.findByToken(token);
            if (user.getRole().equals(Role.UNIT)) {
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("createPartnerContact");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " tạo liên hệ cho Đối tác " +
                        partner.getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLogRepository.save(activityLog);
            }
            return setPartnerContact;
        } else {
            throw new NullPointerException("Không tìm thấy Đối tác!");
        }
    }

    public void editPartnerContact(PartnerContactDTO partnerContactDTO, String token) {
        PartnerContact partnerContact = partnerContactRepository.findById(partnerContactDTO.getId());
        if (partnerContact != null) {
            partnerContact.setContactName(partnerContactDTO.getContactName());
            partnerContact.setEmail(partnerContactDTO.getEmail());
            partnerContact.setSkype(partnerContactDTO.getSkype());
            partnerContact.setAbout(partnerContactDTO.getAbout());
            partnerContact.setPhone(partnerContactDTO.getPhone());
            partnerContactRepository.save(partnerContact);
            User user = userRepository.findByToken(token);
            if (user.getRole().equals(Role.UNIT)) {
                ActivityLog activityLog = new ActivityLog(user);
                userRepository.save(user);
                activityLog.setActivityType("editPartnerInfo");
                activityLog.setAcvtivity(user.getUnitName().getUnitName() + " sửa liên hệ: " + partnerContact.getContactName() + " của Đối tác " +
                        partnerContact.getPartner().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                activityLog.setStatus("NEW");
                activityLogRepository.save(activityLog);
            }
        } else {
            throw new NullPointerException("Không tìm thấy Đối Tác");
        }
    }

    public void deletePartnerContact(int contactId, String token) {
        PartnerContact partnerContact = partnerContactRepository.findById(contactId);
        if (partnerContact != null) {
            if (partnerContact.getContract().isEmpty()) {
                partnerContactRepository.delete(contactId);
                User user = userRepository.findByToken(token);
                if (user.getRole().equals(Role.UNIT)) {
                    ActivityLog activityLog = new ActivityLog(user);
                    userRepository.save(user);
                    activityLog.setActivityType("deletePartnerContact");
                    activityLog.setAcvtivity(user.getUnitName().getUnitName() + " xóa liên hệ: " + partnerContact.getContactName() + " của Đối tác " +
                            partnerContact.getPartner().getPartnerName() + " vào lúc " + activityLog.getTimestamp());
                    activityLog.setStatus("NEW");
                    activityLogRepository.save(activityLog);
                }
            } else {
                throw new NullPointerException("Không thể xóa liên hệ này vì có 1 số hợp đồng được kí bởi liên hệ này!");
            }
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
