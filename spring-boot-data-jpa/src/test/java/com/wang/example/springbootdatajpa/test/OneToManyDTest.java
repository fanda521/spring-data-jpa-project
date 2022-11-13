package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.one2Many.Project2;
import com.wang.example.springbootdatajpa.entity.one2Many.Student2;
import com.wang.example.springbootdatajpa.repository.one2many.Project2Repository;
import com.wang.example.springbootdatajpa.repository.one2many.Student2Repository;
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
 * @date 2022/11/13 14:40
 * @description 双向
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToManyDTest {
    @Autowired
    private Project2Repository project2Repository;

    @Autowired
    private Student2Repository student2Repository;

    /**
     * 外键表插入 project没有设置级联
     */
    @Test
    public void insertTest() {

        Student2 student1 = Student2.builder()
                .name("student1")
                .build();
        System.out.println(student1);
        Student2 save2 = student2Repository.save(student1);
        System.out.println(save2);

        Project2 project = Project2.builder()
                .name("project-one")
                .student2(student1)
                .build();

        Project2 project1 = Project2.builder()
                .name("project-two")
                .student2(student1)
                .build();

        System.out.println(project);
        System.out.println(project1);
        System.out.println("-------------------------------");
        Project2 save = project2Repository.save(project);
        Project2 save1 = project2Repository.save(project1);
        System.out.println(student1);
        System.out.println(save);
        System.out.println(save1);

        /**
         * Student2(id=null, name=student1, project2=null)
         * Hibernate: insert into t_student2 (t_name) values (?)
         * Student2(id=2, name=student1, project2=null)
         * Project2(id=null, name=project-one, student2=Student2(id=2, name=student1, project2=null))
         * Project2(id=null, name=project-two, student2=Student2(id=2, name=student1, project2=null))
         * -------------------------------
         * Hibernate: insert into t_project2 (t_sub_name, t_student_id) values (?, ?)
         * Hibernate: insert into t_project2 (t_sub_name, t_student_id) values (?, ?)
         * Student2(id=2, name=student1, project2=null)
         * Project2(id=1, name=project-one, student2=Student2(id=2, name=student1, project2=null))
         * Project2(id=2, name=project-two, student2=Student2(id=2, name=student1, project2=null))
         */


    }


    /**
     * 外键表插入 project设置级联
     * 加入了注解
     * @Transactional
     *     @Commit
     * 则可以解决插入的问题
     */
    @Test
    @Transactional
    @Commit
    public void insert2Test() {

        Student2 student1 = Student2.builder()
                .name("student1")
                .build();


        Project2 project = Project2.builder()
                .name("project-one")
                .student2(student1)
                .build();

        Project2 project1 = Project2.builder()
                .name("project-two")
                .student2(student1)
                .build();

        System.out.println(student1);
        System.out.println(project);
        System.out.println(project1);
        System.out.println("-------------------------------");
        Project2 save = project2Repository.save(project);
        Project2 save1 = project2Repository.save(project1);
        System.out.println(student1);
        System.out.println(save);
        System.out.println(save1);

        /**
         * Student2(id=null, name=student1, project2=null)
         * Project2(id=null, name=project-one, student2=Student2(id=null, name=student1, project2=null))
         * Project2(id=null, name=project-two, student2=Student2(id=null, name=student1, project2=null))
         * -------------------------------
         * Hibernate: insert into t_student2 (t_name) values (?)
         * Hibernate: insert into t_project2 (t_sub_name, t_student_id) values (?, ?)
         * Hibernate: insert into t_project2 (t_sub_name, t_student_id) values (?, ?)
         * Student2(id=3, name=student1, project2=null)
         * Project2(id=3, name=project-one, student2=Student2(id=3, name=student1, project2=null))
         * Project2(id=4, name=project-two, student2=Student2(id=3, name=student1, project2=null))
         */

    }

    /**
     * 更新外表 没有设置student 有级联
     */
    @Test
    @Transactional
    @Commit
    public void updateTest() {
        Project2 project = Project2.builder()
                .name("project-one-modify")
                .id(1)
                .build();
        Project2 save = project2Repository.save(project);
        System.out.println(save);
        /**
         * Hibernate: select project2x0_.t_id as t_id1_6_0_, project2x0_.t_sub_name as t_sub_na2_6_0_, project2x0_.t_student_id as t_studen3_6_0_ from t_project2 project2x0_ where project2x0_.t_id=?
         * Hibernate: select student2x0_.t_id as t_id1_8_0_, student2x0_.t_name as t_name2_8_0_ from t_student2 student2x0_ where student2x0_.t_id=?
         * Project2(id=1, name=project-one-modify, student2=null)
         * Hibernate: update t_project2 set t_sub_name=?, t_student_id=? where t_id=?
         */
    }

    /**
     * 只添加主表 project 有级联
     */
    @Test
    public void insertMainTest() {
        Student2 student1 = Student2.builder()
                .name("student2")
                .build();
        System.out.println(student1);
        System.out.println("------------------------");
        Student2 save = student2Repository.save(student1);
        System.out.println(save);
        /**
         * Student2(id=null, name=student2, project2=null)
         * ------------------------
         * Hibernate: insert into t_student2 (t_name) values (?)
         * Student2(id=4, name=student2, project2=null)
         */

    }

    /**
     * 只更新主表 project 有级联
     */
    @Test
    public void updateMainTest(){
        Student2 student1 = Student2.builder()
                .name("student2-modiy")
                .id(2)
                .build();
        System.out.println(student1);
        System.out.println("----------------------");
        Student2 save = student2Repository.save(student1);
        System.out.println(save);
        /**
         * Student2(id=2, name=student2-modiy, project2=null)
         * ----------------------
         * Hibernate: select student2x0_.t_id as t_id1_8_0_, student2x0_.t_name as t_name2_8_0_ from t_student2 student2x0_ where student2x0_.t_id=?
         * Hibernate: update t_student2 set t_name=? where t_id=?
         * Student2(id=2, name=student2-modiy, project2=null)
         */
    }
}
