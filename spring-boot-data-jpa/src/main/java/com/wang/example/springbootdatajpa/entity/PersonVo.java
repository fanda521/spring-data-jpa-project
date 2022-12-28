package com.wang.example.springbootdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/26 23:16
 * @description
 */
@Data
@AllArgsConstructor
public class PersonVo {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private Date birthday;
}
