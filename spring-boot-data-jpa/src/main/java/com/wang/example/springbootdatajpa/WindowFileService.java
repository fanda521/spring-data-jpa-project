package com.wang.example.springbootdatajpa;

import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFileVo;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 0:09
 * @description
 */
@Service
public class WindowFileService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<WindowFileVo> getByDiscriminator(String discriminator) {
        //1. 可以不用as
        //2. 查询到的字段 可以少，但是如果查询的多余自定义的接收实体，也就是实体的字段少于查询的会报错
        //com.sun.proxy.$Proxy178 cannot be cast to org.hibernate.query.internal.NativeQueryImpl
        String sql ="select discriminator,id,name,type,date,size,file_count  fileCount from window_file where discriminator =? ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(WindowFileVo.class));
        nativeQuery.setParameter(1,discriminator);

        List<WindowFileVo> resultList = nativeQuery.getResultList();
        System.out.println(resultList);
        return resultList;

    }
}
