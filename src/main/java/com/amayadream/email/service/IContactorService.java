package com.amayadream.email.service;

import com.amayadream.email.pojo.Contactor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * NAME   :  EmailSystem/com.amayadream.service
 * Author :  Amayadream
 * Date   :  2015.10.07 22:47
 * TODO   :
 */
public interface IContactorService {

    public List<Contactor> queryAllContactor(Map<String, Object> map);

    public Contactor queryContactorByCid(Map<String, Object> map);

    public Contactor queryContactorByCname(Map<String, Object> map);

    public Contactor countCount(int userid);

    public void insertContactor(Map<String, Object> map);

    public boolean updateContactor(Map<String, Object> map);

    public boolean deleteContactor(Map<String, Object> map);
}
