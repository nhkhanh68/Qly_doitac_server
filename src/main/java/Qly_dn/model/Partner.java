package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "partner")
public class Partner {
    private int id;
//    @JsonIgnore
    private Nation nation;
    private PartnerInfo partnerInfo;
//    @JsonIgnore
    private Set<PartnerContact> partnerContacts;
    @JsonIgnore
    private Set<Contract> contracts;

    public Partner(){

    }

    public Partner(Nation nation){
        this.nation = nation;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "nation_id")
    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    @OneToOne(mappedBy = "partner")
    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    public Set<PartnerContact> getPartnerContacts() {
        return partnerContacts;
    }

    public void setPartnerContacts(Set<PartnerContact> partnerContacts) {
        this.partnerContacts = partnerContacts;
    }

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
