package com.wang.example.springbootdatajpa.repository.extents_strategy.table_pre_class;

import com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 9:59
 * @week 星期四
 * @description Vehicle 父类dao
 **/
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
