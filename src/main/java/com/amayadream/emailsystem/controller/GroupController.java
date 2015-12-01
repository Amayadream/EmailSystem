package com.amayadream.emailsystem.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.emailsystem.pojo.Group;
import com.amayadream.emailsystem.service.IGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.29 18:31
 * TODO   :
 */
@Controller
@RequestMapping("/group")
@SessionAttributes("userid")
public class GroupController {
    @Resource
    private IGroupService groupService;
    @Resource
    private Group group;

    @RequestMapping(value = "", produces = "application/json;charset=utf-8")
    public String show(@ModelAttribute("userid") String userid, @RequestParam(value = "page", defaultValue = "1") int pageNo, Model model){
        int pageSize = 10;
        List<Group> list = groupService.selectAll(userid, pageNo, pageSize);
        model.addAttribute("result",list);
        return "apps/emailsystem/group";
    }

    @RequestMapping(value = "id", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Group id(@RequestParam("id") String gid, @ModelAttribute("userid") String userid){
        return groupService.selectGroupById(userid, gid);
    }

    @RequestMapping("add")
    public String add(@RequestParam("name") String groupname, @ModelAttribute("userid") String userid, RedirectAttributes redirectAttributes){
        if(groupname == null || groupname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","分组名不能为空!");
        }else{
            if(groupname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","分组名过短!");
            }else{
                Group group = groupService.selectGroupByName(userid,groupname);
                if(group != null){
                    redirectAttributes.addFlashAttribute("ERROR","此分组已存在,请重试!!");
                }else{
                    boolean flag = groupService.insert(userid, groupname);
                    if(flag){
                        redirectAttributes.addFlashAttribute("INFO","添加成功!");
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","添加失败,请重试!");
                    }
                }
            }
        }
        return "redirect:/group";
    }

    @RequestMapping("edit")
    public String edit(@RequestParam("name") String groupname,@RequestParam("id") String gid, @ModelAttribute("userid") String userid, RedirectAttributes redirectAttributes, Map map){
        if(groupname == null || groupname.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","分组名不能为空,请重新输入!");
        }else{
            if(groupname.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","分组名过短,请重新输入!");
            }else{
                Group group = groupService.selectGroupByName(userid,groupname);
                if(group != null){
                    redirectAttributes.addFlashAttribute("ERROR","分组名已存在,请重新输入!");
                }else{
                    boolean flag = groupService.update(gid, userid, groupname);
                    if(flag){
                        redirectAttributes.addFlashAttribute("INFO","修改成功!");
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","修改失败,请重试!");
                    }
                }
            }
        }
        return "redirect:/group";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") String gid, @ModelAttribute("userid") String userid, RedirectAttributes redirectAttributes){
        Group group = groupService.selectGroupById(userid, gid);
        if(group == null){
            redirectAttributes.addFlashAttribute("ERROR","该分组不存在!");
        }else{
            boolean flag = groupService.delete(gid, userid);
            if(flag){
                redirectAttributes.addFlashAttribute("INFO","删除成功!");
            }else{
                redirectAttributes.addFlashAttribute("INFO","删除失败,请重试!");
            }
        }
        return "redirect:/group";
    }

}
