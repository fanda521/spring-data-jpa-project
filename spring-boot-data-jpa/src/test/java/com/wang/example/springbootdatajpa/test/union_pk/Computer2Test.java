package com.wang.example.springbootdatajpa.test.union_pk;

import com.wang.example.springbootdatajpa.entity.union_pk.Computer;
import com.wang.example.springbootdatajpa.entity.union_pk.Computer2;
import com.wang.example.springbootdatajpa.entity.union_pk.Computer2PK;
import com.wang.example.springbootdatajpa.entity.union_pk.ComputerPK;
import com.wang.example.springbootdatajpa.repository.union_pk.Computer2Repository;
import com.wang.example.springbootdatajpa.repository.union_pk.ComputerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 21:38
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Computer2Test {
    @Autowired
    private Computer2Repository computer2Repository;


    /**
     * 插入
     * 先根据联合组件查询
     * 没有则执行的操作是插入
     */
    @Test
    public void insertTest() {
        Computer2 computer = Computer2.builder()
                .brandName("戴尔")
                .ip("192.168.1.1")
                .ownerId("002")
                .build();

        Computer2 save = computer2Repository.save(computer);
        System.out.println(save);
        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_0_, computer2x0_.t_owner_id as t_owner_2_1_0_, computer2x0_.t_brand_name as t_brand_3_1_0_ from t_computer2 computer2x0_ where computer2x0_.t_ip=? and computer2x0_.t_owner_id=?
         * Hibernate: insert into t_computer2 (t_brand_name, t_ip, t_owner_id) values (?, ?, ?)
         * Computer2(ip=192.168.1.1, ownerId=001, brandName=戴尔)
         */


    }

    /**
     * 根据联合主键中的全部查找
     */
    @Test
    @Transactional
    public void selectTest() {
        Computer2 one = computer2Repository.getOne(Computer2PK.builder().ip("192.168.1.1").ownerId("001").build());
        System.out.println(one);

        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_0_, computer2x0_.t_owner_id as t_owner_2_1_0_, computer2x0_.t_brand_name as t_brand_3_1_0_ from t_computer2 computer2x0_ where computer2x0_.t_ip=? and computer2x0_.t_owner_id=?
         * Computer2(ip=192.168.1.1, ownerId=001, brandName=戴尔)
         */
        Computer2 two = computer2Repository.getOne(Computer2PK.builder().ownerId("0012").build());
        //System.out.println(two);
        //报错
        //javax.persistence.EntityNotFoundException:
        // Unable to find com.wang.example.springbootdatajpa.entity.union_pk.Computer2 with id Computer2PK(ip=null, ownerId=0012)
    }

    /**
     * 根据联合主键中的部分查找
     */
    @Test
    @Transactional
    public void select2Test() {
        List<Computer2> byComputerPK_ip = computer2Repository.findByIp("192.168.1.1");
        System.out.println(byComputerPK_ip);

        System.out.println("-------------------------------------------");

        List<Computer2> byComputerPK_ownerId = computer2Repository.findByOwnerId("002");
        System.out.println(byComputerPK_ownerId);
        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_, computer2x0_.t_owner_id as t_owner_2_1_, computer2x0_.t_brand_name as t_brand_3_1_ from t_computer2 computer2x0_ where computer2x0_.t_ip=?
         * [Computer2(ip=192.168.1.1, ownerId=001, brandName=戴尔), Computer2(ip=192.168.1.1, ownerId=002, brandName=戴尔)]
         * -------------------------------------------
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_, computer2x0_.t_owner_id as t_owner_2_1_, computer2x0_.t_brand_name as t_brand_3_1_ from t_computer2 computer2x0_ where computer2x0_.t_owner_id=?
         * [Computer2(ip=192.168.1.1, ownerId=002, brandName=戴尔)]
         */
    }

    /**
     * 根据主键全部属性更新
     */
    @Test
    @Transactional
    @Commit
    public void updateTest() {
        Computer2 computer = Computer2.builder()
                .brandName("戴尔2")
                .ip("192.168.1.1")
                .ownerId("001")
                .build();

        Computer2 save = computer2Repository.save(computer);
        System.out.println(save);
        System.out.println("--------------------------------------");

        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_0_, computer2x0_.t_owner_id as t_owner_2_1_0_, computer2x0_.t_brand_name as t_brand_3_1_0_ from t_computer2 computer2x0_ where computer2x0_.t_ip=? and computer2x0_.t_owner_id=?
         * Computer2(ip=192.168.1.1, ownerId=001, brandName=戴尔2)
         * --------------------------------------
         * Hibernate: update t_computer2 set t_brand_name=? where t_ip=? and t_owner_id=?
         */
        Computer2 computer2 = Computer2.builder()
                .brandName("戴尔2")
                .ownerId("002")
                .build();
        Computer2 save2 = computer2Repository.save(computer2);
        //System.out.println(save2);
        //报错
        //Caused by: com.mysql.jdbc.exceptions.
        // jdbc4.MySQLIntegrityConstraintViolationException: Column 't_ip' cannot be null
    }

    /**
     * 根据主键的部分字段更新
     */
    @Test
    @Transactional
    @Commit
    public void update2Test() {
        int count = computer2Repository.updateComputerByIp("海尔2", "192.168.1.1");
        System.out.println(count);
        /**
         * Hibernate: update t_computer2 set t_brand_name=? where t_ip=?
         * 2
         */

    }

    /**
     * 根据主键的部分字段更新
     */
    @Test
    @Transactional
    @Commit
    public void update3Test() {
        int count = computer2Repository.updateComputerByIpNative("格力", "192.168.1.1");
        System.out.println(count);
        /**
         * Hibernate: update t_computer2 set t_brand_name =? where t_ip =?
         * 2
         */

    }


    /**
     * 根据主键全部字段删除
     */
    @Transactional
    @Test
    @Commit
    public void deleteTest() {
        Computer2PK computerPK = Computer2PK.builder()
                .ownerId("001")
                //.ip("192.168.1.1")
                .ip("192.168.1.1")
                .build();
        //主键中单个的是不行的
        //他会先查找 ，找到了在删除
        computer2Repository.deleteById(computerPK);
        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_0_, computer2x0_.t_owner_id as t_owner_2_1_0_, computer2x0_.t_brand_name as t_brand_3_1_0_ from t_computer2 computer2x0_ where computer2x0_.t_ip=? and computer2x0_.t_owner_id=?
         * Hibernate: delete from t_computer2 where t_ip=? and t_owner_id=?
         */

    }


    /**
     * 根据实体类删除
     */
    @Transactional
    @Test
    @Commit
    public void delete2Test() {
        Computer2 computer = Computer2.builder()
                .ownerId("002")
                //.ip("192.168.1.1")
                .build();

        computer2Repository.delete(computer);
        //没删除
        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_0_, computer2x0_.t_owner_id as t_owner_2_1_0_, computer2x0_.t_brand_name as t_brand_3_1_0_ from t_computer2 computer2x0_ where computer2x0_.t_ip=? and computer2x0_.t_owner_id=?
         */
    }

    /**
     * 自定义的删除
     * 成功删除
     */
    @Transactional
    @Test
    @Commit
    public void delete3Test() {
        int i = computer2Repository.deleteByOwnerId("002");
        System.out.println(i);
        /**
         * Hibernate: select computer2x0_.t_ip as t_ip1_1_, computer2x0_.t_owner_id as t_owner_2_1_, computer2x0_.t_brand_name as t_brand_3_1_ from t_computer2 computer2x0_ where computer2x0_.t_owner_id=?
         * 1
         * Hibernate: delete from t_computer2 where t_ip=? and t_owner_id=?
         */
    }




}
