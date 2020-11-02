package com.nextgate.assesment.datatypes;

import java.util.Date;

public class Singer {

    private String name;
    private Date dateOfBirth;
    private String sex;
    private String company;

    // Constructor
    public Singer(String name, Date dateOfBirth, String sex, String company){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
