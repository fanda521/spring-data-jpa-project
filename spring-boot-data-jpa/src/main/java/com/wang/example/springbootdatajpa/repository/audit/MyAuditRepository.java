package com.wang.example.springbootdatajpa.repository.audit;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/14 23:31
 * @description
 */
@Repository
public interface MyAuditRepository extends JpaRepository<MyAudit,Integer> {
}
