package Qly_dn.DTO;

import java.sql.Date;

/**
 * Created by nhkha on 10/04/2017.
 */
public class ExcelContractDTO {
    private int id;
    private String result;
    private Integer renew;
    private String notice;
    private float funding;
    private Date startDate;
    private Date endDate;
    private int ordinaryNumber;
    private int number;
    private String contentContract;
    private int partnerContactId;
    private int partnerId;
    private int uetManId;
    private int typeContractId;
    private int unitNameId;
    private int STT;

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

    public String getContentContract() {
        return contentContract;
    }

    public void setContentContract(String contentContract) {
        this.contentContract = contentContract;
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
}
