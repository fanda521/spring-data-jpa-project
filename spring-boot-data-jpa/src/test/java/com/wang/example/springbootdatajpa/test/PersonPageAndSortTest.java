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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/10/16 13:39
 * @description 分页和排序
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonPageAndSortTest {
    @Autowired
    private PersonRepository personRepository;

    /**
     * 排序字段名固定死的，如果名字后面换了就对不上了
     */
    @Test
    public void findAllSort() {
        Sort name = Sort.by("name").descending().and(Sort.by("age").descending());
        List<Person> all =
                personRepository.findAll(name);
        System.out.println(all);
    }

    /**
     * 实体方法
     */
    @Test
    public void findAllSort2() {
        Sort.TypedSort<Person> sort = Sort.sort(Person.class);
        sort.by(Person::getName).descending().and(sort.by(Person::getAge).descending());
        List<Person> all =
                personRepository.findAll(sort);
        System.out.println(all);
    }

    @Test
    public void findAllPage() {
        Pageable pageable = PageRequest.of(0,2,Sort.by("name").descending());
        Page<Person> all = personRepository.findAll(pageable);
        System.out.println(all.getContent());
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        System.out.println(all.getNumber());
        System.out.println(all.getSize());
        System.out.println(all.getSort().toList());

    }


}
