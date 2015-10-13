package com.amayadream.dao;

import com.amayadream.pojo.Contactor;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.dao
 * Author :  Amayadream
 * Date   :  2015.10.07 22:38
 * TODO   :
 */
public interface IContactorDao {
    public List<Contactor> selectAllContactor(Map<String, Object> map);

    public Contactor selectContactorByCid(Map<String, Object> map);

    public Contactor selectContactorByCname(Map<String, Object> map);

    public Contactor countContactor(int userid);

    public void insertContactor(Map<String, Object> map);

    public boolean updateContactor(Map<String, Object> map);

    public boolean deleteContactor(Map<String, Object> map);
}
