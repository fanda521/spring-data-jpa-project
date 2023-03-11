package com.wang.example.springbootdatajpa.repository.audit;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/14 23:31
 * @description
 */
@Repository
public interface MyAuditRepository extends JpaRepository<MyAudit,Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "update t_my_audit t set t.t_name=:#{#req.name} where t.t_id=:#{#req.id}")
    int updateMyAudit(@Param("req") MyAudit myAudit);
}
