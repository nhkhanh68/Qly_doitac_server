package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "type_contract")
public class TypeContract {
    private int id;
    private String typeContract;
    @JsonIgnore
    private Set<Contract> contract;

    public TypeContract(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "typeContract", cascade = CascadeType.ALL)
    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}
