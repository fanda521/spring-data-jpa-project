package com.wang.example.springbootdatajpa.repository.one2one;

import com.wang.example.springbootdatajpa.entity.one2one.People2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:18
 * @description
 */
@Repository
public interface People2Repository extends JpaRepository<People2,Integer> {
}
