package com.wang.example.springbootdatajpa;

import com.wang.example.springbootdatajpa.entity.Person;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 11:10
 * @week 星期四
 * @description t_person service
 **/
@Service
public class PersonService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getByNameLikeSql(String name) {
        String sql =" select t.t_id id,t.t_name name , \n" +
                " t.t_age age,t.t_address address, t.t_birthday birthday \n" +
                " from t_person t \n" +
                " where t.t_name like :name";

        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("name","%" + name + "%");
        org.hibernate.query.Query query = nativeQuery.unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.aliasToBean(Person.class));
        List<Person> resultList = query.getResultList();
        return resultList;
    }

    public List<Person> getByNameLikeSqlIndex(String name) {
        String sql =" select t.t_id id,t.t_name name , \n" +
                " t.t_age age,t.t_address address, t.t_birthday birthday \n" +
                " from t_person t \n" +
                " where t.t_name like ?1";

        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,"%" + name + "%");
        org.hibernate.query.Query query = nativeQuery.unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.aliasToBean(Person.class));
        List<Person> resultList = query.getResultList();
        return resultList;
    }
}
