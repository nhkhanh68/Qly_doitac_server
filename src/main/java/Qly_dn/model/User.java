package Qly_dn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import Qly_dn.model.Role;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Entity
@Table(name = "user")
public class User {
    private int id;
    private String userName;
    @JsonIgnore
    private String password;
    private String token;

    private Role role;

    @JsonIgnore
    private UnitName unitName;
    @JsonIgnore
    private Set<ActivityLog> activityLog;

    public User(){

    }

    public User(UnitName unitName){
        this.unitName = unitName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @OneToOne(mappedBy = "user")
    public UnitName getUnitName() {
        return unitName;
    }

    public void setUnitName(UnitName unitName) {
        this.unitName = unitName;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(Set<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
