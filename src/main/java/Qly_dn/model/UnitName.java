package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "unit_name")
public class UnitName {
    private int id;
    private String unitName;
    @JsonIgnore
    private Set<Contract> contract;

    public UnitName(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "unitName", cascade = CascadeType.ALL)
    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }
}