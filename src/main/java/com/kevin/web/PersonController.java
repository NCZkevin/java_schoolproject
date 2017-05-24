package com.kevin.web;

import com.kevin.domain.Person;
import com.kevin.domain.PersonRepository;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "查找所有通讯录用户")
    @GetMapping(value="/person")
    public List<Person> getPersonList(){
        return personRepository.findAll();
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/person/{id}")
    public void putPerson(@PathVariable("id") Integer id,
                          @RequestBody JSONObject userJson) throws JSONException {
        Person person = personRepository.findOne(id);
        person.setName(userJson.getString("name"));
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

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/person/{id}")
    public void deletePerson(@PathVariable("id") Integer id ){
        personRepository.delete(id);
    }


}
