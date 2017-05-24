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
                          @RequestBody  Map map) throws JSONException {
        Person person = personRepository.findOne(id);
        if(map.get("name")!=null){
            person.setName((String) map.get("name"));
        }
        if (map.get("age")!=null){
            person.setAge(Integer.parseInt((String) map.get("age")));
        }
        if(map.get("city")!=null){
            person.setCity((String) map.get("city"));
        }
        if(map.get("classes")!=null){
            person.setClasses((String) map.get("classes"));
        }
        if(map.get("email")!=null){
            person.setEmail((String) map.get("email"));
        }
        if(map.get("enter_year")!=null){
            person.setEnterYear(Integer.parseInt((String) map.get("enter_year")));
        }
        if(map.get("graduation_year")!=null){
            person.setGraduationYear(Integer.parseInt((String) map.get("graduation_year")));
        }
        if(map.get("major")!=null){
            person.setMajor((String) map.get("major"));
        }
        if(map.get("phone_number")!=null){
            person.setPhoneNumber(Long.parseLong((String)map.get("phone_number")));
        }
        if(map.get("sex")!=null){
            person.setSex((String) map.get("sex"));
        }
        if(map.get("work_unit")!=null){
            person.setWorkUnit((String) map.get("work_unit"));
        }
        personRepository.save(person);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/person/{id}")
    public void deletePerson(@PathVariable("id") Integer id ){
        personRepository.delete(id);
    }

    @ApiOperation(value = "查找用户", notes = "根据用户的任意属性模糊匹配，返回List<Person>")
    @GetMapping(value = "/person/{attribute}")
    public List<Person> getPersonByAttribute(@PathVariable("attribute") String attribute){
        return personRepository.findByAttribute(attribute);
    }
}
