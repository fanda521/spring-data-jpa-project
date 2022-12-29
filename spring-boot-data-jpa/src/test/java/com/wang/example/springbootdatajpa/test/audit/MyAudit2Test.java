package com.wang.example.springbootdatajpa.test.audit;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit;
import com.wang.example.springbootdatajpa.entity.audit.MyAudit2;
import com.wang.example.springbootdatajpa.repository.audit.MyAudit2Repository;
import com.wang.example.springbootdatajpa.repository.audit.MyAuditRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/14 23:32
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyAudit2Test {
    @Autowired
    private MyAudit2Repository myAudit2Repository;

    @Test
    public void insertTest() {
        MyAudit2 myAudit = MyAudit2.builder()
                .name("allen")
                .build();
        MyAudit2 save = myAudit2Repository.save(myAudit);
        System.out.println(save);
        /**
         * Hibernate: insert into t_my_audit2 (cre_date, cre_user, modi_date, modi_user, t_name) values (?, ?, ?, ?, ?)
         * MyAudit2(id=1, name=allen, createUser=allen, modifyUser=allen, createDate=2022-12-29T12:44:17.908, modifyDate=2022-12-29T12:44:17.908)
         */

    }

    @Test
    @Transactional
    @Commit
    public void updateTest() {
        MyAudit2 myAudit = MyAudit2.builder()
                .id(1)
                .name("jack")
                .build();
        MyAudit2 save = myAudit2Repository.save(myAudit);
        System.out.println(save);

    }


}
