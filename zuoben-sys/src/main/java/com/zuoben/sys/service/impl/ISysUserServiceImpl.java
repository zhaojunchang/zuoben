package com.zuoben.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuoben.context.BaseContextHandler;
import com.zuoben.enums.ZuoBenEnum;
import com.zuoben.sys.dto.SysUserDTO;
import com.zuoben.sys.mapper.SysRoleMapper;
import com.zuoben.sys.mapper.SysUserMapper;
import com.zuoben.sys.mapper.SysUserRoleMapper;
import com.zuoben.sys.model.*;
import com.zuoben.sys.service.ISysUserService;
import com.zuoben.sys.vo.SysUserVO;
import com.zuoben.util.Md5Util;
import com.zuoben.util.StringUtils;
import com.zuoben.util.page.PageUtils;
import com.zuoben.util.resultUtils.JsonResult;
import com.zuoben.util.resultUtils.SafeExecutor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.swing.*;

import com.zuoben.vo.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.zuoben.util.resultUtils.Guard.argumentNotNull;
import static com.zuoben.util.resultUtils.Guard.argumentNotNullOrEmpty;

/**
 * 系统用户ServiceImpl
 * @author zuoben
 */
@Service
public class ISysUserServiceImpl implements ISysUserService {

    private Logger logger = LoggerFactory.getLogger(ISysUserServiceImpl.class);

    @Value("${user.salt}")
    private String salt;

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public JsonResult validate(String username, String password) {
        return SafeExecutor.noTranExecute(() -> {
            argumentNotNullOrEmpty(username, "账号不能为空");
            argumentNotNullOrEmpty(password, "密码不能为空");

            String password0 = Md5Util.md5(password, salt);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserNameEqualTo(username).andPasswordEqualTo(password0);

            List<SysUser> sysUser = sysUserMapper.selectByExample(sysUserExample);

            if(sysUser == null || sysUser.size() <= 0) {
                return JsonResult.fail("用户名密码不正确");
            }
            else if(!sysUser.get(0).getState().equals(ZuoBenEnum.State.SELLING.getVal())) {
                return JsonResult.fail("账户已禁用");
            }

            UserInfo userInfo = new UserInfo();
            userInfo.setId(sysUser.get(0).getId().toString());
            userInfo.setPhonenum(sysUser.get(0).getPhone());
            userInfo.setPassword(password0);
            userInfo.setUsername(sysUser.get(0).getUserName());
            return JsonResult.success(userInfo);
        });
    }

    @Override
    public JsonResult list(String userName, Integer page, Integer limit) {
        return SafeExecutor.noTranExecute(() -> {
            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria sc =  sysUserExample.createCriteria();
            if (!StringUtils.isEmpty(userName)) {
                sc.andUserNameLike("%" + userName + "%");
            }

            sc.andStateNotEqualTo(ZuoBenEnum.State.DELETE.getVal());
            if (!page.equals(ZuoBenEnum.Page.PAGE.getVal())
                    && !limit.equals(ZuoBenEnum.Page.LIMIT.getVal()))
            {
                PageHelper.startPage(page, limit);
            }

            List<SysUser> sysUserList = this.sysUserMapper.selectByExample(sysUserExample);
            List<SysUserVO> sysUserVOList = new ArrayList<>();
            PageInfo<SysUser> pages = new PageInfo<>(sysUserList);
            for (int i = 0; i < sysUserList.size(); i++) {
                SysUserVO sysUserVO = new SysUserVO();
                BeanUtils.copyProperties(sysUserList.get(i), sysUserVO);
                sysUserVOList.add(sysUserVO);
            }
            return JsonResult.success(PageUtils.transData(pages, sysUserVOList));
        });
    }

    @Override
    public JsonResult<SysUserVO> selectById(Integer id) {
        return SafeExecutor.execute(() -> {
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(id);
            List<SysUserRole> list = this.sysUserRoleMapper.selectByExample(sysUserRoleExample);
            SysUserVO sysUserVO = new SysUserVO();

            for (SysUserRole sysUserRole :  list) {
                sysUserVO.setRoleId(sysUserRole.getRoleId());
            }

            SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(id);
            if(sysUser != null) {
                BeanUtils.copyProperties(sysUser, sysUserVO);
            }
            return JsonResult.success(sysUserVO);
        });
    }

