package com.wang.example.springbootdatajpa.repository.extents_strategy.joined;

import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 17:15
 * @description
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer> {
    List<Animal> findByName(String name);

    @Query("from Animal t where t.name=:name")
    List<Animal> getAnimalName(@Param("name") String name);
}
