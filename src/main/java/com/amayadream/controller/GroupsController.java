package com.amayadream.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.pojo.Groups;
import com.amayadream.service.IGroupsService;
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
 * Date   :  2015.10.09 14:04
 * TODO   :
 */

@Controller
@SessionAttributes("id")
@RequestMapping("/groups")
public class GroupsController {
    @Resource
    private IGroupsService groupsService;

    @RequestMapping(value = "/all", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String show(@ModelAttribute("id") int userid, int page, Map map1, Map map2){
        int pageSize = 2;
        if(page==1){
            map1.put("startRow", 1);
            map1.put("endRow", pageSize);

        }
        else{
            map1.put("startRow", pageSize*(page-1)+1);
            map1.put("endRow", pageSize*page);
        }
        map1.put("userid",userid);
        Groups groups = this.groupsService.countGroups(userid);
        int rowCount = groups.getGid();
        List<Groups> list = this.groupsService.queryAllGroups(map1);
        if(rowCount%pageSize!=0){
            rowCount = rowCount/pageSize+1;
        }
        else{
            rowCount = rowCount/pageSize;
        }
        map2.put("pageCount", rowCount);
        map2.put("CurrentPage", page);
        map2.put("list", list);
        return JSON.toJSONString(map2);
    }

    @RequestMapping(value = "one", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Groups one(@RequestParam("id") int gid, @ModelAttribute("id") int userid){
        Groups groups = this.groupsService.queryGroupsByGid(gid, userid);
        return groups;
    }

    @RequestMapping("add")
    public String add(@RequestParam("name") String gname, @ModelAttribute("id") int userid, Map map, RedirectAttributes redirectAttributes){
        if(gname == null || gname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","分组名不能为空!");
            return "redirect:/groups";
        }else{
            if(gname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","分组名过短!");
                return "redirect:/groups";
            }else{
                Groups groups = this.groupsService.queryGroupsByGname(userid,gname);
                if(groups != null){
                    redirectAttributes.addFlashAttribute("ERROR","此分组已存在,请重试!!");
                    return "redirect:/groups";
                }else{
                    map.put("gname", gname);
                    map.put("userid",userid);
                    this.groupsService.insertGroups(map);
                    redirectAttributes.addFlashAttribute("INFO","添加成功!");
                    return "redirect:/groups";
                }
            }
        }
    }

    @RequestMapping("edit")
    public String edit(@RequestParam("name") String gname,@RequestParam("id") int gid, @ModelAttribute("id") int userid, RedirectAttributes redirectAttributes, Map map){
        if(gname == null || gname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","分组名不能为空,请重新输入!");
            return "redirect:/groups";
        }else{
            if(gname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","分组名过短,请重新输入!");
                return "redirect:/groups";
            }else{
                Groups groups = this.groupsService.queryGroupsByGname(userid,gname);
                if(groups != null){
                    redirectAttributes.addFlashAttribute("ERROR","分组名已存在,请重新输入!");
                    return "redirect:/groups";
                }else{
                    boolean flag = false;
                    System.out.println(userid);
                    System.out.printf(gname);
                    map.put("gname", gname);
                    map.put("userid", userid);
                    map.put("gid",gid);
                    flag = this.groupsService.updateGroups(map);
                    if(flag){
                        redirectAttributes.addFlashAttribute("INFO","修改成功!");
                        return "redirect:/groups";
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","未知原因导致修改失败,请重试!");
                        return "redirect:/groups";
                    }
                }
            }
        }
    }


    @RequestMapping("delete")
    public String delete(@RequestParam("id") int gid, @ModelAttribute("id") int userid, RedirectAttributes redirectAttributes){
        Groups groups = this.groupsService.queryGroupsByGid(gid, userid);
        if(groups == null){
            redirectAttributes.addFlashAttribute("ERROR","该分组不存在!");
            return "redirect:/groups";
        }else{
            if(this.groupsService.deleteGroups(gid, userid)){
                redirectAttributes.addFlashAttribute("INFO","删除成功!");
                return "redirect:/groups";
            }else{
                redirectAttributes.addFlashAttribute("INFO","未知原因导致删除失败!");
                return "redirect:/groups";
            }
        }
    }

}
