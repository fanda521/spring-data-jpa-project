package com.wang.example.springbootdatajpa.entity.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

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
@Table(name = "t_my_audit3")
public class MyAudit3 {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;


    @Column(name = "cre_user",updatable = false)
    private String createUser;

    @Column(name = "modi_user")
    private String modifyUser;


    @Column(name = "cre_date",updatable = false)
    private LocalDateTime createDate;

    @Column(name = "modi_date")
    private LocalDateTime modifyDate;


    @PrePersist
    public void insert() {
        this.createDate = this.modifyDate = LocalDateTime.now();
        this.createUser = this.modifyUser ="mark";
    }

    @PreUpdate
    public void update() {
        this.modifyDate = LocalDateTime.now();
        this.modifyUser = "mark";
    }
}
