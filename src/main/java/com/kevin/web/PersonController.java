package com.kevin.web;

import com.kevin.domain.Person;
import com.kevin.domain.PersonRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by kevin on 17/4/22.
 */
@RestController
//@RequestMapping(value="/users")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // 查找所有通讯录用户
    @GetMapping(value="/person")
    public List<Person> getPersonList(){
        return personRepository.findAll();
    }

//    // 注册用户
//    @PostMapping(value="/users")
//    public Person postUser(@RequestParam("name") String name,
//                           @RequestParam("studentNum") long studentNum,
//                           @RequestParam("password") String password) {
//        Person person = new Person();
//        person.setName(name);
//        person.setStudentNum(studentNum);
//        person.setPassword(password);
//
//        return personRepository.save(person);
//    }

    // 修改用户信息
    @PutMapping(value = "/person/{id}")
    public void putPerson(@PathVariable("id") Integer id,
                          @RequestBody JSONObject userJson) throws JSONException {
        Person person = personRepository.findOne(id);
        person.setAge(userJson.getInt("age"));
        person.setCity(userJson.getString("city"));
        person.setClasses(userJson.getString("classes"));
        person.setEmail(userJson.getString("email"));
        person.setEnterYear(userJson.getInt("enter_year"));
        person.setGraduationYear(userJson.getInt("graduation_year"));
        person.setMajor(userJson.getString("major"));
        person.setPhoneNumber(userJson.getLong("phone_number"));
        person.setSex(userJson.getString("sex"));
        person.setWorkUnit(userJson.getString("work_unit"));
    }

    // 删除通讯录用户
    @DeleteMapping(value = "/person/{id}")
    public String deletePerson(@PathVariable("id") Integer id ){
        personRepository.delete(id);
        return "success";
    }


}
