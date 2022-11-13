package com.wang.example.springbootdatajpa.entity.many2many;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 16:07
 * @description 多对多 不维护中间表配置 多向
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_sys_user2")
public class SysUser2 {
    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @ManyToMany(mappedBy = "sysUserList2")//维护外键的表的对应实体中的属性字段名
    private List<SysRole2> sysRole2;

}
