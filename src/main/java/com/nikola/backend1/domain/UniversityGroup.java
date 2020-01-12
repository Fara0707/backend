package com.nikola.backend1.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "UniversityGroups")
public class UniversityGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "groupName", length = 10)
    private String groupName; //5144m, 2141ст

    public UniversityGroup() {
    }

    public UniversityGroup(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public UniversityGroup setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public UniversityGroup setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
