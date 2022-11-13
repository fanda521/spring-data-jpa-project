package com.wang.example.springbootdatajpa.entity.one2one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:07
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_idcard2")
public class IdCard2 {
    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "t_number")
    private String number;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "t_people_id")//外键id
    private People2 people2;

}
