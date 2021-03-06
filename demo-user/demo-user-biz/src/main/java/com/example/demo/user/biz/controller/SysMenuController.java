package com.example.demo.user.biz.controller;

import com.example.demo.common.core.annotation.SysLogAn;
import com.example.demo.common.core.exception.RRException;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.core.utils.Constant;
import com.example.demo.user.api.entity.SysMenu;
import com.example.demo.user.api.request.MenuAddDTO;
import com.example.demo.user.api.request.MenuUpdateDTO;
import com.example.demo.user.biz.service.ShiroService;
import com.example.demo.user.biz.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.isismtt.ocsp.RequestedCertificate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author oyg
 * @Date 2018/7/21/19:33
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 导航菜单
     *
     * @return
     */
    @GetMapping("/nav")
    public AirResult nav() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList);
        map.put("permissions", permissions);
        return AirResult.success(map);
    }

    /**
     * 菜单列表
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('sys:menu:list')")
    public AirResult<List<SysMenu>> list() {
        List<SysMenu> menuList = sysMenuService.list(null);
        menuList.forEach(x -> {
            SysMenu parentMenu = sysMenuService.getById(x.getParentId());
            if (parentMenu != null) {
                x.setParentName(parentMenu.getName());
            }
        });
        return AirResult.ok(menuList);
    }

    /**
     * 选择菜单
     *
     * @return
     */
    @GetMapping("/select")
    @PreAuthorize("@ps.hasPermission('sys:menu:select')")
    public AirResult<List<SysMenu>> select() {
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return AirResult.ok(menuList);
    }

    /**
     * 菜单信息
     *
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @PreAuthorize("@ps.hasPermission('sys:menu:info')")
    public AirResult<SysMenu> info(@PathVariable("menuId") Long menuId) {
        SysMenu sysMenu = sysMenuService.getById(menuId);
        return AirResult.ok(sysMenu);
    }


    @SysLogAn("保存菜单")
    @PostMapping("/save")
    @PreAuthorize("@ps.hasPermission('sys:menu:save')")
    public AirResult save(RequestEntity<MenuAddDTO> requestEntity) {

        MenuAddDTO menuAddDTO = requestEntity.getBody();
        //数据校验
        verifyForm(menuAddDTO);

        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(menuAddDTO, sysMenu);

        sysMenuService.save(sysMenu);

        return AirResult.success();
    }

    @SysLogAn("修改菜单")
    @PutMapping("/update")
    @PreAuthorize("@ps.hasPermission('sys:menu:update')")
    public AirResult update(RequestEntity<MenuUpdateDTO> requestEntity) {

        MenuUpdateDTO menuUpdateDTO = requestEntity.getBody();
        verifyForm(menuUpdateDTO);

        SysMenu sysMenu = new SysMenu();

        BeanUtils.copyProperties(menuUpdateDTO, sysMenu);

        sysMenuService.updateById(sysMenu);

        return AirResult.success();

    }

    @SysLogAn("删除菜单")
    @DeleteMapping("/delete/{menuId}")
    @PreAuthorize("@ps.hasPermission('sys:menu:delete')")
    public AirResult delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 3L) {
            return AirResult.error("系统菜单，不能删除");
        }

        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return AirResult.error("请先删除子菜单或按钮");
        }

        boolean delete = sysMenuService.delete(menuId);

        return AirResult.success(delete);
    }

    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

    private void verifyForm(MenuAddDTO menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

//        if (menu.getParentId() == null) {
//            throw new RRException("上级菜单不能为空");
//        }

        Optional.ofNullable(menu.getParentId()).orElseThrow(()-> new RRException("上级菜单不能为空"));

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

    private void verifyForm(MenuUpdateDTO menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

    @GetMapping("/sing-thread/test")
    public AirResult singThreadTest(){
        sysMenuService.singThreadTest();
        return AirResult.ok();
    }
}
