package com.amayadream.service;

import com.amayadream.pojo.Groups;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.service
 * Author :  Amayadream
 * Date   :  2015.10.09 13:54
 * TODO   :
 */
public interface IGroupsService {
    public List<Groups> queryAllGroups(Map<String, Object> map);
    public Groups queryGroupsByGname(int userid, String gname);
    public Groups queryGroupsByGid(int gid, int userid);
    public Groups countGroups(int userid);
    public void insertGroups(Map<String, Object> map);
    public boolean updateGroups(Map<String, Object> map);
    public boolean deleteGroups(int gid, int userid);
    public boolean deleteAllGroups(int userid);
}
