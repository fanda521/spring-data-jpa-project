package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.many2many.SysRole;
import com.wang.example.springbootdatajpa.entity.many2many.SysRole2;
import com.wang.example.springbootdatajpa.entity.many2many.SysUser;
import com.wang.example.springbootdatajpa.entity.many2many.SysUser2;
import com.wang.example.springbootdatajpa.repository.many2many.SysRole2Repository;
import com.wang.example.springbootdatajpa.repository.many2many.SysRoleRepository;
import com.wang.example.springbootdatajpa.repository.many2many.SysUser2Repository;
import com.wang.example.springbootdatajpa.repository.many2many.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 16:20
 * @description 多对多 单向
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ManyToManyDTest {
    @Autowired
    private SysUser2Repository sysUserRepository;

    @Autowired
    private SysRole2Repository sysRoleRepository;


    /**
     * 外键表插入 没有级联
     */
    @Test
    public void insertTest() {
        SysUser2 sysUser1 = SysUser2.builder()
                .name("SYS_USER_1")
                .build();

        SysUser2 sysUser2 = SysUser2.builder()
                .name("SYS_USER_2")
                .build();

        SysUser2 sysUser3 = SysUser2.builder()
                .name("SYS_USER_3")
                .build();

        System.out.println(sysUser1);
        System.out.println(sysUser2);
        System.out.println(sysUser3);
        System.out.println("-------------------------");
        sysUserRepository.save(sysUser1);
        sysUserRepository.save(sysUser2);
        sysUserRepository.save(sysUser3);
        System.out.println(sysUser1);
        System.out.println(sysUser2);
        System.out.println(sysUser3);

        SysRole2 role_admin = SysRole2.builder()
                .name("ROLE_ADMIN")
                .sysUserList2(Arrays.asList(sysUser1, sysUser2))
                .build();

        SysRole2 role_client = SysRole2.builder()
                .name("ROLE_CLIENT")
                .sysUserList2(Arrays.asList(sysUser2,sysUser3))
                .build();
        System.out.println(role_admin);
        System.out.println(role_client);
        System.out.println("-------------------");
        sysRoleRepository.save(role_admin);
        sysRoleRepository.save(role_client);
        System.out.println(role_admin);
        System.out.println(role_client);
        /**
         * SysUser2(id=null, name=SYS_USER_1, sysRole2=null)
         * SysUser2(id=null, name=SYS_USER_2, sysRole2=null)
         * SysUser2(id=null, name=SYS_USER_3, sysRole2=null)
         * -------------------------
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * SysUser2(id=1, name=SYS_USER_1, sysRole2=null)
         * SysUser2(id=2, name=SYS_USER_2, sysRole2=null)
         * SysUser2(id=3, name=SYS_USER_3, sysRole2=null)
         * SysRole2(id=null, name=ROLE_ADMIN, sysUserList2=[SysUser2(id=1, name=SYS_USER_1, sysRole2=null), SysUser2(id=2, name=SYS_USER_2, sysRole2=null)])
         * SysRole2(id=null, name=ROLE_CLIENT, sysUserList2=[SysUser2(id=2, name=SYS_USER_2, sysRole2=null), SysUser2(id=3, name=SYS_USER_3, sysRole2=null)])
         * -------------------
         * Hibernate: insert into t_sys_role2 (t_name) values (?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into t_sys_role2 (t_name) values (?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * SysRole2(id=1, name=ROLE_ADMIN, sysUserList2=[SysUser2(id=1, name=SYS_USER_1, sysRole2=null), SysUser2(id=2, name=SYS_USER_2, sysRole2=null)])
         * SysRole2(id=2, name=ROLE_CLIENT, sysUserList2=[SysUser2(id=2, name=SYS_USER_2, sysRole2=null), SysUser2(id=3, name=SYS_USER_3, sysRole2=null)])
         */

    }

    /**
     * 外键表插入 有级联
     */
    @Test
    @Transactional
    @Commit
    public void insert2Test() {
        SysUser2 sysUser1 = SysUser2.builder()
                .name("SYS_USER_1")
                .build();

        SysUser2 sysUser2 = SysUser2.builder()
                .name("SYS_USER_2")
                .build();

        SysUser2 sysUser3 = SysUser2.builder()
                .name("SYS_USER_3")
                .build();

        System.out.println(sysUser1);
        System.out.println(sysUser2);
        System.out.println(sysUser3);
        System.out.println("-------------------------");


        SysRole2 role_admin = SysRole2.builder()
                .name("ROLE_ADMIN")
                .sysUserList2(Arrays.asList(sysUser1, sysUser2))
                .build();

        SysRole2 role_client = SysRole2.builder()
                .name("ROLE_CLIENT")
                .sysUserList2(Arrays.asList(sysUser2,sysUser3))
                .build();
        System.out.println(role_admin);
        System.out.println(role_client);
        System.out.println("-------------------");
        sysRoleRepository.save(role_admin);
        sysRoleRepository.save(role_client);
        System.out.println(sysUser1);
        System.out.println(sysUser2);
        System.out.println(sysUser3);
        System.out.println(role_admin);
        System.out.println(role_client);
        /**
         * SysUser2(id=null, name=SYS_USER_1, sysRole2=null)
         * SysUser2(id=null, name=SYS_USER_2, sysRole2=null)
         * SysUser2(id=null, name=SYS_USER_3, sysRole2=null)
         * -------------------------
         * SysRole2(id=null, name=ROLE_ADMIN, sysUserList2=[SysUser2(id=null, name=SYS_USER_1, sysRole2=null), SysUser2(id=null, name=SYS_USER_2, sysRole2=null)])
         * SysRole2(id=null, name=ROLE_CLIENT, sysUserList2=[SysUser2(id=null, name=SYS_USER_2, sysRole2=null), SysUser2(id=null, name=SYS_USER_3, sysRole2=null)])
         * -------------------
         * Hibernate: insert into t_sys_role2 (t_name) values (?)
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * Hibernate: insert into t_sys_role2 (t_name) values (?)
         * Hibernate: insert into t_sys_user2 (t_name) values (?)
         * SysUser2(id=4, name=SYS_USER_1, sysRole2=null)
         * SysUser2(id=5, name=SYS_USER_2, sysRole2=null)
         * SysUser2(id=6, name=SYS_USER_3, sysRole2=null)
         * SysRole2(id=3, name=ROLE_ADMIN, sysUserList2=[SysUser2(id=4, name=SYS_USER_1, sysRole2=null), SysUser2(id=5, name=SYS_USER_2, sysRole2=null)])
         * SysRole2(id=4, name=ROLE_CLIENT, sysUserList2=[SysUser2(id=5, name=SYS_USER_2, sysRole2=null), SysUser2(id=6, name=SYS_USER_3, sysRole2=null)])
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel2 (role_id, user_id) values (?, ?)
         */
    }

    /**
     * 更新维护了外键的表 有级联 没有设置sysUser
     * 结果：会删除中间表中对应的记录
     *
     */
    @Test
    @Transactional
    @Commit
    public void updateTest() {
        SysRole2 role_admin_modify = SysRole2.builder()
                .id(1)
                .name("ROLE_ADMIN_MODIFY")
                .build();
        SysRole2 save = sysRoleRepository.save(role_admin_modify);
        System.out.println(save);
        /**
         * Hibernate: select sysrole2x0_.t_id as t_id1_10_0_, sysrole2x0_.t_name as t_name2_10_0_ from t_sys_role2 sysrole2x0_ where sysrole2x0_.t_id=?
         * SysRole2(id=1, name=ROLE_ADMIN_MODIFY, sysUserList2=null)
         * Hibernate: update t_sys_role2 set t_name=? where t_id=?
         * Hibernate: delete from user_role_rel2 where role_id=?
         */
    }

    /**
     * 只插入没有维护外键的记录 sysrole有级联
     */
    @Test
    public void insertMainTest() {
        SysUser2 sysUser1 = SysUser2.builder()
                .name("SYS_USER_1")
                .build();
        System.out.println(sysUser1);
        System.out.println("----------------------------");
        SysUser2 save = sysUserRepository.save(sysUser1);
        System.out.println(save);
        /**
         * SysUser(id=null, name=SYS_USER_1)
         * ----------------------------
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * SysUser(id=7, name=SYS_USER_1)
         */

    }

    /**
     * 只插入维护外键的记录 sysrole 有级联
     */
    @Test
    public void insertOutTest() {
        SysRole2 role_out = SysRole2.builder()
                .name("ROLE_OUT")
                .build();
        System.out.println(role_out);
        System.out.println("----------------------------");
        SysRole2 save = sysRoleRepository.save(role_out);
        System.out.println(save);
        /**
         * SysRole2(id=null, name=ROLE_OUT, sysUserList2=null)
         * ----------------------------
         * Hibernate: insert into t_sys_role2 (t_name) values (?)
         * SysRole2(id=5, name=ROLE_OUT, sysUserList2=null)
         */

    }

    /**
     * 只更新没有维护外键的记录  sysrole有级联
     */
    @Test
    public void updateMainTest() {
        SysUser2 sys_user_4_modify = SysUser2.builder()
                .id(4)
                .name("sys_user_4_modify")
                .build();
        SysUser2 save = sysUserRepository.save(sys_user_4_modify);
        System.out.println(save);
        /**
         * Hibernate: select sysuser2x0_.t_id as t_id1_12_0_, sysuser2x0_.t_name as t_name2_12_0_ from t_sys_user2 sysuser2x0_ where sysuser2x0_.t_id=?
         * Hibernate: update t_sys_user2 set t_name=? where t_id=?
         * SysUser2(id=4, name=sys_user_4_modify, sysRole2=null)
         */
    }

}
