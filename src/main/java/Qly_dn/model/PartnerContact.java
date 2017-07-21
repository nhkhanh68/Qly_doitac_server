package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "partner_contact")
public class PartnerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String contactName;
    private String phone;
    private String email;
    private String skype;
    private String about;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonIgnore
    private Partner partner;

    @OneToMany(mappedBy = "partnerContact")
    @JsonIgnore
    private Set<Contract> contract;

    public PartnerContact(){

    }

    public PartnerContact(String contactName, String phone, String email, String skype, String about, Partner partner){
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
        this.about = about;
        this.partner = partner;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }


    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}
