package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

//    @ManyToOne
//    @JoinColumn(name = "type_contract_id")
//    private TypeContract typeContract;

    @ManyToOne
    @JoinColumn(name = "partner_contact_id")
    private PartnerContact partnerContact;

    @ManyToOne
    @JoinColumn(name = "uet_man_id")
    private UetMan uetMan;

    @ManyToOne
    @JoinColumn(name = "unit_name_id")
    private UnitName unitName;

    @Column(name = "contentContract", length  = 2800000)
    private String contentContract;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<CooperateActivity> cooperateActivity;

    public Contract(){

    }

    public Contract(Partner partner, PartnerContact partnerContact, UetMan uetMan, UnitName unitName){
        this.partner = partner;
//        this.typeContract = typeContract;
        this.partnerContact = partnerContact;
        this.uetMan = uetMan;
        this.unitName = unitName;
    }


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


    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }


//    public TypeContract getTypeContract() {
//        return typeContract;
//    }
//
//    public void setTypeContract(TypeContract typeContract) {
//        this.typeContract = typeContract;
//    }


    public UetMan getUetMan() {
        return uetMan;
    }

    public void setUetMan(UetMan uetMan) {
        this.uetMan = uetMan;
    }


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


    public PartnerContact getPartnerContact() {
        return partnerContact;
    }

    public void setPartnerContact(PartnerContact partnerContact) {
        this.partnerContact = partnerContact;
    }


    public Set<CooperateActivity> getCooperateActivity() {
        return cooperateActivity;
    }

    public void setCooperateActivity(Set<CooperateActivity> cooperateActivity) {
        this.cooperateActivity = cooperateActivity;
    }
}
