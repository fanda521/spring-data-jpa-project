package com.wang.example.springbootdatajpa.entity.extents_strategy.joined;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_animal")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "aaa")  // 辨别字段 AAA
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Animal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;
}