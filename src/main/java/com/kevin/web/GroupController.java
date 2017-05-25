package com.kevin.web;

import com.kevin.domain.CGroup;
import com.kevin.domain.GroupRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by min on 2017/5/25.
 */
@RestController
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @ApiOperation(value = "添加小组")
    @PostMapping(value = "/group/creation")
    public Map postGroup(@RequestParam("text") String text,
                        @RequestParam("groupname") String groupName){
        Map<String,Object> map = new HashMap<>();
        int flag = 0;
        if(groupRepository.findByGroupName(groupName)!=null){
            flag = 1; // 已经存在这个名字的组
        }
        if (Objects.equals(text, "") || Objects.equals(groupName, "") || flag == 1) {
            map.put("message","没有插入数据");
            return map;
        }else {
            CGroup group = new CGroup();
            group.setText(text);
            group.setGroupName(groupName);
            groupRepository.save(group);
            map.put("success",true);
            map.put("message","插入了新的分组");
            map.put("group", group);
            return map;
        }
    }

    @ApiOperation(value = "删除分组")
    @DeleteMapping(value = "/group/{id}")
    public void deleteGroup(@PathVariable("id") Integer id){
        groupRepository.delete(id);
    }

    @ApiOperation(value = "查询所有分组")
    @GetMapping(value="/groups")
    public List<CGroup> getGroupsList(){
        return groupRepository.findAll();
    }
}
