package com.example.report.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1PlanAreaZb;
import com.example.report.entity.Base1PlanWork;
import com.example.report.entity.MbAreaUaZbNum;
import com.example.report.mapper.MbAreaUaZbNumMapper;
import com.example.report.service.IMbAreaUaZbNumService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.system.entity.SysRolePerm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 城市ua占比 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2020-01-03
 */
@Service
public class MbAreaUaZbNumServiceImpl extends ServiceImpl<MbAreaUaZbNumMapper, MbAreaUaZbNum> implements IMbAreaUaZbNumService {

    @Override
    public List<Base1PlanAreaZb> selestDemoAll(Base1PlanWork plan) {
        List<Base1PlanAreaZb> areaZbList = new ArrayList<>();
        List<MbAreaUaZbNum> mbAreaUaZbNumList = baseMapper.selectList(null);
        for (MbAreaUaZbNum zbNum : mbAreaUaZbNumList) {
            Base1PlanAreaZb areaZb = new Base1PlanAreaZb();
            areaZb.setPf(plan.getPf());
            areaZb.setWvid(plan.getWvid());
            areaZb.setAreaCode(zbNum.getAreaCode());
            areaZb.setAreaName(zbNum.getName());
            areaZb.setNum(zbNum.getZb());

            areaZbList.add(areaZb);
        }

        return areaZbList;
    }
}
