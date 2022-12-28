package com.wang.example.springbootdatajpa.entity.extents_strategy.joined;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 21:25
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalVo {

    private Integer id;


    private String name;


    private String color;

    private String speed;

    private Integer legs;
}
