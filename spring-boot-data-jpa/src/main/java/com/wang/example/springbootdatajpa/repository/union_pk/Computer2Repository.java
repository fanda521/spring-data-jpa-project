package com.wang.example.springbootdatajpa.repository.union_pk;

import com.wang.example.springbootdatajpa.entity.union_pk.Computer2;
import com.wang.example.springbootdatajpa.entity.union_pk.Computer2PK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 21:37
 * @description
 */
@Repository
public interface Computer2Repository extends JpaRepository<Computer2, Computer2PK> {
    //根据ip
    List<Computer2> findByIp(String ip);

    //根据 ownerId
    List<Computer2> findByOwnerId(String ownerId);

    //根据ip 更新
    //@Query("update Computer set brandName =:brandName where ip =:ip")
    //上面的不行 报错不支持上面的DML
    @Modifying
    //@Transactional
    @Query("update Computer2 set brandName =:brandName where ip =:ip")
    int updateComputerByIp(@Param("brandName") String name, @Param("ip") String ip);

    //尝试使用本地sql
    @Modifying
    @Query(value = "update t_computer2 set t_brand_name =:brandName where t_ip =:ip",nativeQuery = true)
    int updateComputerByIpNative(@Param("brandName") String name, @Param("ip") String ip);

    int deleteByOwnerId(String ownerId);

}
