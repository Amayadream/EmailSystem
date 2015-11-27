package com.amayadream.email.serviceImpl;

import com.amayadream.email.dao.IGroupsDao;
import com.amayadream.email.service.IGroupsService;
import com.amayadream.email.pojo.Groups;
import com.amayadream.email.service.IGroupsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.10.09 13:56
 * TODO   :
 */

@Service("groupsService")
public class GroupsServiceImpl implements IGroupsService {
    @Resource
    private IGroupsDao groupsDao;


    public List<Groups> queryAllGroups(Map<String, Object> map) {
        return this.groupsDao.selectAllGroups(map);
    }

    public Groups queryGroupsByGid(int gid, int userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gid",gid);
        map.put("userid",userid);
        return this.groupsDao.selectGroupsByGid(map);
    }

    public Groups queryGroupsByGname(int userid, String gname){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid",userid);
        map.put("gname",gname);
        return this.groupsDao.selectGroupsByGname(map);
    }

    public Groups countGroups(int userid) {
        return this.groupsDao.countGroups(userid);
    }

    public void insertGroups(Map<String, Object> map) {
        this.groupsDao.insertGroups(map);
    }

    public boolean updateGroups(Map<String, Object> map) {
        return this.groupsDao.updateGroups(map);
    }

    public boolean deleteGroups(int gid, int userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gid",gid);
        map.put("userid",userid);
        return this.groupsDao.deleteGroups(map);
    }

    public boolean deleteAllGroups(int userid) {
        return this.groupsDao.deleteAllGroups(userid);
    }
}
