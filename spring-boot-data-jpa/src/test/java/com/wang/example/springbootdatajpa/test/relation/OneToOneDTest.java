package com.wang.example.springbootdatajpa.test.relation;

import com.wang.example.springbootdatajpa.entity.one2one.IdCard2;
import com.wang.example.springbootdatajpa.entity.one2one.People2;
import com.wang.example.springbootdatajpa.repository.one2one.IdCard2Repository;
import com.wang.example.springbootdatajpa.repository.one2one.People2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 13:12
 * @description 一对一 双向
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToOneDTest {
    @Autowired
    private IdCard2Repository idCard2Repository;

    @Autowired
    private People2Repository people2Repository;

    /**
     * 外键表插入
     */
    @Test
    public void insertTest() {
        People2 people = People2.builder()
                .age(15)
                .name("jeffreD")
                .build();

        IdCard2 idCard = IdCard2.builder()
                .number(UUID.randomUUID().toString().replace("-", ""))
                .people2(people)
                .build();
        IdCard2 save = idCard2Repository.save(idCard);
        System.out.println("--------------------------");
        System.out.println(save);

        /**
         * Hibernate: insert into t_people2 (t_age, t_name) values (?, ?)
         * Hibernate: insert into t_idcard2 (t_number, t_people_id) values (?, ?)
         * --------------------------
         * IdCard2(id=1, number=57fcb1ad7bd741eb8a721f8b2e9a6a04, people2=People2(id=1, name=jeffreD, age=15, idCard2=null))
         */

    }

    /**
     * 主表自己插入
     */
    @Test
    public void insertMainTest() {
        People2 people = People2.builder()
                .age(13)
                .name("jeffrey-main")
                .build();
        System.out.println(people);
        People2 save = people2Repository.save(people);
        System.out.println(save);

        /**
         * People2(id=null, name=jeffrey-main, age=13, idCard2=null)
         * Hibernate: insert into t_people2 (t_age, t_name) values (?, ?)
         * People2(id=2, name=jeffrey-main, age=13, idCard2=null)
         */

    }

    /**
     * 外键表 自己更新
     * 结果：没有传入主表对象，会默认清空外键字段值
     */
    @Test
    public void updateTest() {
        IdCard2 idCard = IdCard2.builder()
                .number("123456")
                .id(1)
                .build();
        IdCard2 save = idCard2Repository.save(idCard);
        System.out.println(save);
        //结果
        //没有传入people2,认为是解除了关联关系
        /**
         * Hibernate: select idcard2x0_.t_id as t_id1_1_0_, idcard2x0_.t_number as t_number2_1_0_, idcard2x0_.t_people_id as t_people3_1_0_ from t_idcard2 idcard2x0_ where idcard2x0_.t_id=?
         * Hibernate: select people2x0_.t_id as t_id1_3_0_, people2x0_.t_age as t_age2_3_0_, people2x0_.t_name as t_name3_3_0_ from t_people2 people2x0_ where people2x0_.t_id=?
         * Hibernate: select idcard2x0_.t_id as t_id1_1_1_, idcard2x0_.t_number as t_number2_1_1_, idcard2x0_.t_people_id as t_people3_1_1_, people2x1_.t_id as t_id1_3_0_, people2x1_.t_age as t_age2_3_0_, people2x1_.t_name as t_name3_3_0_ from t_idcard2 idcard2x0_ left outer join t_people2 people2x1_ on idcard2x0_.t_people_id=people2x1_.t_id where idcard2x0_.t_people_id=?
         * Hibernate: update t_idcard2 set t_number=?, t_people_id=? where t_id=?
         * IdCard2(id=1, number=123456, people2=null)
         */
    }

    /**
     * 只更新主表
     * 结果：先根据填入的id查询
     * 然后再更新
     *
     */
    @Test
    public void updateMianTest() {
        People2 people = People2.builder()
                .age(17)
                .id(1)
                .name("jeffrey-main1")
                .build();
        System.out.println(people);
        People2 save = people2Repository.save(people);
        System.out.println(save);
        /**
         * People2(id=1, name=jeffrey-main1, age=17, idCard2=null)
         * Hibernate: select people2x0_.t_id as t_id1_3_0_, people2x0_.t_age as t_age2_3_0_, people2x0_.t_name as t_name3_3_0_ from t_people2 people2x0_ where people2x0_.t_id=?
         * Hibernate: select idcard2x0_.t_id as t_id1_1_1_, idcard2x0_.t_number as t_number2_1_1_, idcard2x0_.t_people_id as t_people3_1_1_, people2x1_.t_id as t_id1_3_0_, people2x1_.t_age as t_age2_3_0_, people2x1_.t_name as t_name3_3_0_ from t_idcard2 idcard2x0_ left outer join t_people2 people2x1_ on idcard2x0_.t_people_id=people2x1_.t_id where idcard2x0_.t_people_id=?
         * Hibernate: update t_people2 set t_age=?, t_name=? where t_id=?
         * People2(id=1, name=jeffrey-main1, age=17, idCard2=null)
         */

    }

}
