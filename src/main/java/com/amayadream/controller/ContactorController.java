package com.amayadream.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.pojo.Contactor;
import com.amayadream.service.IContactorService;
import com.amayadream.tools.Regex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.07 23:41
 * TODO   :
 */

@Controller
@SessionAttributes("id")
@RequestMapping(value = "contact")
public class ContactorController {

    @Resource
    private IContactorService contactorService;

    @RequestMapping(value = "/all", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Contactor> all(@ModelAttribute("id") int userid, int page, Map map, Map map2, HttpSession session){
        int pageSize = 10;
        if(page==1){
            map.put("startRow", 1);
            map.put("endRow", pageSize);
        }
        else{
            map.put("startRow", pageSize*(page-1)+1);
            map.put("endRow", pageSize*page);
        }
        map.put("userid", userid);
        Contactor contactor = this.contactorService.countCount(userid);
        int rowCount = contactor.getCid();
        List<Contactor> list = this.contactorService.queryAllContactor(map);
        if(rowCount%pageSize!=0){
            rowCount = rowCount/pageSize+1;
        }
        else{
            rowCount = rowCount/pageSize;
        }
        map2.put("pageCount", rowCount);
        map2.put("CurrentPage", page);
        map2.put("list", list);
        return list;
    }


    @RequestMapping(value = "/asd", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String asd(@ModelAttribute("id") int userid, int limit, int offset, Map map, HttpSession session){
        int startRow = offset;
        int endRow = offset + limit;
        map.put("startRow",startRow);
        map.put("endRow",endRow);
        map.put("userid",userid);
        Contactor contactor = this.contactorService.countCount(userid);
        int total = contactor.getCid();
        List<Contactor> list = this.contactorService.queryAllContactor(map);
        map.clear();
        map.put("total", total);
        map.put("rows",list);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Contactor id(@RequestParam("id") int cid, @ModelAttribute("id") int userid, Map map){
        map.put("userid", userid);
        map.put("cid",cid);;
        Contactor contactor = this.contactorService.queryContactorByCid(map);
        return contactor;
    }

    @RequestMapping(value = "/name", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Contactor name(@RequestParam("name") String cname, @ModelAttribute("id") int userid, Map map){
        map.put("userid",userid);
        map.put("cname",cname);
        Contactor contactor = this.contactorService.queryContactorByCname(map);
        return contactor;
    }

    @RequestMapping(value = "add")
    public String add(@RequestParam("name") String cname, @ModelAttribute("id") int userid, String email, int groups,
                               RedirectAttributes redirectAttributes, Regex regex, Map map1, Map map2){
        if(cname == null || cname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR", "姓名不能为空,请重新输入!");
            return "redirect:/contact";
        }else{
            if(cname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR", "姓名太短,请重新输入!");
                return "redirect:/contact";
            }else{
                map1.put("cname",cname);
                map1.put("userid",userid);
                //验证联系人是否存在
                if(this.contactorService.queryContactorByCname(map1) == null){
                    //验证邮箱是否符合规则
                    if(regex.checkEmail(email)){
                        if((Integer)groups instanceof Integer){
                            map2.put("cname",cname);
                            map2.put("userid",userid);
                            map2.put("email",email);
                            map2.put("groups",groups);
                            this.contactorService.insertContactor(map2);
                            redirectAttributes.addFlashAttribute("INFO", cname + "已成功添加到通讯录!");
                            return "redirect:/contact";
                        }else{
                            redirectAttributes.addFlashAttribute("ERROR","分组错误,请重新输入!");
                            return "redirect:/contact";
                        }
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","邮箱" + email + "不符合规则,请重新输入!");
                        return "redirect:/contact";
                    }
                }else{
                    redirectAttributes.addFlashAttribute("ERROR","联系人"+ cname +"已存在!");
                    return "redirect:/contact";
                }
            }
        }
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam("id") int cid,@RequestParam("name") String cname, @ModelAttribute("id") int userid, String email, int groups,
                                RedirectAttributes redirectAttributes, Regex regex, Map map1, Map map2){
        if(cname == null || cname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","姓名不能为空,请重新输入!");
            return "redirect:/contact";
        }else{
            if(cname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","姓名过短,请重新输入!");
                return "redirect:/contact";
            }else{
                map1.put("cname",cname);
                map1.put("userid",userid);
                if(this.contactorService.queryContactorByCname(map1) == null){
                    if(regex.checkEmail(email)){
                        if((Integer)groups instanceof Integer){
                            boolean flag = false;
                            map2.put("cid",cid);
                            map2.put("cname",cname);
                            map2.put("email",email);
                            map2.put("groups", groups);
                            flag = this.contactorService.updateContactor(map2);
                            if(flag == true){
                                redirectAttributes.addFlashAttribute("INFO","编辑" + cname + "成功!");
                                return "redirect:/contact";
                            }else{
                                redirectAttributes.addFlashAttribute("ERROR","未知原因导致添加失败,请重试");
                                return "redirect:/contact";
                            }
                        }else{
                            redirectAttributes.addFlashAttribute("ERROR","分组信息错误,请重试");
                            return "redirect:/contact";
                        }
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","邮箱" + email + "不符合规则,请重新输入!");
                        return "redirect:/contact";
                    }
                }else {
                    redirectAttributes.addFlashAttribute("ERROR","联系人" + cname + "已存在!");
                    return "redirect:/contact";
                }
            }
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int cid, @ModelAttribute("id") int userid, RedirectAttributes redirectAttributes, HttpSession session, Map map1, Map map2){
        map1.put("cid",cid);
        map1.put("userid",userid);
        if(this.contactorService.queryContactorByCid(map1) == null){
            redirectAttributes.addFlashAttribute("ERROR","此联系人不存在!");
            return "redirect:/contact";
        }else{
            boolean flag = false;
            map2.put("cid",cid);
            map2.put("userid",userid);
            flag = this.contactorService.deleteContactor(map2);
            if(flag == true){
                redirectAttributes.addFlashAttribute("INFO","删除成功!");
                return "redirect:/contact";
            }else{
                redirectAttributes.addFlashAttribute("ERROR","未知原因导致删除失败,请重试!");
                return "redirect:/contact";
            }
        }
    }

}
