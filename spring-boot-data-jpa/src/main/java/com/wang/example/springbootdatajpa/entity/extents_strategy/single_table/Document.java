package com.wang.example.springbootdatajpa.entity.extents_strategy.single_table;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document extends WindowFile {

 @Basic
 @Column(name = "SIZE")
 private String size;

 @Override
 public String toString() {
  return super.toString() + "Document{" +
          "size='" + size + '\'' +
          '}';
 }
}