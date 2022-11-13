package com.wang.example.springbootdatajpa.entity.union_pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 21:35
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_computer")
public class Computer {

    @EmbeddedId
    private ComputerPK computerPK;

    @Column(name="t_brand_name")
    private String brandName;

}
