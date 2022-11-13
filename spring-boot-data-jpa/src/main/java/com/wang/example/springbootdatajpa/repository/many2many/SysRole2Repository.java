package com.wang.example.springbootdatajpa.repository.many2many;

import com.wang.example.springbootdatajpa.entity.many2many.SysRole;
import com.wang.example.springbootdatajpa.entity.many2many.SysRole2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 16:19
 * @description
 */
@Repository
public interface SysRole2Repository extends JpaRepository<SysRole2,Integer> {
}
