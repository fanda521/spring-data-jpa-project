package com.wang.example.springbootdatajpa.entity.extents_strategy.single_table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/26 21:47
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WindowFileVo {
    private String discriminator;

    private Integer id;


    private String name;


    private String type;


    private Date date;

    private String size;

    private Integer fileCount;
}
