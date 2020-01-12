package com.nikola.backend1.domain;

import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name", length = 50)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "Group_id")
    private UniversityGroup Group_id;

    public Student() {
    }

    public Student(String name, UniversityGroup group_id) {
        Name = name;
        Group_id = group_id;
    }



    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public UniversityGroup getGroup_id() {
        return Group_id;
    }

    public String getGroup_id_name(){
        return Group_id.getGroupName();
    }

    public  Integer getGroup_id_id(){
        return Group_id.getId();
    }

//    public Student setGroup_id(UniversityGroup group_id) {
//        this.Group_id = group_id;
//        return this;
//    }
}
