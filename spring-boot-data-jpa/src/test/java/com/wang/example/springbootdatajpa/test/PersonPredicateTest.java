package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 普通多条件查询
     */
    @Test
    public void test01() {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate nameLikePredicate= criteriaBuilder.like(root.get("name"), "%luck%");
                Predicate agePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("age"), 20);
                Predicate agePredicate2 = criteriaBuilder.greaterThanOrEqualTo(root.get("age"), 12);
                Predicate birthday = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date());
                Predicate birthday1 = criteriaBuilder.isNull(root.get("birthday"));
                Predicate predicate = criteriaBuilder.or(birthday, birthday1);
                ArrayList<Predicate> predicates = new ArrayList<>();
                predicates.add(nameLikePredicate);
                predicates.add(agePredicate2);
                predicates.add(agePredicate);
                predicates.add(predicate);
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };

        List<Person> all = personRepository.findAll(specification);
        all.stream().forEach(System.out::println);
        /**
         * select person0_.t_id as t_id1_13_, person0_.t_address as t_addres2_13_,
         * person0_.t_age as t_age3_13_, person0_.t_birthday as t_birthd4_13_,
         * person0_.t_name as t_name5_13_
         * from t_person person0_
         * where (person0_.t_name like ?) and
         * person0_.t_age>=12 and person0_.t_age<=20 and
         * (person0_.t_birthday>=? or person0_.t_birthday is null)
         */
    }


    /**
     * 普通多条件查询
     * 并且多种方式排序
     */
    @Test
    public void test02() {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate birthday = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date());
                Predicate birthday1 = criteriaBuilder.isNull(root.get("birthday"));
                Predicate predicate = criteriaBuilder.or(birthday, birthday1);
                ArrayList<Predicate> predicates = new ArrayList<>();
                predicates.add(predicate);
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };
        // 是否顺序和加入的顺序有关：有关
        //Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"),Sort.Order.desc("birthday"));
        Sort sort = Sort.by(Sort.Order.asc("name"),Sort.Order.desc("birthday"), Sort.Order.desc("age"));
        List<Person> all = personRepository.findAll(specification,sort);
        all.stream().forEach(System.out::println);
        /**
         * select person0_.t_id as t_id1_13_, person0_.t_address as t_addres2_13_,
         * person0_.t_age as t_age3_13_, person0_.t_birthday as t_birthd4_13_,
         * person0_.t_name as t_name5_13_
         * from t_person person0_
         * where person0_.t_birthday>=? or person0_.t_birthday is null
         * order by person0_.t_name asc,
         * person0_.t_age desc,
         * person0_.t_birthday desc
         */

    }

    /**
     * 普通多条件查询
     * 并且多种方式排序
     * 并且加上分页
     */
    @Test
    public void test03() {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate birthday = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date());
                Predicate birthday1 = criteriaBuilder.isNull(root.get("birthday"));
                Predicate predicate = criteriaBuilder.or(birthday, birthday1);
                ArrayList<Predicate> predicates = new ArrayList<>();
                predicates.add(predicate);
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };
        // 是否顺序和加入的顺序有关：有关
        //Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"),Sort.Order.desc("birthday"));
        Sort sort = Sort.by(Sort.Order.asc("name"),Sort.Order.desc("birthday"), Sort.Order.desc("age"));
        Pageable pageable =  PageRequest.of(1,3,sort);
        Page<Person> page = personRepository.findAll(specification, pageable);
        List<Person> content = page.getContent();
        content.stream().forEach(System.out::println);
        System.out.println(page);
        /**
         * Hibernate:
         * select person0_.t_id as t_id1_13_, person0_.t_address as t_addres2_13_,
         * person0_.t_age as t_age3_13_, person0_.t_birthday as t_birthd4_13_,
         * person0_.t_name as t_name5_13_
         * from t_person person0_
         * where person0_.t_birthday>=? or person0_.t_birthday is null
         * order by person0_.t_name asc, person0_.t_birthday desc, person0_.t_age desc
         * limit ?, ?
         * Hibernate:
         * select count(person0_.t_id) as col_0_0_
         * from t_person person0_
         * where person0_.t_birthday>=? or person0_.t_birthday is null
         * Person{id=22, name='luckname', age=45, address='江西吉安', birthday=2024-04-12 20:32:55.0}
         * Person{id=18, name='luckname', age=12, address='江西赣州', birthday=2024-02-24 14:55:34.0}
         * Person{id=20, name='smile', age=23, address='广东佛山', birthday=2024-08-16 20:31:20.0}
         */

    }

    /**
     * 普通多条件查询
     * 并且多种方式排序
     * 并且是分组查询
     */
    @Test
    public void test04() {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate birthday = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date());
                Predicate birthday1 = criteriaBuilder.isNull(root.get("birthday"));
                Predicate predicate = criteriaBuilder.or(birthday, birthday1);
                ArrayList<Predicate> predicates = new ArrayList<>();
                predicates.add(predicate);
                return query
                        .where(predicates.toArray(new Predicate[0]))
                        .groupBy(root.get("name")).having(criteriaBuilder.like(root.get("name"),"%luck%"))
                        .getRestriction();
            }
        };

        Sort sort = Sort.by(Sort.Order.asc("name"));
        List<Person> all = personRepository.findAll(specification, sort);
        all.stream().forEach(System.out::println);
        /**
         * select person0_.t_id as t_id1_13_, person0_.t_address as t_addres2_13_,
         * person0_.t_age as t_age3_13_, person0_.t_birthday as t_birthd4_13_,
         * person0_.t_name as t_name5_13_
         * from t_person person0_
         * where person0_.t_birthday>=? or person0_.t_birthday is null
         * group by person0_.t_name
         * having person0_.t_name like ?
         * order by person0_.t_name asc
         */
    }


}
