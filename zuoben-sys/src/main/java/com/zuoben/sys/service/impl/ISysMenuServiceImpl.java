package com.zuoben.sys.service.impl;

import com.zuoben.context.BaseContextHandler;
import com.zuoben.enums.ZuoBenEnum;
import com.zuoben.sys.mapper.SysMenuMapper;
import com.zuoben.sys.mapper.SysRoleMenuMapper;
import com.zuoben.sys.mapper.SysUserRoleMapper;
import com.zuoben.sys.model.*;
import com.zuoben.sys.service.ISysMenuService;
import com.zuoben.sys.vo.MenuTree;
import com.zuoben.util.TreeUtil;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.util.resultUtils.SafeExecutor;
import com.zuoben.vo.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 菜单实现类
 * @author zuoben
 */
@Service
public class ISysMenuServiceImpl implements ISysMenuService {
    private Logger logger = LoggerFactory.getLogger(ISysMenuServiceImpl.class);

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public JsonResult<List<MenuTree>> selectUserMenu() {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        Integer userID = BaseContextHandler.getUserID();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(userID);

        // 该用户所有的菜单id
        List<Integer> srmLists = new ArrayList<>();
        // 查询该用户的角色
        List<SysUserRole> surList = sysUserRoleMapper.selectByExample(sysUserRoleExample);

        for(int i = 0; i < surList.size(); i++) {
            SysUserRole  sysUserRole = surList.get(i);

            // 查询该角色所有的菜单
            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysUserRole.getRoleId());
            List<SysRoleMenu> srmList = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
            for (int j = 0; j < srmList.size(); j++) {
                srmLists.add(srmList.get(j).getMenuId());
            }
        }

        List<MenuTree> trees = selectMT(srmLists);
        List<MenuTree> menuTree = TreeUtil.bulid(trees, -1);
        return JsonResult.success(menuTree);
    }

    @Override
    public JsonResult<List<MenuTree>> list() {
        return SafeExecutor.noTranExecute(() -> {
            List<MenuTree> trees = selectMT(null);
            List<MenuTree> menuTree = TreeUtil.bulid(trees, -1);
            return JsonResult.success(menuTree);
        });
    }

    @Override
    public JsonResult<MenuTree> selectById(Integer id) {
        List<MenuTree> trees = selectMT(null);
        SysMenu menu = this.sysMenuMapper.selectByPrimaryKey(id);
        MenuTree menuTree = new MenuTree();
        BeanUtils.copyProperties(menu, menuTree);
        menuTree.setSituation(menu.getState());
        return JsonResult.success(TreeUtil.findChildren(menuTree, trees));
    }

    @Override
    public JsonResult insert(MenuTree sysMenu) {
        try {
            SysMenu sm = new SysMenu();
            BeanUtils.copyProperties(sysMenu, sm);
            sm.setState(sysMenu.getSituation());
            this.sysMenuMapper.insertSelective(sm);
        } catch (Exception var3) {
            return JsonResult.fail("新增失败");
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult update(MenuTree sysMenu) {
        try {
            SysMenu sm = new SysMenu();
            BeanUtils.copyProperties(sysMenu, sm);
            sm.setState(sysMenu.getSituation());
            sm.setParentId((Integer)null);
            this.sysMenuMapper.updateByPrimaryKeySelective(sm);
        } catch (Exception var3) {
            return JsonResult.fail("更新失败");
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult disableMenu(Integer id) {
        try {
            SysMenu sysMenu = new SysMenu();
            MenuTree menuTree = this.selectById(id).getData();
            sysMenu.setId(menuTree.getId());
            sysMenu.setState(ZuoBenEnum.State.WITHDRAW.getVal());
            this.sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
            Iterator var4 = menuTree.getChildren().iterator();

            while(var4.hasNext()) {
                TreeNode treeNode1 = (TreeNode)var4.next();
                sysMenu.setId(treeNode1.getId());
                this.sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
                Iterator var6 = treeNode1.getChildren().iterator();

                while(var6.hasNext()) {
                    TreeNode treeNode2 = (TreeNode)var6.next();
                    sysMenu.setId(treeNode2.getId());
                    this.sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
                }
            }
        } catch (Exception var8) {
            return JsonResult.fail("禁用失败");
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult deleteMenu(Integer id) {
        try {
            MenuTree menuTree = (MenuTree)this.selectById(id).getData();
            List<Integer> list = new ArrayList();
            list.add(menuTree.getId());
            Iterator var4 = menuTree.getChildren().iterator();

            label23:
            while(true) {
                if (var4.hasNext()) {
                    TreeNode treeNode1 = (TreeNode)var4.next();
                    list.add(treeNode1.getId());
                    Iterator var6 = treeNode1.getChildren().iterator();

                    while(true) {
                        if (!var6.hasNext()) {
                            continue label23;
                        }

                        TreeNode treeNode2 = (TreeNode)var6.next();
                        list.add(treeNode2.getId());
                    }
                }

                SysMenuExample sysMenuExample = new SysMenuExample();
                sysMenuExample.createCriteria().andIdIn(list);
                this.sysMenuMapper.deleteByExample(sysMenuExample);
                return JsonResult.success();
            }
        } catch (Exception var8) {
            return JsonResult.fail("删除失败");
        }
    }

    /**
     * 获取所有的菜单
     */
    List<MenuTree> selectMT(List<Integer> list) {
        SysMenuExample sysMenuExample = new SysMenuExample();
        if(list != null) {
            sysMenuExample.createCriteria().andIdIn(list);
        }
        sysMenuExample.setOrderByClause("sort desc");
        List<SysMenu> menus = this.sysMenuMapper.selectByExample(sysMenuExample);
        List<MenuTree> trees = new LinkedList();
        MenuTree node = null;
        Iterator var5 = menus.iterator();

        while(var5.hasNext()) {
            node = new MenuTree();
            SysMenu menu = (SysMenu)var5.next();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return trees;
    }
}
