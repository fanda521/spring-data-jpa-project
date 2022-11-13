package com.wang.example.springbootdatajpa.repository.many2many;

import com.wang.example.springbootdatajpa.entity.many2many.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 16:18
 * @description
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
}
