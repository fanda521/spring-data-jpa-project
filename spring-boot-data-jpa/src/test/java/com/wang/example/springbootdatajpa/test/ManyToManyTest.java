package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.many2many.SysRole;
import com.wang.example.springbootdatajpa.entity.many2many.SysUser;
import com.wang.example.springbootdatajpa.repository.many2many.SysRoleRepository;
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
public class ManyToManyTest {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;


    /**
     * 外键表插入 没有级联
     */
    @Test
    public void insertTest() {
        SysUser sysUser1 = SysUser.builder()
                .name("SYS_USER_1")
                .build();

        SysUser sysUser2 = SysUser.builder()
                .name("SYS_USER_2")
                .build();

        SysUser sysUser3 = SysUser.builder()
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

        SysRole role_admin = SysRole.builder()
                .name("ROLE_ADMIN")
                .sysUserList(Arrays.asList(sysUser1, sysUser2))
                .build();

        SysRole role_client = SysRole.builder()
                .name("ROLE_CLIENT")
                .sysUserList(Arrays.asList(sysUser2,sysUser3))
                .build();
        System.out.println(role_admin);
        System.out.println(role_client);
        System.out.println("-------------------");
        sysRoleRepository.save(role_admin);
        sysRoleRepository.save(role_client);
        System.out.println(role_admin);
        System.out.println(role_client);
        /**
         * SysUser(id=null, name=SYS_USER_1)
         * SysUser(id=null, name=SYS_USER_2)
         * SysUser(id=null, name=SYS_USER_3)
         * -------------------------
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * SysUser(id=1, name=SYS_USER_1)
         * SysUser(id=2, name=SYS_USER_2)
         * SysUser(id=3, name=SYS_USER_3)
         * SysRole(id=null, name=ROLE_ADMIN, sysUserList=[SysUser(id=1, name=SYS_USER_1), SysUser(id=2, name=SYS_USER_2)])
         * SysRole(id=null, name=ROLE_CLIENT, sysUserList=[SysUser(id=2, name=SYS_USER_2), SysUser(id=3, name=SYS_USER_3)])
         * -------------------
         * Hibernate: insert into t_sys_role (t_name) values (?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into t_sys_role (t_name) values (?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * SysRole(id=1, name=ROLE_ADMIN, sysUserList=[SysUser(id=1, name=SYS_USER_1), SysUser(id=2, name=SYS_USER_2)])
         * SysRole(id=2, name=ROLE_CLIENT, sysUserList=[SysUser(id=2, name=SYS_USER_2), SysUser(id=3, name=SYS_USER_3)])
         */

    }

    /**
     * 外键表插入 有级联
     */
    @Test
    @Transactional
    @Commit
    public void insert2Test() {
        SysUser sysUser1 = SysUser.builder()
                .name("SYS_USER_1")
                .build();

        SysUser sysUser2 = SysUser.builder()
                .name("SYS_USER_2")
                .build();

        SysUser sysUser3 = SysUser.builder()
                .name("SYS_USER_3")
                .build();

        System.out.println(sysUser1);
        System.out.println(sysUser2);
        System.out.println(sysUser3);
        System.out.println("-------------------------");


        SysRole role_admin = SysRole.builder()
                .name("ROLE_ADMIN")
                .sysUserList(Arrays.asList(sysUser1, sysUser2))
                .build();

        SysRole role_client = SysRole.builder()
                .name("ROLE_CLIENT")
                .sysUserList(Arrays.asList(sysUser2,sysUser3))
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
         * SysUser(id=null, name=SYS_USER_1)
         * SysUser(id=null, name=SYS_USER_2)
         * SysUser(id=null, name=SYS_USER_3)
         * -------------------------
         * SysRole(id=null, name=ROLE_ADMIN, sysUserList=[SysUser(id=null, name=SYS_USER_1), SysUser(id=null, name=SYS_USER_2)])
         * SysRole(id=null, name=ROLE_CLIENT, sysUserList=[SysUser(id=null, name=SYS_USER_2), SysUser(id=null, name=SYS_USER_3)])
         * -------------------
         * Hibernate: insert into t_sys_role (t_name) values (?)
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * Hibernate: insert into t_sys_role (t_name) values (?)
         * Hibernate: insert into t_sys_user (t_name) values (?)
         * SysUser(id=4, name=SYS_USER_1)
         * SysUser(id=5, name=SYS_USER_2)
         * SysUser(id=6, name=SYS_USER_3)
         * SysRole(id=3, name=ROLE_ADMIN, sysUserList=[SysUser(id=4, name=SYS_USER_1), SysUser(id=5, name=SYS_USER_2)])
         * SysRole(id=4, name=ROLE_CLIENT, sysUserList=[SysUser(id=5, name=SYS_USER_2), SysUser(id=6, name=SYS_USER_3)])
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
         * Hibernate: insert into user_role_rel (role_id, user_id) values (?, ?)
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
        SysRole role_admin_modify = SysRole.builder()
                .id(1)
                .name("ROLE_ADMIN_MODIFY")
                .build();
        SysRole save = sysRoleRepository.save(role_admin_modify);
        System.out.println(save);
        /**
         * Hibernate: select sysrole0_.t_id as t_id1_9_0_, sysrole0_.t_name as t_name2_9_0_ from t_sys_role sysrole0_ where sysrole0_.t_id=?
         * SysRole(id=1, name=ROLE_ADMIN_MODIFY, sysUserList=null)
         * Hibernate: update t_sys_role set t_name=? where t_id=?
         * Hibernate: delete from user_role_rel where role_id=?
         */
    }

    /**
     * 只插入没有维护外键的记录 sysrole有级联
     */
    @Test
    public void insertMainTest() {
        SysUser sysUser1 = SysUser.builder()
                .name("SYS_USER_1")
                .build();
        System.out.println(sysUser1);
        System.out.println("----------------------------");
        SysUser save = sysUserRepository.save(sysUser1);
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
        SysRole role_out = SysRole.builder()
                .name("ROLE_OUT")
                .build();
        System.out.println(role_out);
        System.out.println("----------------------------");
        SysRole save = sysRoleRepository.save(role_out);
        System.out.println(save);
        /**
         * SysRole(id=null, name=ROLE_OUT, sysUserList=null)
         * ----------------------------
         * Hibernate: insert into t_sys_role (t_name) values (?)
         * SysRole(id=5, name=ROLE_OUT, sysUserList=null)
         */

    }

    /**
     * 只更新没有维护外键的记录  sysrole有级联
     */
    @Test
    public void updateMainTest() {
        SysUser sys_user_4_modify = SysUser.builder()
                .id(4)
                .name("sys_user_4_modify")
                .build();
        SysUser save = sysUserRepository.save(sys_user_4_modify);
        System.out.println(save);
        /**
         * Hibernate: select sysuser0_.t_id as t_id1_10_0_, sysuser0_.t_name as t_name2_10_0_ from t_sys_user sysuser0_ where sysuser0_.t_id=?
         * Hibernate: update t_sys_user set t_name=? where t_id=?
         * SysUser(id=4, name=sys_user_4_modify)
         */
    }

}
