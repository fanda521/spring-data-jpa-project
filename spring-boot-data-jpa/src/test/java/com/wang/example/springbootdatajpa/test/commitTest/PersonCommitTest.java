package com.wang.example.springbootdatajpa.test.commitTest;

import com.wang.example.springbootdatajpa.repository.PersonRepository;
import com.wang.example.springbootdatajpa.service.CommitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2024/1/20 22:17
 * @description 编程式事务
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonCommitTest {

    @Autowired
    private CommitService commitService;

    /**
     * 没有事务注解可以
     */
    @Test
    public void test01() {
        commitService.testCommitWithOutAnno();
    }

    /**
     * 有事务的注解也可以
     */
    @Test
    public void test02() {
        commitService.testCommitWithAnno();
    }
}
