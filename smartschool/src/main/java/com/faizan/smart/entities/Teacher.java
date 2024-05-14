package com.faizan.smart.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Setter
//@Getter
//@ToString
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teacher_id;
    @NotNull
    private String name;
    @NotNull
    private String education;
    @NotNull
    private int experience;
    @Column(name="phone_number")
    private String  phoneNumber;
    private String address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;


    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public String getName() {
        return name;
    }

    public String getEducation() {
        return education;
    }

    public int getExperience() {
        return experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", education='" + education + '\'' +
                ", experience=" + experience +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

}
