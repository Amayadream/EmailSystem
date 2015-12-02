package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.pojo.Contact;
import com.amayadream.emailsystem.pojo.Group;
import com.amayadream.emailsystem.service.IContactService;
import com.amayadream.emailsystem.service.IGroupService;
import com.amayadream.emailsystem.util.Page;
import com.amayadream.emailsystem.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private IContactService contactService;
    @Resource
    private Group group;

    @RequestMapping(value = "", produces = "application/json;charset=utf-8")
    public String show(@ModelAttribute("userid") String userid, Model model, HttpServletRequest request){
        Page<Group> page = new Page<Group>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        List<Group> list = groupService.selectAll(pageParams[0]+1,pageParams[0]+pageParams[1],userid);
        int count = Integer.parseInt(groupService.count(userid).getGid());
        page.setTotalCount(count);
        page.setResult(list);
        model.addAttribute("page",page);
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
                    redirectAttributes.addFlashAttribute("ERROR","分组"+ groupname +"已存在!");
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
            Contact contact = contactService.countByGroup(userid, gid);
            if(Integer.parseInt(contact.getCid())==0){      //判断分组下是否存在用户
                boolean flag = groupService.delete(gid, userid);
                if(flag){
                    redirectAttributes.addFlashAttribute("INFO","删除成功!");
                }else{
                    redirectAttributes.addFlashAttribute("ERROR","删除失败,请重试!");
                }
            }else{
                redirectAttributes.addFlashAttribute("ERROR","该分组下还有联系人,请先删除联系人!");
            }

        }
        return "redirect:/group";
    }

}
