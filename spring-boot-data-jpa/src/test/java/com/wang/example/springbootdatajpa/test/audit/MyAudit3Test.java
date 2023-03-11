package com.wang.example.springbootdatajpa.test.audit;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit2;
import com.wang.example.springbootdatajpa.entity.audit.MyAudit3;
import com.wang.example.springbootdatajpa.repository.audit.MyAudit2Repository;
import com.wang.example.springbootdatajpa.repository.audit.MyAudit3Repository;
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
public class MyAudit3Test {
    @Autowired
    private MyAudit3Repository myAudit3Repository;

    @Test
    public void insertTest() {
        MyAudit3 myAudit = MyAudit3.builder()
                .name("allen")
                .build();
        MyAudit3 save = myAudit3Repository.save(myAudit);
        System.out.println(save);
        /**
         * 仅仅用@prePersist @preUpdate 需要自己控制字段信息的填充
         * Hibernate: insert into t_my_audit3 (cre_date, cre_user, modi_date, modi_user, t_name) values (?, ?, ?, ?, ?)
         * MyAudit3(id=1, name=allen, createUser=null, modifyUser=null, createDate=2022-12-29T14:51:22.803, modifyDate=2022-12-29T14:51:22.803)
         *
         * 加入了创建人和更新人的字段后的结果
         * Hibernate: insert into t_my_audit3 (cre_date, cre_user, modi_date, modi_user, t_name) values (?, ?, ?, ?, ?)
         * MyAudit3(id=3, name=allen, createUser=mark, modifyUser=mark, createDate=2022-12-29T14:56:49.708, modifyDate=2022-12-29T14:56:49.708)
         */

    }

    @Test
    @Transactional
    @Commit
    public void updateTest() {
        MyAudit3 myAudit = MyAudit3.builder()
                .id(3)
                .name("jack")
                .build();
        MyAudit3 save = myAudit3Repository.save(myAudit);
        System.out.println(save);

    }


}
