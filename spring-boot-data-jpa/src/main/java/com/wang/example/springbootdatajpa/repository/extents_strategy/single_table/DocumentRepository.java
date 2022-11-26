package com.wang.example.springbootdatajpa.repository.extents_strategy.single_table;

import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/26 18:31
 * @description
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
