package com.wang.example.springbootdatajpa.entity.one2Many;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 14:03
 * @description 学科科目 一对多 多的一方 双向 负责维护外键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_project2")
public class Project2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "t_sub_name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "t_student_id")
    private Student2 student2;
}
