package com.example.report.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.report.entity.Base1AdidZb;
import com.baomidou.mybatisplus.service.IService;
import com.example.report.entity.Base1PlanWork;

import java.util.List;

/**
 * <p>
 * 点位详情表 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
public interface IBase1AdidZbService extends IService<Base1AdidZb> {
    List<Base1AdidZb> groupByDrama(Base1PlanWork plan);
    List<Base1AdidZb> groupByMater(Base1PlanWork plan);

}
