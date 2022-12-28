package com.wang.example.springbootdatajpa.repository.extents_strategy.joined;

import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 17:38
 * @description
 */
public interface DogRepository extends JpaRepository<Dog,Integer> {
    List<Dog> findByName(String name);

    @Query("from Dog  t where t.name=:name")
    List<Dog> getDogName(String name);

}
