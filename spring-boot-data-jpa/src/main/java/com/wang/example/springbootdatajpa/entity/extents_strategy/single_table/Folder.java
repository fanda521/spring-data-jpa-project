package com.wang.example.springbootdatajpa.entity.extents_strategy.single_table;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Folder")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Folder extends WindowFile {

 @Basic
 @Column(name = "FILE_COUNT")
 private Integer fileCount;

 @Override
 public String toString() {
  return super.toString() + "Folder{" +
          "fileCount=" + fileCount +
          '}';
 }
}