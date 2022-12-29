package com.wang.example.springbootdatajpa.entity.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name = "t_my_audit2")
@EntityListeners({AuditingEntityListener.class})
public class MyAudit2 implements Auditable<String, Integer, LocalDateTime> {

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


    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(this.createUser);
    }

    @Override
    public void setCreatedBy(String s) {
        this.createUser = s;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(this.createDate);
    }

    @Override
    public void setCreatedDate(LocalDateTime date) {
        this.createDate = date;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(this.modifyUser);
    }

    @Override
    public void setLastModifiedBy(String s) {
        this.modifyUser =s;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.modifyDate);
    }

    @Override
    public void setLastModifiedDate(LocalDateTime date) {
        this.modifyDate = date;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
