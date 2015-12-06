package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Contact;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.11.27 17:32
 * TODO   :
 */
public interface IContactService {
    /**
     * 查询所有联系人
     * @param startRow  查询的起始点
     * @param endRow    查询的终结点
     * @param userid    用户id
     * @return
     */
    public List<Contact> selectAll(int startRow, int endRow, String userid);

    /**
     * 根据联系人id查询联系人信息
     * @param cid
     * @param userid
     * @return
     */
    public Contact selectContactById(String cid, String userid);

    /**
     * 根据联系人姓名查询联系人信息
     * @param name
     * @param userid
     * @return
     */
    public Contact selectContactByName(String name, String userid);

    /**
     * 根据分组查询指定分组下的联系人信息
     * @param groupid
     * @param userid
     * @return
     */
    public List<Contact> selectContactByGroupid(String groupid, String userid);

    public Contact selectContactByEmail(String userid, String email);

    /**
     * 查询联系人总数
     * @param userid
     * @return
     */
    public Contact count(String userid);

    /**
     * 查询某个分组的联系人总数
     * @param userid
     * @param groupid
     * @return
     */
    public Contact countByGroup(String userid, String groupid);

    /**
     * 插入联系人
     * @param userid
     * @param name
     * @param email
     * @param groupid
     * @return
     */
    public boolean insert(String userid, String name, String email, String groupid);

    /**
     * 更新联系人
     * @param cid
     * @param userid
     * @param name
     * @param email
     * @param groupid
     * @return
     */
    public boolean update(String cid, String userid, String name, String email, String groupid);

    /**
     * 删除联系人
     * @param cid
     * @param userid
     * @return
     */
    public boolean delete(String cid, String userid);
}
