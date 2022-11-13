package com.wang.example.springbootdatajpa.repository.one2many;

import com.wang.example.springbootdatajpa.entity.one2Many.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 14:13
 * @description
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
