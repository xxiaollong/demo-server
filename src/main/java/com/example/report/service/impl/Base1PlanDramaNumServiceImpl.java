package com.example.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.report.entity.Base1AdidZb;
import com.example.report.entity.Base1PlanDramaNum;
import com.example.report.entity.Base1PlanWork;
import com.example.report.mapper.Base1PlanDramaNumMapper;
import com.example.report.service.IBase1AdidZbService;
import com.example.report.service.IBase1PlanDramaNumService;
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
 * 剧的占比 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@Service
public class Base1PlanDramaNumServiceImpl extends ServiceImpl<Base1PlanDramaNumMapper, Base1PlanDramaNum> implements IBase1PlanDramaNumService {

    @Autowired
    private IBase1PlanWorkService iBase1PlanWorkService;

    @Autowired
    private IBase1AdidZbService iBase1AdidZbService;

    @Autowired
    private IBase2DaysAdidNumService iBase2DaysAdidNumService;

    @Override
    public List<Base1PlanDramaNum> selectByWvid(Base1PlanWork plan) {
        List<Base1PlanDramaNum> dramaList = new ArrayList<>();

        if (plan.getStatus()<=3 && plan.getPlanDramaNum() == 0){
            List<Base1AdidZb> adidZbs = iBase1AdidZbService.groupByDrama(plan);
            for (Base1AdidZb adidZb : adidZbs) {
                Base1PlanDramaNum drama = new Base1PlanDramaNum();
                drama.setPf(adidZb.getPf());
                drama.setWvid(adidZb.getWvid());
                drama.setDid(adidZb.getDid());
                drama.setDname(adidZb.getDname());
                drama.setNum(0);
                if (adidZb.getReledate().getTime() > plan.getStime().getTime()){
                    drama.setStime(adidZb.getReledate());
                }else{
                    drama.setStime(plan.getStime());
                }
                drama.setEtime(plan.getEtime());

                dramaList.add(drama);
            }
        }else if (plan.getStatus() <= 3 && plan.getPlanDramaNum() == 1){
            EntityWrapper<Base1PlanDramaNum> wrapper = new EntityWrapper<>();
            wrapper.eq("pf", plan.getPf());
            wrapper.eq("wvid", plan.getWvid());

            dramaList = baseMapper.selectList(wrapper);
        }else {
            List<Map<String, Object>> mapList = iBase2DaysAdidNumService.sumGroupByDrama(plan);
            for (Map<String, Object> map : mapList) {
                Base1PlanDramaNum drama = new Base1PlanDramaNum();
                drama.setDname(map.get("dname").toString());
                drama.setNum(Integer.valueOf(map.get("nums").toString()));

                dramaList.add(drama);
            }
        }

        return dramaList;
    }

    @Override
    public boolean saveList(String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        List<Base1PlanDramaNum> dramas = JSON.parseArray(jsonObject.getString("dramas"), Base1PlanDramaNum.class);

        // 删除数据
        EntityWrapper<Base1PlanDramaNum> wrapper = new EntityWrapper<>();
        wrapper.eq("pf", dramas.get(0).getPf());
        wrapper.eq("wvid", dramas.get(0).getWvid());
        baseMapper.delete(wrapper);

        // 写入数据
        for (Base1PlanDramaNum drama : dramas) {
            drama.setId(null);
            baseMapper.insert(drama);
        }

        // 更新计划状态
        Base1PlanWork temp = new Base1PlanWork();
        temp.setId(jsonObject.getInteger("planId"));
        temp.setPlanDramaNum(1);

        boolean b = iBase1PlanWorkService.updateById(temp);

        return b;
    }

}
