package com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_car")
public class Car extends Vehicle {  
  
    @Column(name = "engine")
    private String engine;// 发动机  

    @Override
    public String toString() {
        return super.toString() + "Car{" +
                "engine='" + engine + '\'' +
                '}';
    }
}