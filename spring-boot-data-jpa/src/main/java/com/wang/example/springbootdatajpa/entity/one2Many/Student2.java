package com.wang.example.springbootdatajpa.entity.one2Many;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 13:53
 * @description 一对多 一的一方 双向 不维护外键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_student2")
public class Student2 {
    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @OneToMany(mappedBy = "student2")//维护外键的一方对应的实体类中的本类类型的属性字段名称
    private List<Project2> project2;


}
