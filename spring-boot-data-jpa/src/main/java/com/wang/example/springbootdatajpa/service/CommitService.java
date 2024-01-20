package com.wang.example.springbootdatajpa.service;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2024/1/20 22:21
 * @description
 */
@Service
public class CommitService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void testCommitWithOutAnno() {

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            transactionTemplate.execute(new TransactionCallback<Object>() {
                @Override
                public Object doInTransaction(TransactionStatus transactionStatus) {
                    try {
                        insertPerson(finalI);
                        if (finalI == 2) {
                            int temp = 1/ 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        transactionStatus.setRollbackOnly();
                    }
                    return null;
                }
            });
        }

    }

    public void insertPerson(int i) {
        Person person = new Person();
        person.setName("jeffrey" + i);
        person.setAge(22);
        person.setBirthday(new Date());
        person.setAddress("江西南昌" + i);

        System.out.println("before:" + person);
        Person save = personRepository.save(person);
        System.out.println("after:"+save);
    }

    public void testCommitWithAnno() {

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            transactionTemplate.execute(new TransactionCallback<Object>() {
                @Override
                public Object doInTransaction(TransactionStatus transactionStatus) {
                    try {
                        insertPerson2(finalI);
                        if (finalI == 1) {
                            int temp = 1/ 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        transactionStatus.setRollbackOnly();
                    }
                    return null;
                }
            });
        }

    }


    @Transactional
    public void insertPerson2(int i) {
        Person person = new Person();
        person.setName("luck" + i);
        person.setAge(22);
        person.setBirthday(new Date());
        person.setAddress("江西南昌" + i);

        System.out.println("before:" + person);
        Person save = personRepository.save(person);
        System.out.println("after:"+save);
    }
}
