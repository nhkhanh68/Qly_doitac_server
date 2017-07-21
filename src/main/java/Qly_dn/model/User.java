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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;

    @JsonIgnore
    private String password;
    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private UnitName unitName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ActivityLog> activityLog;

    public User(){
    }

    public User(UnitName unitName){
        this.unitName = unitName;
    }


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


    public UnitName getUnitName() {
        return unitName;
    }

    public void setUnitName(UnitName unitName) {
        this.unitName = unitName;
    }


    public Set<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(Set<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
