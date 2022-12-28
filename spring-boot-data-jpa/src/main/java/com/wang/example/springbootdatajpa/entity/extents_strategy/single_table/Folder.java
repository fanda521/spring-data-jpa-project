package com.wang.example.springbootdatajpa.entity.extents_strategy.single_table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Folder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Folder extends WindowFile {

 @Basic
 @Column(name = "FILE_COUNT")
 private Integer fileCount;

}