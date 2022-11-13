package com.jeffrey.spring_data_jpa_xml.entity;

/**
 * @author allen
 * @version 1.0
 * @date 2020-09-12 9:48
 */

import javax.persistence.*;

/**
 * 配置实体类和表的映射关系
 *
 */
@Entity
@Table(name = "jpa_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cus_id")
    private Integer cusId;
    @Column(name = "cus_name")
    private String  cusName;
    @Column(name = "cus_resource")
    private String  cusResource;
    @Column(name = "cus_level")
    private String  cusLevel;
    @Column(name = "cus_industry")
    private String  cusIndustry;
    @Column(name = "cus_phone")
    private String  cusPhone;
    @Column(name = "cus_address")
    private String  cusAddress;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusRource() {
        return cusResource;
    }

    public void setCusRource(String cusRource) {
        this.cusResource = cusRource;
    }

    public String getCusLevel() {
        return cusLevel;
    }

    public void setCusLevel(String cusLevel) {
        this.cusLevel = cusLevel;
    }

    public String getCusIndustry() {
        return cusIndustry;
    }

    public void setCusIndustry(String cusIndustry) {
        this.cusIndustry = cusIndustry;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId=" + cusId +
                ", cusName='" + cusName + '\'' +
                ", cusRource='" + cusResource + '\'' +
                ", cusLevel='" + cusLevel + '\'' +
                ", cusIndustry='" + cusIndustry + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                '}';
    }
}
