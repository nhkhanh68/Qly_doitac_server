package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by nhkha on 13/04/2017.
 */
@Entity
public class CooperateActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "cooperateActivity", length  = 2800000)
    private String cooperateActivity;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonIgnore
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonIgnore
    private Partner partner;

    public CooperateActivity(){

    }

    public CooperateActivity(String cooperateActivity, Partner partner, Contract contract){
        this.cooperateActivity = cooperateActivity;
        this.partner = partner;
        this.contract = contract;
    }


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


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
