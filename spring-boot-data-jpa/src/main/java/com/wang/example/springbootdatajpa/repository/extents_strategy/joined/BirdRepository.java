package com.wang.example.springbootdatajpa.repository.extents_strategy.joined;

import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 17:16
 * @description
 */
public interface BirdRepository extends JpaRepository<Bird,Integer> {

    List<Bird> findByName(String name);

    @Query("from Bird t where t.name=:name")
    List<Bird> getBirdName(String name);
}
