package Qly_dn.DTO;

import Qly_dn.model.Contract;
import Qly_dn.model.Nation;
import Qly_dn.model.PartnerContact;
import Qly_dn.model.PartnerInfo;

import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
public class PartnerDTO {
    private int id;
    private Nation nation;
    private PartnerInfo partnerInfo;
    private Set<PartnerContact> partnerContacts;
    private Set<Contract> contracts;
    private int nationId;
    private String partnerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public Set<PartnerContact> getPartnerContacts() {
        return partnerContacts;
    }

    public void setPartnerContacts(Set<PartnerContact> partnerContacts) {
        this.partnerContacts = partnerContacts;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}
