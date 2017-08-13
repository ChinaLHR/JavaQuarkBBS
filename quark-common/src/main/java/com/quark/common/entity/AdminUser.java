package com.quark.common.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lhr on 17-7-31.
 */

@Entity
@Table(name = "quark_adminuser")
public class AdminUser implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;


    //是否可以使用,默认为１
    @Column(nullable = false)
    private Integer enable = 1;

    @JsonIgnore
    @JoinTable(name = "quark_adminuser_role",
            joinColumns = {@JoinColumn(name = "adminuser_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "Id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable;
    }
}
