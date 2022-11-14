package com.wang.example.springbootdatajpa.entity.union_pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name = "t_computer2")
@IdClass(Computer2PK.class)
public class Computer2 {

    @Id
    @Column(name = "t_ip")
    private String ip;

    @Id
    @Column(name = "t_owner_id")
    private String ownerId;

    @Column(name="t_brand_name")
    private String brandName;

}
