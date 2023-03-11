package com.wang.example.springbootdatajpa.repository.extents_strategy.table_pre_class;

import com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 10:00
 * @week 星期四
 * @description Car 子类 dao
 **/
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
}
