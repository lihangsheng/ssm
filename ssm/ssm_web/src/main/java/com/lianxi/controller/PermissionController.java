package com.lianxi.controller;

import com.lianxi.domain.Permission;
import com.lianxi.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        String uuid = UUID.randomUUID().toString();
        permission.setId(uuid);
        permissionService.save(permission);
        return  "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
   public ModelAndView findAll() throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
   }

}
