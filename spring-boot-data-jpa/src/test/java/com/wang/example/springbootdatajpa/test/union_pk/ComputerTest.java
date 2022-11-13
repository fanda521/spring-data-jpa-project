package com.wang.example.springbootdatajpa.test.union_pk;

import com.wang.example.springbootdatajpa.entity.union_pk.Computer;
import com.wang.example.springbootdatajpa.entity.union_pk.ComputerPK;
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
public class ComputerTest {
    @Autowired
    private ComputerRepository computerRepository;


    /**
     * 插入
     * 先根据联合组件查询
     * 没有则执行的操作是插入
     */
    @Test
    public void insertTest() {
        ComputerPK computerPK = ComputerPK.builder()
                .ip("192.168.1.1")
                .ownerId("002")
                .build();
        Computer computer = Computer.builder()
                .brandName("戴尔")
                .computerPK(computerPK)
                .build();

        Computer save = computerRepository.save(computer);
        System.out.println(save);
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_0_, computer0_.t_owner_id as t_owner_2_0_0_, computer0_.t_brand_name as t_brand_3_0_0_ from t_computer computer0_ where computer0_.t_ip=? and computer0_.t_owner_id=?
         * Hibernate: insert into t_computer (t_brand_name, t_ip, t_owner_id) values (?, ?, ?)
         * Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=001), brandName=戴尔)
         */

    }

    /**
     * 根据联合主键中的全部查找
     */
    @Test
    @Transactional
    public void selectTest() {
        Computer one = computerRepository.getOne(ComputerPK.builder().ip("192.168.1.1").ownerId("001").build());
        System.out.println(one);
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_0_, computer0_.t_owner_id as t_owner_2_0_0_, computer0_.t_brand_name as t_brand_3_0_0_ from t_computer computer0_ where computer0_.t_ip=? and computer0_.t_owner_id=?
         * Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=001), brandName=戴尔)
         */

        Computer two = computerRepository.getOne(ComputerPK.builder().ownerId("0012").build());
        //System.out.println(two);
        //报错
    }

    /**
     * 根据联合主键中的部分查找
     */
    @Test
    @Transactional
    public void select2Test() {
        List<Computer> byComputerPK_ip = computerRepository.findByComputerPK_Ip("192.168.1.1");
        System.out.println(byComputerPK_ip);

        System.out.println("-------------------------------------------");

        List<Computer> byComputerPK_ownerId = computerRepository.findByComputerPK_OwnerId("002");
        System.out.println(byComputerPK_ownerId);
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_, computer0_.t_owner_id as t_owner_2_0_, computer0_.t_brand_name as t_brand_3_0_ from t_computer computer0_ where computer0_.t_ip=?
         * [Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=001), brandName=戴尔), Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=002), brandName=戴尔)]
         * -------------------------------------------
         * Hibernate: select computer0_.t_ip as t_ip1_0_, computer0_.t_owner_id as t_owner_2_0_, computer0_.t_brand_name as t_brand_3_0_ from t_computer computer0_ where computer0_.t_owner_id=?
         * [Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=002), brandName=戴尔)]
         */
    }

    /**
     * 根据主键全部属性更新
     */
    @Test
    @Transactional
    @Commit
    public void updateTest() {
        ComputerPK computerPK = ComputerPK.builder()
                .ip("192.168.1.1")
                .ownerId("001")
                .build();
        Computer computer = Computer.builder()
                .brandName("戴尔2")
                .computerPK(computerPK)
                .build();

        Computer save = computerRepository.save(computer);
        System.out.println(save);
        System.out.println("--------------------------------------");
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_0_, computer0_.t_owner_id as t_owner_2_0_0_, computer0_.t_brand_name as t_brand_3_0_0_ from t_computer computer0_ where computer0_.t_ip=? and computer0_.t_owner_id=?
         * Computer(computerPK=ComputerPK(ip=192.168.1.1, ownerId=001), brandName=戴尔2)
         * --------------------------------------
         * Hibernate: update t_computer set t_brand_name=? where t_ip=? and t_owner_id=?
         */
        ComputerPK computerPK2 = ComputerPK.builder()
                .ownerId("002")
                .build();
        Computer computer2 = Computer.builder()
                .brandName("戴尔2")
                .computerPK(computerPK2)
                .build();
        //Computer save2 = computerRepository.save(computer2);
        //System.out.println(save2);
        //单个的会报错和查询一样不能这样操作
        //需要用单独的查询方式查询，不要用主键的，主键的只能是全部属性的
    }

    /**
     * 根据主键的部分字段更新
     */
    @Test
    @Transactional
    @Commit
    public void update2Test() {
        int count = computerRepository.updateComputerByIp("海尔2", "192.168.1.1");
        System.out.println(count);
        /**
         * Hibernate: update t_computer set t_brand_name=? where t_ip=?
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
        int count = computerRepository.updateComputerByIpNative("格力", "192.168.1.1");
        System.out.println(count);
        /**
         * Hibernate: update t_computer set t_brand_name=? where t_ip=?
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
        ComputerPK computerPK = ComputerPK.builder()
                .ownerId("001")
                //.ip("192.168.1.1")
                .ip("192.168.1.1")
                .build();
        //主键中单个的是不行的
        //他会先查找 ，找到了在删除
        computerRepository.deleteById(computerPK);
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_0_, computer0_.t_owner_id as t_owner_2_0_0_, computer0_.t_brand_name as t_brand_3_0_0_ from t_computer computer0_ where computer0_.t_ip=? and computer0_.t_owner_id=?
         * Hibernate: delete from t_computer where t_ip=? and t_owner_id=?
         */
    }


    /**
     * 根据实体类删除
     */
    @Transactional
    @Test
    @Commit
    public void delete2Test() {
        ComputerPK computerPK = ComputerPK.builder()
                .ownerId("002")
                //.ip("192.168.1.1")
                .build();
        Computer computer = Computer.builder()
                .computerPK(computerPK)
                .build();


        computerRepository.delete(computer);
        //没有删除，因为他会找到里面的两个，如果没传就是空 条件就变成了 where ip = null and owner_id ='002'
        //自然是找不到这样的记录
        //所以测试到这里，可以发现像这样联合主键的，必须将联合主键看成整体，使用jpa自带的方法必须都传入值
        //如果想只用部分联合主键的字段过滤或者更新的，请使用自定义的方法，可以使本地的也可以是根据方法名的
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_0_, computer0_.t_owner_id as t_owner_2_0_0_, computer0_.t_brand_name as t_brand_3_0_0_ from t_computer computer0_ where computer0_.t_ip=? and computer0_.t_owner_id=?
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
        int i = computerRepository.deleteByComputerPK_OwnerId("002");
        System.out.println(i);
        /**
         * Hibernate: select computer0_.t_ip as t_ip1_0_, computer0_.t_owner_id as t_owner_2_0_, computer0_.t_brand_name as t_brand_3_0_ from t_computer computer0_ where computer0_.t_owner_id=?
         * 1
         * Hibernate: delete from t_computer where t_ip=? and t_owner_id=?
         */
    }




}
