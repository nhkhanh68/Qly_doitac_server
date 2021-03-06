package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "continent")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String continentName;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
//    @JsonIgnore
    private Set<Nation> nation;

    public Continent(){

    }

    public Continent(String continentName){
        this.continentName = continentName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }


    public Set<Nation> getNation() {
        return nation;
    }

    public void setNation(Set<Nation> nation) {
        this.nation = nation;
    }
}
