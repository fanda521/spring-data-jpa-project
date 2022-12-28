package com.wang.example.springbootdatajpa.entity.extents_strategy.joined;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_bird")
@DiscriminatorValue("bird")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bird extends Animal {  
  
    @Column(name = "speed")
    private String speed;

    @Override
    public String toString() {
        return super.toString() + "Bird{" +
                "speed='" + speed + '\'' +
                '}';
    }
}