package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Group;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.11.27 21:42
 * TODO   :
 */
public interface IGroupService {
    public List<Group> selectAll(String userid, int pageNo, int pageSize);
    public Group selectGroupByName(String userid, String groupname);
    public Group selectGroupById(String userid, String gid);
    public Group count(String userid);
    public boolean insert(String userid, String groupname);
    public boolean update(String gid, String userid, String groupname);
    public boolean delete(String gid, String userid);
    public boolean deleteAllGroup(String userid);
}