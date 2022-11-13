package com.jeffrey.spring_data_jpa_xml.test;

import com.jeffrey.spring_data_jpa_xml.entity.Customer;
import com.jeffrey.spring_data_jpa_xml.untils.JpaUntils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author allen
 * @version 1.0
 * @date 2020-09-12 10:01
 */
public class CustomerTest {

    /**
     * jpa保存测试
     *      jpa操作步骤
     *          1.加载配置文件创建工厂（实体管理类工厂）对象
     *          2.通过实体管理类工厂获取实体管理器
     *          3.获取食物对象。开启事务
     *          4.完成增删改查操作
     *          5.提交事务
     *          6.释放资源
     *
     */

    @Test
    public void testSave(){
        EntityManagerFactory myJpa = Persistence.createEntityManagerFactory("myJpa");
        EntityManager manager = myJpa.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            Customer customer = new Customer();
            customer.setCusAddress("广东深圳");
            customer.setCusIndustry("IT");
            customer.setCusLevel("二等");
            customer.setCusName("jeffrey2");
            customer.setCusPhone("13870873449");
            customer.setCusRource("58同城");
            manager.persist(customer);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            transaction.rollback();
        }finally {
            manager.close();;
            myJpa.close();
        }

    }
    @Test
    public void testSelect(){
        JpaUntils.factory.close();
    }


}
