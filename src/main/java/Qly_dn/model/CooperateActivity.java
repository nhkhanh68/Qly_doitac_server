package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by nhkha on 13/04/2017.
 */
@Entity
public class CooperateActivity {
    private int id;
    private String cooperateActivity;
    @JsonIgnore
    private Contract contract;
    @JsonIgnore
    private Partner partner;

    public CooperateActivity(){

    }

    public CooperateActivity(String cooperateActivity, Partner partner, Contract contract){
        this.cooperateActivity = cooperateActivity;
        this.partner = partner;
        this.contract = contract;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCooperateActivity() {
        return cooperateActivity;
    }

    public void setCooperateActivity(String cooperateActivity) {
        this.cooperateActivity = cooperateActivity;
    }

    @ManyToOne
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @ManyToOne
    @JoinColumn(name = "partner_id")
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
