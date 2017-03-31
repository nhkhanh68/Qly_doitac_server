package Qly_dn.DTO;

import Qly_dn.model.Contract;

import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
public class TypeContactDTO {
    private int id;
    private String typeContract;
    private Set<Contract> contract;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(String typeContract) {
        this.typeContract = typeContract;
    }

    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}
