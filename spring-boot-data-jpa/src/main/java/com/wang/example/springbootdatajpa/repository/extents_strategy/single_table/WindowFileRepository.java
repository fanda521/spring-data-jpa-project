package com.wang.example.springbootdatajpa.repository.extents_strategy.single_table;

import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFile;
import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFileVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(nativeQuery = true,
            value = "select * from window_file where discriminator =:discriminator"
    )
    List<WindowFileVo> getByDiscriminator(@Param("discriminator") String discriminator);

    @Query(nativeQuery = true,
            value = "select * from window_file where type =:type "
    )
    List<WindowFileVo> getByType(@Param("type") String type);

    @Query(nativeQuery = true,
            value = "select * from window_file where discriminator =:discriminator"
    )
    List<WindowFile> findByDiscriminator(@Param("discriminator") String discriminator);




}
