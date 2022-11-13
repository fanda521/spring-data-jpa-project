package com.wang.example.springbootdatajpa.repository.one2one;

import com.wang.example.springbootdatajpa.entity.one2one.IdCard2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:19
 * @description
 */
@Repository
public interface IdCard2Repository extends JpaRepository<IdCard2,Integer> {
}
