package com.wang.example.springbootdatajpa.entity.union_pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 21:32
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Computer2PK implements Serializable {

    private String ip;

    private String ownerId;


}
