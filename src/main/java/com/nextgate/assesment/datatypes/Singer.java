package com.nextgate.assesment.datatypes;

public class Singer {

    private String name;
    private String dateOfBirth;
    private String sex;
    private String company;

    // Constructor
    public Singer(String name, String dateOfBirth, String sex, String company){
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    @Override
    public String toString() {
        return "Singer {" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
