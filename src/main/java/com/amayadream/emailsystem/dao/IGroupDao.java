package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.11.27 21:38
 * TODO   :
 */
@Service("groupDao")
public interface IGroupDao {
    public List<Group> selectAll(Map<String, Object> map);
    public Group selectGroupByName(Group group);
    public Group selectGroupById(Group group);
    public Group count(String userid);
    public boolean insert(Group group);
    public boolean update(Group group);
    public boolean delete(Group group);
    public boolean deleteAllGroup(String userid);
}
