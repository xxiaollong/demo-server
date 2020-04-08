package com.example.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1AdidZb;
import com.example.report.entity.Base1PlanMaterNum;
import com.example.report.entity.Base1PlanWork;
import com.example.report.entity.Base2DaysAdidNum;
import com.example.report.mapper.Base1PlanMaterNumMapper;
import com.example.report.service.IBase1AdidZbService;
import com.example.report.service.IBase1PlanMaterNumService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.report.service.IBase1PlanWorkService;
import com.example.report.service.IBase2DaysAdidNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 集的占比 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@Service
public class Base1PlanMaterNumServiceImpl extends ServiceImpl<Base1PlanMaterNumMapper, Base1PlanMaterNum> implements IBase1PlanMaterNumService {

    @Autowired
    private IBase1PlanWorkService iBase1PlanWorkService;

    @Autowired
    private IBase1AdidZbService iBase1AdidZbService;

    @Autowired
    private IBase2DaysAdidNumService iBase2DaysAdidNumService;

    @Override
    public List<Base1PlanMaterNum> selectByWvid(Base1PlanWork plan) {
        List<Base1PlanMaterNum> materList = new ArrayList<>();

        if (plan.getStatus()<=3 && plan.getPlanMaterNum() == 0){
            List<Base1AdidZb> adidZbList = iBase1AdidZbService.groupByMater(plan);
            for (Base1AdidZb adidZb : adidZbList) {
                Base1PlanMaterNum mater = new Base1PlanMaterNum();
                mater.setPf(plan.getPf());
                mater.setWvid(plan.getWvid());
                mater.setMid(adidZb.getMid());
                mater.setMname(adidZb.getMname());
                mater.setNum(0);
                if (adidZb.getReledate().getTime() > plan.getStime().getTime()){
                    mater.setStime(adidZb.getReledate());
                }else {
                    mater.setStime(plan.getStime());
                }
                mater.setEtime(plan.getEtime());

                materList.add(mater);
            }
        }else if (plan.getStatus() <= 3 && plan.getPlanMaterNum() == 1){
            EntityWrapper<Base1PlanMaterNum> wrapper = new EntityWrapper<>();
            wrapper.eq("pf", plan.getPf());
            wrapper.eq("wvid", plan.getWvid());

            materList = baseMapper.selectList(wrapper);
        }else {
            List<Map<String, Object>> mapList = iBase2DaysAdidNumService.sumGroupByMater(plan);
            for (Map<String, Object> map : mapList) {
                Base1PlanMaterNum mater = new Base1PlanMaterNum();
                mater.setMname(map.get("mname").toString());
                mater.setNum(Integer.valueOf(map.get("nums").toString()));

                materList.add(mater);
            }

        }

        return materList;
    }

    @Override
    public boolean saveList(String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        List<Base1PlanMaterNum> maters = JSON.parseArray(jsonObject.getString("maters"), Base1PlanMaterNum.class);

        // 删除数据
        EntityWrapper<Base1PlanMaterNum> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", maters.get(0).getPf());
        wrapper.eq("wvid", maters.get(0).getWvid());
        baseMapper.delete(wrapper);

        // 写入数据
        for (Base1PlanMaterNum mater : maters) {
            mater.setId(null);
            baseMapper.insert(mater);
        }

        // 更新计划状态
        Base1PlanWork temp = new Base1PlanWork();
        temp.setId(jsonObject.getInteger("planId"));
        temp.setPlanMaterNum(1);

        boolean b = iBase1PlanWorkService.updateById(temp);

        return b;
    }


}
