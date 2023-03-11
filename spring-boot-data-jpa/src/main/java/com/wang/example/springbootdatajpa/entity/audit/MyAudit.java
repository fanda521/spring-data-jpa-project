package com.wang.example.springbootdatajpa.entity.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/14 23:23
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_my_audit")
@EntityListeners({AuditingEntityListener.class})
public class MyAudit {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    /**
     * @CreatedBy ：由谁创建这条记录
     * @LastModifiedBy：是谁最后更新了这条记录
     * @CreatedDate：创建时间
     * @LastModifiedDate：最后更新时间
     */
    @Column(name = "cre_user", updatable = false)
    @CreatedBy
    private String createUser;

    @Column(name = "modi_user")
    @LastModifiedBy
    private String modifyUser;


    @Column(name = "cre_date", updatable = false)
    @CreatedDate
    private Date createDate;

    @Column(name = "modi_date")
    @LastModifiedDate
    private Date modifyDate;



}
