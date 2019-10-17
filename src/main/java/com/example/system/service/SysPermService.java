package com.example.system.service;

import com.example.system.entity.SysPerm;
import com.example.system.vo.AuthVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

public interface SysPermService extends IService<SysPerm> {

    Set<AuthVo> getPermsByUserId(String userId);

    List<SysPerm> getPermsByRoleId(String roleId);

    void saveOrUpdate(List<SysPerm> perms);



}
