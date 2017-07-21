package Qly_dn.DTO;

import Qly_dn.model.*;

import java.sql.Date;

/**
 * Created by nhkha on 25/03/2017.
 */
public class ContractDTO {
    private int id;
    private String result;
    private Integer renew;
    private String notice;
    private float funding;
    private Date startDate;
    private Date endDate;
    private int ordinaryNumber;
    private int number;
    private Partner partner;
    private PartnerContact partnerContact;
    private UetMan uetMan;
    private UnitName unitName;
    private String contentContract;
    private int partnerContactId;
    private int partnerId;
    private int uetManId;
    private int typeContractId;
    private int unitNameId;
    private int STT;
    private String cooperateActivityValue;

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

    public int getPartnerContactId() {
        return partnerContactId;
    }

    public void setPartnerContactId(int partnerContactId) {
        this.partnerContactId = partnerContactId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getUetManId() {
        return uetManId;
    }

    public void setUetManId(int uetManId) {
        this.uetManId = uetManId;
    }

    public int getTypeContractId() {
        return typeContractId;
    }

    public void setTypeContractId(int typeContractId) {
        this.typeContractId = typeContractId;
    }

    public int getUnitNameId() {
        return unitNameId;
    }

    public void setUnitNameId(int unitNameId) {
        this.unitNameId = unitNameId;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getCooperateActivityValue() {
        return cooperateActivityValue;
    }

    public void setCooperateActivityValue(String cooperateActivityValue) {
        this.cooperateActivityValue = cooperateActivityValue;
    }
}
