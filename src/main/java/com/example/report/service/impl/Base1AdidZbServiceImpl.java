package com.example.report.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1AdidZb;
import com.example.report.entity.Base1PlanWork;
import com.example.report.mapper.Base1AdidZbMapper;
import com.example.report.service.IBase1AdidZbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点位详情表 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
@Service
public class Base1AdidZbServiceImpl extends ServiceImpl<Base1AdidZbMapper, Base1AdidZb> implements IBase1AdidZbService {

    @Override
    public List<Base1AdidZb> groupByDrama(Base1PlanWork plan) {
        EntityWrapper<Base1AdidZb> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", plan.getPf());
        wrapper.eq("wvid", plan.getWvid());
        wrapper.groupBy("did");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Base1AdidZb> groupByMater(Base1PlanWork plan) {
        EntityWrapper<Base1AdidZb> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", plan.getPf());
        wrapper.eq("wvid", plan.getWvid());
        wrapper.groupBy("mid");
        return baseMapper.selectList(wrapper);
    }
}
