package com.wang.example.springbootdatajpa.test.audit;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit;
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
public class MyAuditTest {
    @Autowired
    private MyAuditRepository myAuditRepository;

    @Test
    public void insertTest() {
        MyAudit myAudit = MyAudit.builder()
                .name("allen")
                .build();
        MyAudit save = myAuditRepository.save(myAudit);
        System.out.println(save);
        /**
         * Hibernate: insert into t_my_audit (cre_date, cre_user, modi_date, modi_user, t_name) values (?, ?, ?, ?, ?)
         * MyAudit(id=1, name=allen, createUser=allen, modifyUser=allen, createDate=Mon Nov 14 23:35:30 CST 2022, modifyDate=Mon Nov 14 23:35:30 CST 2022)
         */
    }

    @Test
    @Transactional
    @Commit
    public void updateTest() {
        MyAudit myAudit = MyAudit.builder()
                .id(1)
                .name("jack")
                .build();
        MyAudit save = myAuditRepository.save(myAudit);
        System.out.println(save);
        /**
         * Hibernate: select myaudit0_.t_id as t_id1_4_0_, myaudit0_.cre_date as cre_date2_4_0_, myaudit0_.cre_user as cre_user3_4_0_, myaudit0_.modi_date as modi_dat4_4_0_, myaudit0_.modi_user as modi_use5_4_0_, myaudit0_.t_name as t_name6_4_0_ from t_my_audit myaudit0_ where myaudit0_.t_id=?
         * MyAudit(id=1, name=jack, createUser=null, modifyUser=null, createDate=null, modifyDate=null)
         * Hibernate: update t_my_audit set cre_date=?, cre_user=?, modi_date=?, modi_user=?, t_name=? where t_id=?
         */
    }


}
