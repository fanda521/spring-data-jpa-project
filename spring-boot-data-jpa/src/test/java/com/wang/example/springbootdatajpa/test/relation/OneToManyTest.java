package com.wang.example.springbootdatajpa.test.relation;

import com.wang.example.springbootdatajpa.entity.one2Many.Project;
import com.wang.example.springbootdatajpa.entity.one2Many.Student;
import com.wang.example.springbootdatajpa.repository.one2many.ProjectRepository;
import com.wang.example.springbootdatajpa.repository.one2many.StudentRepository;
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
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToManyTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 外键表插入 project没有设置级联
     */
    @Test
    public void insertTest() {

        Student student1 = Student.builder()
                .name("student1")
                .build();
        System.out.println(student1);
        Student save2 = studentRepository.save(student1);
        System.out.println(save2);

        Project project = Project.builder()
                .name("project-one")
                .student(student1)
                .build();

        Project project1 = Project.builder()
                .name("project-two")
                .student(student1)
                .build();

        System.out.println(project);
        System.out.println(project1);
        System.out.println("-------------------------------");
        Project save = projectRepository.save(project);
        Project save1 = projectRepository.save(project1);
        System.out.println(student1);
        System.out.println(save);
        System.out.println(save1);

        /**
         * Student(id=null, name=student1)
         * Hibernate: insert into t_student (t_name) values (?)
         * Student(id=1, name=student1)
         * Project(id=null, name=project-one, student=Student(id=1, name=student1))
         * Project(id=null, name=project-two, student=Student(id=1, name=student1))
         * -------------------------------
         * Hibernate: insert into t_project (t_sub_name, t_student_id) values (?, ?)
         * Hibernate: insert into t_project (t_sub_name, t_student_id) values (?, ?)
         * Student(id=1, name=student1)
         * Project(id=2, name=project-one, student=Student(id=1, name=student1))
         * Project(id=3, name=project-two, student=Student(id=1, name=student1))
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

        Student student1 = Student.builder()
                .name("student1")
                .build();


        Project project = Project.builder()
                .name("project-one")
                .student(student1)
                .build();

        Project project1 = Project.builder()
                .name("project-two")
                .student(student1)
                .build();

        System.out.println(student1);
        System.out.println(project);
        System.out.println(project1);
        System.out.println("-------------------------------");
        Project save = projectRepository.save(project);
        Project save1 = projectRepository.save(project1);
        System.out.println(student1);
        System.out.println(save);
        System.out.println(save1);

        /**
         * Student(id=null, name=student1)
         * Project(id=null, name=project-one, student=Student(id=null, name=student1))
         * Project(id=null, name=project-two, student=Student(id=null, name=student1))
         * -------------------------------
         * Hibernate: insert into t_student (t_name) values (?)
         * Hibernate: insert into t_project (t_sub_name, t_student_id) values (?, ?)
         * Hibernate: insert into t_project (t_sub_name, t_student_id) values (?, ?)
         * Student(id=1, name=student1)
         * Project(id=1, name=project-one, student=Student(id=1, name=student1))
         * Project(id=2, name=project-two, student=Student(id=1, name=student1))
         */

    }

    /**
     * 更新外表 没有设置student 有级联
     */
    @Test
    @Transactional
    @Commit
    public void updateTest() {
        Project project = Project.builder()
                .name("project-one-modify")
                .id(1)
                .build();
        Project save = projectRepository.save(project);
        System.out.println(save);
        /**
         * Hibernate: select project0_.t_id as t_id1_5_0_, project0_.t_sub_name as t_sub_na2_5_0_, project0_.t_student_id as t_studen3_5_0_ from t_project project0_ where project0_.t_id=?
         * Hibernate: select student0_.t_id as t_id1_6_0_, student0_.t_name as t_name2_6_0_ from t_student student0_ where student0_.t_id=?
         * Project(id=1, name=project-one-modify, student=null)
         * Hibernate: update t_project set t_sub_name=?, t_student_id=? where t_id=?
         */
    }

    /**
     * 只添加主表 project 有级联
     */
    @Test
    public void insertMainTest() {
        Student student1 = Student.builder()
                .name("student2")
                .build();
        System.out.println(student1);
        System.out.println("------------------------");
        Student save = studentRepository.save(student1);
        System.out.println(save);
        /**
         * Student(id=null, name=student2)
         * ------------------------
         * Hibernate: insert into t_student (t_name) values (?)
         * Student(id=2, name=student2)
         */
    }

    /**
     * 只更新主表 project 有级联
     */
    @Test
    public void updateMainTest(){
        Student student1 = Student.builder()
                .name("student2-modiy")
                .id(2)
                .build();
        System.out.println(student1);
        System.out.println("----------------------");
        Student save = studentRepository.save(student1);
        System.out.println(save);
        /**
         * Student(id=2, name=student2-modiy)
         * ----------------------
         * Hibernate: select student0_.t_id as t_id1_6_0_, student0_.t_name as t_name2_6_0_ from t_student student0_ where student0_.t_id=?
         * Hibernate: update t_student set t_name=? where t_id=?
         * Student(id=2, name=student2-modiy)
         */
    }
}
