package com.wang.example.springbootdatajpa.entity.one2one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 10:59
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_people2")
public class People2 {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;


    @Column(name = "t_age")
    private int age;

    @OneToOne(mappedBy = "people2")//负责维护外键的表对应的实体中，持有的本类类型属性的属性名字
    private IdCard2 idCard2;



}
