package Qly_dn.DTO;

import Qly_dn.model.Contract;

import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
public class UnitNameDTO {
    private int id;
    private String unitName;
    private Set<Contract> contract;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}
