package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.one2one.IdCard;
import com.wang.example.springbootdatajpa.entity.one2one.People;
import com.wang.example.springbootdatajpa.repository.one2one.IdCardRepository;
import com.wang.example.springbootdatajpa.repository.one2one.PeopleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:13
 * @description 一对一 单向
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToOneTest {
    @Autowired
    private IdCardRepository idCardRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    /**
     * 外键表插入
     */
    @Test
    public void insertTest() {
        People people = People.builder()
                .age(13)
                .name("jeffrey")
                .build();

        IdCard idCard = IdCard.builder()
                .number(UUID.randomUUID().toString().replace("-", ""))
                .people(people)
                .build();
        IdCard save = idCardRepository.save(idCard);
        System.out.println("--------------------------");
        System.out.println(save);

    }

    /**
     * 主表自己插入
     */
    @Test
    public void insertMainTest() {
        People people = People.builder()
                .age(13)
                .name("jeffrey-main")
                .build();
        System.out.println(people);
        People save = peopleRepository.save(people);
        System.out.println(save);

    }

    /**
     * 外键表 自己更新
     * 结果：没有传入主表对象，会默认清空外键字段值
     */
    @Test
    public void updateTest() {
        IdCard idCard = IdCard.builder()
                .number("123456")
                .id(1)
                .build();
        IdCard save = idCardRepository.save(idCard);
        System.out.println(save);
        //结果
        //没有传入people,认为是解除了关联关系
    }

    /**
     * 只更新主表
     * 结果：先根据填入的id查询
     * 然后再更新
     *
     */
    @Test
    public void updateMianTest() {
        People people = People.builder()
                .age(16)
                .id(3)
                .name("jeffrey-main2")
                .build();
        System.out.println(people);
        People save = peopleRepository.save(people);
        System.out.println(save);
        /*Hibernate: select people0_.t_id as t_id1_2_0_, people0_.t_age as t_age2_2_0_, people0_.t_name as t_name3_2_0_ from t_people people0_ where people0_.t_id=?
        Hibernate: update t_people set t_age=?, t_name=? where t_id=?*/
    }
}
