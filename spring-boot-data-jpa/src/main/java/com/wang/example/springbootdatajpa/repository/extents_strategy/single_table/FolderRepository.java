package com.wang.example.springbootdatajpa.repository.extents_strategy.single_table;

import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/26 18:30
 * @description
 */
@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer> {

}
