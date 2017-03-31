package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "partner_info")
public class PartnerInfo {
    private int id;
    private String partnerName;
    private String taxCode;
    private String director;
    private String fieldWork;
    private String website;
    private String address;
    private String phone;
    private String email;
    private String description;
    private String fax;
    @JsonIgnore
    private Partner partner;

    public PartnerInfo(){

    }

    public PartnerInfo(Partner partner){
        this.partner = partner;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFieldWork() {
        return fieldWork;
    }

    public void setFieldWork(String fieldWork) {
        this.fieldWork = fieldWork;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
