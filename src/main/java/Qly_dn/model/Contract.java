package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "contract")
public class Contract {
    private int id;
    private String result;
    private Integer renew;
    private String notice;
    private float funding;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private int ordinaryNumber;
    private int number;
    private Partner partner;
    private TypeContract typeContract;
    private PartnerContact partnerContact;
    private UetMan uetMan;
    private UnitName unitName;
    private String contentContract;

    public Contract(){

    }

    public Contract(Partner partner, TypeContract typeContract, PartnerContact partnerContact, UetMan uetMan, UnitName unitName){
        this.partner = partner;
        this.typeContract = typeContract;
        this.partnerContact = partnerContact;
        this.uetMan = uetMan;
        this.unitName = unitName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRenew() {
        return renew;
    }

    public void setRenew(Integer renew) {
        this.renew = renew;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public float getFunding() {
        return funding;
    }

    public void setFunding(float funding) {
        this.funding = funding;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOrdinaryNumber() {
        return ordinaryNumber;
    }

    public void setOrdinaryNumber(int ordinaryNumber) {
        this.ordinaryNumber = ordinaryNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "partner_id")
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @ManyToOne
    @JoinColumn(name = "type_contract_id")
    public TypeContract getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(TypeContract typeContract) {
        this.typeContract = typeContract;
    }

    @ManyToOne
    @JoinColumn(name = "uet_man_id")
    public UetMan getUetMan() {
        return uetMan;
    }

    public void setUetMan(UetMan uetMan) {
        this.uetMan = uetMan;
    }

    @ManyToOne
    @JoinColumn(name = "unit_name_id")
    public UnitName getUnitName() {
        return unitName;
    }

    public void setUnitName(UnitName unitName) {
        this.unitName = unitName;
    }

    public String getContentContract() {
        return contentContract;
    }

    public void setContentContract(String contentContract) {
        this.contentContract = contentContract;
    }

    @ManyToOne
    @JoinColumn(name = "partner_contact_id")
    public PartnerContact getPartnerContact() {
        return partnerContact;
    }

    public void setPartnerContact(PartnerContact partnerContact) {
        this.partnerContact = partnerContact;
    }
}
