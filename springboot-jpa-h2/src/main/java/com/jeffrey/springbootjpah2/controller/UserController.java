package com.jeffrey.springbootjpah2.controller;

import com.jeffrey.springbootjpah2.entity.User;
import com.jeffrey.springbootjpah2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

   @RequestMapping("/list")
    public List<User> findAll(){
       List<User> userList = userRepository.findAll();
       return userList;
   }

   @PostMapping("/save")
   public String save(@RequestBody User user){
       userRepository.save(user);
       return "保存成功";
   }

   @PostMapping("/update")
   public String update(@RequestBody User user){
       userRepository.save(user);
       return "更新成功";
   }

   @RequestMapping("/delete")
   public String delete(Integer id){
       userRepository.deleteById(id);
       return "删除成功";
   }

}
