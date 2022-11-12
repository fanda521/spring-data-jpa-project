package com.wang.example.springbootdatajpa.test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wang.example.springbootdatajpa.entity.QPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/10/17 0:36
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonQueryDslTest {
    @PersistenceContext
    private EntityManager entityManager;
    // 查询工厂实体
    private JPAQueryFactory jpaQueryFactory;

    // 实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Test
    public void testDSL01() {
        QPerson person = QPerson.person;

    }

}
