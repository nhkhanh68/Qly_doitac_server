package Qly_dn.DTO;

import Qly_dn.model.Contract;

import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
public class UetManDTO {
    private int id;
    private String uetManName;
    private Set<Contract> contract;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUetManName() {
        return uetManName;
    }

    public void setUetManName(String uetManName) {
        this.uetManName = uetManName;
    }

    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}
