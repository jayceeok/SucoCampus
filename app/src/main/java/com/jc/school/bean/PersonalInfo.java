package com.jc.school.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class PersonalInfo extends BmobObject {
    private String name;
    private String position;
    private String office_tel;
    private String office_short_tel;
    private String mobile_phone;
    private String mobile_short_phone;
    private String home_tel;
    private String email;
    private String qq;
    private String department;
    private String head_url;

    public PersonalInfo() {
    }

    public PersonalInfo(String name, String position, String office_tel, String office_short_tel,
                        String mobile_phone, String mobile_short_phone, String home_tel,
                        String email, String qq, String department,String head_url) {
        this.department = department;
        this.email = email;
        this.home_tel = home_tel;
        this.mobile_phone = mobile_phone;
        this.mobile_short_phone = mobile_short_phone;
        this.name = name;
        this.office_short_tel = office_short_tel;
        this.office_tel = office_tel;
        this.position = position;
        this.qq = qq;
        this.head_url=head_url;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getMobile_short_phone() {
        return mobile_short_phone;
    }

    public void setMobile_short_phone(String mobile_short_phone) {
        this.mobile_short_phone = mobile_short_phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice_short_tel() {
        return office_short_tel;
    }

    public void setOffice_short_tel(String office_short_tel) {
        this.office_short_tel = office_short_tel;
    }

    public String getOffice_tel() {
        return office_tel;
    }

    public void setOffice_tel(String office_tel) {
        this.office_tel = office_tel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
