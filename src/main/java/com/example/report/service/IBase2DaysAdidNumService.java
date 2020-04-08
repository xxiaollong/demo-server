package com.example.report.service;

import com.example.report.entity.Base1PlanWork;
import com.example.report.entity.Base2DaysAdidNum;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每天点位详情表 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface IBase2DaysAdidNumService extends IService<Base2DaysAdidNum> {

    // 根据天分组
    List<Map<String, Object>> sumGroupByDay(Base1PlanWork plan);

    // 根据剧分组
    List<Map<String, Object>> sumGroupByDrama(Base1PlanWork plan);

    // 根据集分组
    List<Map<String, Object>> sumGroupByMater(Base1PlanWork plan);

}
