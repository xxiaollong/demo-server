package com.example.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1PlanDaysNum;
import com.example.report.entity.Base1PlanWork;
import com.example.report.mapper.Base1PlanDaysNumMapper;
import com.example.report.service.IBase1PlanDaysNumService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.report.service.IBase1PlanWorkService;
import com.example.report.service.IBase2DaysAdidNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每日数据 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@Service
public class Base1PlanDaysNumServiceImpl extends ServiceImpl<Base1PlanDaysNumMapper, Base1PlanDaysNum> implements IBase1PlanDaysNumService {

    @Autowired
    private IBase1PlanWorkService iBase1PlanWorkService;

    @Autowired
    private IBase2DaysAdidNumService iBase2DaysAdidNumService;

    @Override
    public boolean saveList(String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        List<Base1PlanDaysNum> days = JSON.parseArray(jsonObject.getString("days"), Base1PlanDaysNum.class);

        // 删除数据
        EntityWrapper<Base1PlanDaysNum> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", days.get(0).getPf());
        wrapper.eq("wvid", days.get(0).getWvid());
        baseMapper.delete(wrapper);

        // 写入数据
        for (Base1PlanDaysNum day : days) {
            day.setId(null);
            baseMapper.insert(day);
        }

        // 更新计划状态
        Base1PlanWork temp = new Base1PlanWork();
        temp.setId(jsonObject.getInteger("planId"));
        temp.setPlanDaysNum(1);

        boolean b = iBase1PlanWorkService.updateById(temp);

        return b;
    }

    @Override
    public List<Base1PlanDaysNum> selectByWvid(Base1PlanWork plan){
        List<Base1PlanDaysNum> base1PlanDayList = new ArrayList<>();
        if (plan.getStatus() <= 3){
            EntityWrapper<Base1PlanDaysNum> wrapper = new EntityWrapper<>();
            wrapper.eq("pf", plan.getPf());
            wrapper.eq("wvid", plan.getWvid());
            base1PlanDayList = baseMapper.selectList(wrapper);
        }else {
            List<Map<String, Object>> mapList = iBase2DaysAdidNumService.sumGroupByDay(plan);
            for (Map<String, Object> map : mapList) {
                Base1PlanDaysNum day = new Base1PlanDaysNum();
                try {
                    day.setVday(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("day").toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                day.setNum(Integer.valueOf(map.get("nums").toString()));

                base1PlanDayList.add(day);
            }
        }

        return base1PlanDayList;
    }


}
