package com.wang.example.springbootdatajpa.entity.extents_strategy.single_table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//继承的策略
@Table(name = "WINDOW_FILE")
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING, length = 30)  // 指定辨别字段的类型为String，长度30
@DiscriminatorValue("WindowFile")//指定辨别的字段值
public class WindowFile {
   
     @Id  
     @GeneratedValue(strategy = GenerationType.AUTO)  
     private Integer id;  
   
     @Basic  
     @Column(name = "NAME")  
     private String name;  
   
     @Basic  
     @Column(name = "TYPE")  
     private String type;  
   
     @Basic  
     @Column(name = "DATE")  
     private Date date;

}