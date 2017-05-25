package com.kevin.domain;

import javax.persistence.*;

/**
 * Created by min on 2017/5/25.
 */
@Entity
public class CGroup {
    @Id
    @GeneratedValue
    private Integer id;

    private String text;

    private String groupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CGroup(){

    }
}
