package com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 9:52
 * @week 星期四
 * @description 父类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "t_Vehicle")
public class Vehicle {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "SPEED")
    private Integer speed;// 速度

}
