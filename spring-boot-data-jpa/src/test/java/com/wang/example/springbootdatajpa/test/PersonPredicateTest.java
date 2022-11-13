package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/10/17 0:00
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonPredicateTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testPredicate01() {
        List<Person> all = personRepository.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //root 获取的列
                //query 组合 order by
                //criteriaBuilder where
                Path<String> name = root.get("name");
                Path<Integer> age = root.get("age");
                Path<String> address = root.get("address");
                Predicate nameP = criteriaBuilder.notLike(name, "luck1");
                Predicate ageP = criteriaBuilder.greaterThan(age, 16);
                CriteriaBuilder.In<String> in = criteriaBuilder.in(address);
                in.value("江西宜春").value("讲义抚州");
                Order desc = criteriaBuilder.desc(age);

                Predicate in1 = criteriaBuilder.and(nameP);
                return query.where(in1).orderBy(desc).getRestriction();

            }
        });

        System.out.println(all);
    }







}
