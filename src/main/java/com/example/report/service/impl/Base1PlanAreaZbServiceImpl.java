package com.example.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1PlanAreaZb;
import com.example.report.entity.Base1PlanDaysNum;
import com.example.report.entity.Base1PlanWork;
import com.example.report.mapper.Base1PlanAreaZbMapper;
import com.example.report.mapper.Base1PlanWorkMapper;
import com.example.report.service.IBase1PlanAreaZbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.report.service.IBase1PlanWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地区占比 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@Service
public class Base1PlanAreaZbServiceImpl extends ServiceImpl<Base1PlanAreaZbMapper, Base1PlanAreaZb> implements IBase1PlanAreaZbService {

    @Autowired
    private Base1PlanWorkMapper base1PlanWorkMapper;

    @Override
    public List<Base1PlanAreaZb> selectByWvid(Base1PlanWork plan) {
        EntityWrapper<Base1PlanAreaZb> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", plan.getPf());
        wrapper.eq("wvid", plan.getWvid());

        return baseMapper.selectList(wrapper);
    }

    @Override
    public boolean saveList(String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        List<Base1PlanAreaZb> areas = JSON.parseArray(jsonObject.getString("areas"), Base1PlanAreaZb.class);

        // 删除数据
        EntityWrapper<Base1PlanAreaZb> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", areas.get(0).getPf());
        wrapper.eq("wvid", areas.get(0).getWvid());
        baseMapper.delete(wrapper);

        // 写入数据
        for (Base1PlanAreaZb area : areas) {
            area.setId(null);
            baseMapper.insert(area);
        }

        // 更新计划状态
        Base1PlanWork temp = new Base1PlanWork();
        temp.setId(jsonObject.getInteger("planId"));
        temp.setPlanAreaZb(1);

        Integer i = base1PlanWorkMapper.updateById(temp);

        return i == 1;
    }
}