    @Override
    public JsonResult select() {
        return SafeExecutor.execute(() -> {
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(BaseContextHandler.getUserID());
            List<SysUserRole> list = this.sysUserRoleMapper.selectByExample(sysUserRoleExample);
            SysUserVO sysUserVO = new SysUserVO();

            for (SysUserRole sysUserRole :  list) {
                SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId());
                sysUserVO.setRoleId(sysUserRole.getRoleId());
                sysUserVO.setRoleName(sysRole.getName());
            }

            SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(BaseContextHandler.getUserID());
            BeanUtils.copyProperties(sysUser, sysUserVO);
            return JsonResult.success(sysUserVO);
        });
    }

    @Override
    public JsonResult insert(SysUserDTO sysUserDTO) {
        return SafeExecutor.execute(() -> {
            JsonResult<PageInfo<SysUserVO>> jsonResult = list(sysUserDTO.getUserName(), -1, -1);
            Integer list = jsonResult.getData().getList().size();

            if(list > 0) {
                return JsonResult.fail("该账号已存在");
            }

            SysUser sysUser = new SysUser();
            sysUser.setState(ZuoBenEnum.State.SELLING.getVal());
            BeanUtils.copyProperties(sysUserDTO, sysUser);
            String password0 = Md5Util.md5(sysUser.getPassword(), salt);
            sysUser.setPassword(password0);
            sysUser.setCreateId(BaseContextHandler.getUserID());
            sysUser.setCreateTime(new Date());
            this.sysUserMapper.insertSelective(sysUser);
            sysUserDTO.setId(sysUser.getId());
            this.deleteAndInsertUserMenu(sysUserDTO, false);
            return  JsonResult.success();
        });
    }

    @Override
    public JsonResult update(SysUserDTO sysUserDTO) {
        return SafeExecutor.execute(() -> {
            SysUserVO sysUserVO = selectById(sysUserDTO.getId()).getData();

            if(!sysUserVO.getUserName().equals(sysUserDTO.getUserName())) {
                JsonResult<PageInfo<SysUserVO>> jsonResult = list(sysUserDTO.getUserName(), -1, -1);
                Integer list = jsonResult.getData().getList().size();

                if(list > 0) {
                    return JsonResult.fail("该账号已存在");
                }
            }

            SysUser sysUser = new SysUser();
            String password0 = Md5Util.md5(sysUserDTO.getPassword(), salt);
            sysUserDTO.setPassword(password0);
            BeanUtils.copyProperties(sysUserDTO, sysUser);
            sysUser.setUpdateId(BaseContextHandler.getUserID());
            sysUser.setUpdateTime(new Date());
            this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
            this.deleteAndInsertUserMenu(sysUserDTO, true);
            return  JsonResult.success();
        });
    }

    @Override
    public JsonResult disable(Integer id) {
        return SafeExecutor.execute(() -> {
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setUpdateId(BaseContextHandler.getUserID());
            sysUser.setUpdateTime(new Date());
            sysUser.setState(ZuoBenEnum.State.WITHDRAW.getVal());
            this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult delete(Integer id) {
        return SafeExecutor.execute(() -> {
            this.sysUserMapper.deleteByPrimaryKey(id);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult deletes(String ids) {
        return SafeExecutor.execute(() -> {
            List<Integer> list = StringUtils.strToStrArr(ids);
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andIdIn(list);
            this.sysUserMapper.deleteByExample(sysUserExample);
            return JsonResult.success();
        });
    }

    @Override
    public JsonResult getUserStateByUserId(Integer userId) {
        return SafeExecutor.noTranExecute(() -> {
            argumentNotNull(userId, "userId为空");
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andIdEqualTo(userId);
            List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
            if (list != null && list.size() > 0) {
                if (!ZuoBenEnum.State.SELLING.getVal().equals(list.get(0).getState())) {
                    return JsonResult.fail("账户状态异常");
                }
                return JsonResult.success();
            }
            return JsonResult.fail("账户不存在");
        });
    }

    /**
     * 删除该用户的角色添加新角色
     * @param sysUserDTO dto
     * @param bool true?删除用户的角色旧数据:不删除
     * @return
     */
    private JsonResult deleteAndInsertUserMenu(SysUserDTO sysUserDTO, boolean bool) {
        return SafeExecutor.execute(() -> {
            if (bool) {
                SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
                sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUserDTO.getId());
                this.sysUserRoleMapper.deleteByExample(sysUserRoleExample);
            }

            if (sysUserDTO.getRoleList() != null) {
                sysUserDTO.getRoleList().forEach((roleId) -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUserDTO.getId());
                    sysUserRole.setRoleId(roleId);
                    this.sysUserRoleMapper.insertSelective(sysUserRole);
                });
            }

            return JsonResult.success();
        });
    }
}
