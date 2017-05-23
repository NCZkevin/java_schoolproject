package com.kevin.web;

import com.kevin.domain.User;
import com.kevin.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by min on 2017/5/23.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 查找所有注册用户
    @GetMapping(value="/getuser")
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    // 查找所有未通过审核用户
    @GetMapping(value = "/user/unpass")
    private List<User> getUnpassUserList(){
        return userRepository.findAllByVerify(-1);
    }

    // 删除注册用户
    @DeleteMapping(value = "/deluser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userRepository.delete(id);
        return "success";
    }

    // 通过审核
    @PutMapping(value = "/user/pass/{id}")
    public String putUserToPass(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);
        user.setVerify(1);
        userRepository.save(user);
        return "success";
    }

    // 不通过审核
    @PutMapping(value = "/user/unpass/{id}")
    public String putUserToUnpass(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);
        user.setVerify(-1);
        userRepository.save(user);
        return "success";
    }

    // 禁用审核通过账户
    @PutMapping(value = "/user/forbid/{id}")
    public String putUserToForbid(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);
        user.setVerify(2);
        userRepository.save(user);
        return "success";
    }

    // 删除未通过审核账户
    @DeleteMapping(value = "/user/delete/{id}")
    public String deleteUnpassUser(@PathVariable("id") Integer id){
        userRepository.delete(id);
        return "success";
    }

}
