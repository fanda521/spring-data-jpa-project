package com.wang.example.springbootdatajpa.controller;

import com.wang.example.springbootdatajpa.entity.audit.MyAudit;
import com.wang.example.springbootdatajpa.repository.audit.MyAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 14:12
 * @week 星期四
 * @description
 **/
@RequestMapping("/audit")
@Controller
public class MyAuditController {
    @Autowired
    private MyAuditRepository myAuditRepository;

    @GetMapping("/update")
    @ResponseBody
    public Object updateMyAudit() {
        MyAudit myAudit = MyAudit.builder()
                .id(1)
                .name("jack")
                .build();
        MyAudit save = myAuditRepository.save(myAudit);
        System.out.println(save);
        return save;
    }
}
