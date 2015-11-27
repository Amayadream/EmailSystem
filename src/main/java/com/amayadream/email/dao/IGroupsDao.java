package com.amayadream.email.dao;

import com.amayadream.email.pojo.Groups;
import com.amayadream.email.pojo.Groups;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.dao
 * Author :  Amayadream
 * Date   :  2015.10.09 11:28
 * TODO   :
 */

@Repository("groupsDao")
public interface IGroupsDao {
    public List<Groups> selectAllGroups(Map<String, Object> map);
    public Groups selectGroupsByGname(Map<String, Object> map);
    public Groups selectGroupsByGid(Map<String, Object> map);
    public Groups countGroups(int userid);
    public void insertGroups(Map<String, Object> map);
    public boolean updateGroups(Map<String, Object> map);
    public boolean deleteGroups(Map<String, Object> map);
    public boolean deleteAllGroups(int userid);
}
