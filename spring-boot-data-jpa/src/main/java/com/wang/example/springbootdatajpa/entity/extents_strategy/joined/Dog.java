package com.wang.example.springbootdatajpa.entity.extents_strategy.joined;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_dog")
@DiscriminatorValue("dog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends Animal {  
  
    @Column(name = "legs")
    private Integer legs;

    @Override
    public String toString() {
        return super.toString() + "Dog{" +
                "legs=" + legs +
                '}';
    }
}