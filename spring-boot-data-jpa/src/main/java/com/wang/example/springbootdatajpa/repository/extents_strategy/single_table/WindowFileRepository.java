package com.wang.example.springbootdatajpa.repository.extents_strategy.single_table;

import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/11/25
 * @time 18:58
 * @week 星期五
 * @description
 **/
@Repository
public interface WindowFileRepository extends JpaRepository<WindowFile,Integer> {
}
