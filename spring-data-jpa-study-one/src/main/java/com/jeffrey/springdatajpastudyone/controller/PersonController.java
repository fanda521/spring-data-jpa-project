package com.jeffrey.springdatajpastudyone.controller;

import com.jeffrey.springdatajpastudyone.dao.PersonDao;
import com.jeffrey.springdatajpastudyone.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther Jeffrey
 * @date 2020/9/10 17:21
 */
@RestController
public class PersonController {
    @Autowired
    private PersonDao personDao;

    @PostMapping(value = "PersonController/insertPerson")
    public Person insertPerson(@RequestBody Person person){
        Person save = (Person) personDao.save(person);
        return save;
    }
}
