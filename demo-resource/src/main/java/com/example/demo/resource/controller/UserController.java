package com.example.demo.resource.controller;

//import com.example.demo.common.security.component.PermissionService;
import com.example.demo.common.security.component.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2018/11/12/22:27
 */
@RestController
public class UserController {

    @GetMapping(value = "/t")
    public String t(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "苏杉杉";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/t2")
    public String t2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "苏杉杉";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/t3")
    public String t3(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "苏杉杉";
    }


    @PreAuthorize("@ps.hasPermission('sys_dept_add')")
    @GetMapping(value = "/t5")
    public String t5(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "苏杉杉";
    }

    @PreAuthorize("@ps.hasPermission('sys_dept_update')")
    @GetMapping(value = "/t6")
    public String t6(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "苏杉杉";
    }
}
