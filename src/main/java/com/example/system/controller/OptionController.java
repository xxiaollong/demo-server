package com.example.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.system.annotation.PermInfo;
import com.example.system.entity.SysRole;
import com.example.system.service.SysRoleService;
import com.example.system.vo.Json;
import com.example.system.vo.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by CaiBaoHong at 2018/4/17 16:41<br>
 */
@PermInfo(value = "选项模块", pval = "a:option")
@RestController
@RequestMapping("/option")
public class OptionController {

    private static final Logger log = LoggerFactory.getLogger(OptionController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/role")
    public Json listRoleOptions() {
        String oper = "list role options";
        log.info(oper);

        EntityWrapper<SysRole> params = new EntityWrapper<>();
        params.setSqlSelect("rid,rname,rval");

        List<SysRole> list = sysRoleService.selectList(params);
        List<Option> options = list.stream().map(obj -> new Option(obj.getRid(), obj.getRname(),obj.getRval())).collect(Collectors.toList());
        return Json.succ(oper, "options", options);
    }


}
