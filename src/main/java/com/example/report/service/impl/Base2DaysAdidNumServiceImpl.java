package com.example.report.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1PlanWork;
import com.example.report.entity.Base2DaysAdidNum;
import com.example.report.mapper.Base2DaysAdidNumMapper;
import com.example.report.service.IBase2DaysAdidNumService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每天点位详情表 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@Service
public class Base2DaysAdidNumServiceImpl extends ServiceImpl<Base2DaysAdidNumMapper, Base2DaysAdidNum> implements IBase2DaysAdidNumService {

    @Autowired
    private Base2DaysAdidNumMapper base2DaysAdidNumMapper;

    @Override
    public List<Map<String, Object>> sumGroupByDay(Base1PlanWork plan) {
        return base2DaysAdidNumMapper.sumGroupByDay(plan.getPf(), plan.getWvid());
    }

    @Override
    public List<Map<String, Object>> sumGroupByDrama(Base1PlanWork plan) {
        return base2DaysAdidNumMapper.sumGroupByDrama(plan.getPf(), plan.getWvid());
    }

    @Override
    public List<Map<String, Object>> sumGroupByMater(Base1PlanWork plan) {
        return base2DaysAdidNumMapper.sumGroupByMater(plan.getPf(), plan.getWvid());
    }
}
