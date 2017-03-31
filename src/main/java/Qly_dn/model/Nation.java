package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "nation")
public class Nation {
    private int id;
    private String nationName;
    @JsonIgnore
    private Continent continent;
    @JsonIgnore
    private Set<Partner> partner;

    public Nation(){

    }

    public Nation(String nationName){
        this.nationName = nationName;
    }

    public Nation(String nationName, Continent continent){
        this.nationName = nationName;
        this.continent = continent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    @ManyToOne
    @JoinColumn(name = "continent_id")
    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @OneToMany(mappedBy = "nation", cascade = CascadeType.ALL)
    public Set<Partner> getPartner() {
        return partner;
    }

    public void setPartner(Set<Partner> partner) {
        this.partner = partner;
    }
}
