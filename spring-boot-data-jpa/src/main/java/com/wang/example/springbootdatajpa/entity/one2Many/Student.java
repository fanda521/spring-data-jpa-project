package com.wang.example.springbootdatajpa.entity.one2Many;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 13:53
 * @description 一对多 一的一方 单向 不维护外键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;


}
