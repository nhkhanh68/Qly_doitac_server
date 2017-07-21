package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "uet_man")
public class UetMan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String uetManName;

    @OneToMany(mappedBy = "uetMan")
    @JsonIgnore
    private Set<Contract> contract;

    public UetMan(){

    }

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
