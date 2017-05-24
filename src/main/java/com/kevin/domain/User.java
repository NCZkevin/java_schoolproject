package com.kevin.domain;

import javafx.beans.DefaultProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by min on 2017/5/23.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="student_num", referencedColumnName="other_column_for_fk")
//    private Person person;
    private Long username;
    private String password;
    private Timestamp lastLoginTime;
    private Integer loginCount;
    private Integer permission = 0; // 0为普通用户，1为管理员
    private Integer verify = 0; // 0为未审核，1为审核通过，2为被禁用，-1为审核未通过

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp  lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public User(){

    }


}
