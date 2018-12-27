package com.zuoben.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuoben.context.BaseContextHandler;
import com.zuoben.enums.ZuoBenEnum;
import com.zuoben.sys.dto.SysRoleDTO;
import com.zuoben.sys.mapper.SysRoleMapper;
import com.zuoben.sys.mapper.SysRoleMenuMapper;
import com.zuoben.sys.model.SysRole;
import com.zuoben.sys.model.SysRoleExample;
import com.zuoben.sys.model.SysRoleMenu;
import com.zuoben.sys.model.SysRoleMenuExample;
import com.zuoben.sys.service.ISysRoleService;
import com.zuoben.sys.vo.SysRoleVO;
import com.zuoben.util.StringUtils;
import com.zuoben.util.page.PageUtils;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.util.resultUtils.SafeExecutor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ISysRoleServiceImpl implements ISysRoleService {
    private Logger logger = LoggerFactory.getLogger(ISysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public JsonResult<PageInfo> list(String name, Integer page, Integer limit) {
        return SafeExecutor.noTranExecute(() -> {
            SysRoleExample sysRoleExample = new SysRoleExample();
            SysRoleExample.Criteria sc  = sysRoleExample.createCriteria();
            if (!StringUtils.isEmpty(name)) {
                sc.andNameLike("%" + name + "%");
            }

            sc.andCIdEqualTo(BaseContextHandler.getCId());
            sc.andStateNotEqualTo(ZuoBenEnum.State.DELETE.getVal());
            if (page != -1 && limit != -1) {
                PageHelper.startPage(page, limit);
            }

            List<SysRole> sysRoleList = this.sysRoleMapper.selectByExample(sysRoleExample);
            List<SysRoleVO> sysRoleVOList = new ArrayList<>();
            PageInfo<SysRole> pages = new PageInfo<>(sysRoleList);
            for (int i = 0; i < sysRoleList.size(); i++) {
                SysRoleVO sysRoleVO = new SysRoleVO();
                BeanUtils.copyProperties(sysRoleList.get(i), sysRoleVO);
                sysRoleVOList.add(sysRoleVO);
            }

            return JsonResult.success(PageUtils.transData(pages, sysRoleVOList));
        });
    }

    @Override
    public JsonResult<SysRoleVO> selectById(Integer id) {
        SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
        sysRoleMenuExample.createCriteria().andRoleIdEqualTo(id);
        List<SysRoleMenu> list = this.sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        List<Integer> menuList = new ArrayList();
        SysRoleVO sysRoleVO = new SysRoleVO();
        list.forEach((srm) -> {
            menuList.add(srm.getMenuId());
        });
        SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(sysRole, sysRoleVO);
        sysRoleVO.setMenuList(menuList);
        return JsonResult.success(sysRoleVO);
    }

    @Override
    public JsonResult insert(SysRoleDTO sysRoleDTO) {
        return SafeExecutor.execute(() -> {
            SysRole sysRole = new SysRole();
            sysRole.setState(ZuoBenEnum.State.SELLING.getVal());
            BeanUtils.copyProperties(sysRoleDTO, sysRole);
            sysRole.setcId(BaseContextHandler.getCId());
            this.sysRoleMapper.insertSelective(sysRole);
            sysRoleDTO.setId(sysRole.getId());
            JsonResult jsonResult = this.deleteAndInsertRoleMenu(sysRoleDTO, false);
            return jsonResult;
        });
    }

    @Override
    public JsonResult update(SysRoleDTO sysRoleDTO) {
        return SafeExecutor.execute(() -> {
            SysRole sysRole = new SysRole();
            BeanUtils.copyProperties(sysRoleDTO, sysRole);
            sysRole.setUpdateId(BaseContextHandler.getUserID());
            sysRole.setUpdateTime(new Date());
            this.sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            JsonResult jsonResult = this.deleteAndInsertRoleMenu(sysRoleDTO, true);
            return jsonResult;
        });
    }

    @Override
    public JsonResult disable(Integer id) {
        return SafeExecutor.execute(() -> {
            SysRole sysRole = new SysRole();
            sysRole.setId(id);
            sysRole.setUpdateId(BaseContextHandler.getUserID());
            sysRole.setUpdateTime(new Date());
            sysRole.setState(ZuoBenEnum.State.WITHDRAW.getVal());
            this.sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult delete(Integer id) {
        return SafeExecutor.execute(() -> {
            this.sysRoleMapper.deleteByPrimaryKey(id);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult deletes(String ids) {
        return SafeExecutor.execute(() -> {
            List<Integer> list = StringUtils.strToStrArr(ids);
            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            sysRoleMenuExample.createCriteria().andRoleIdIn(list);
            this.sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);
            SysRoleExample sysRoleExample = new SysRoleExample();
            sysRoleExample.createCriteria().andIdIn(list);
            this.sysRoleMapper.deleteByExample(sysRoleExample);
            return JsonResult.success();
        });
    }

    private JsonResult deleteAndInsertRoleMenu(SysRoleDTO sysRoleDTO, boolean bool) {
        return SafeExecutor.execute(() -> {
            if (bool) {
                SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
                sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysRoleDTO.getId());
                this.sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);
            }

            if (sysRoleDTO.getMenuList() != null) {
                sysRoleDTO.getMenuList().forEach((menuId) -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setMenuId(menuId);
                    sysRoleMenu.setRoleId(sysRoleDTO.getId());
                    this.sysRoleMenuMapper.insertSelective(sysRoleMenu);
                });
            }

            return JsonResult.success();
        });
    }
}
