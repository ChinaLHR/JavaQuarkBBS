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
@Table(name = "quark_role")
public class Role implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,nullable = false)
    private String name;

    //角色描述
    private String description;

    //是否持有角色标志
    @Transient
    private Integer selected;

    //角色与用户的关联关系
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<AdminUser> adminUsers = new HashSet<>();

    //角色与权限的关联关系
    @JsonIgnore
    @JoinTable(name = "quark_role_permission",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permissions_id",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AdminUser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(Set<AdminUser> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", selected=" + selected +
                '}';
    }
}
