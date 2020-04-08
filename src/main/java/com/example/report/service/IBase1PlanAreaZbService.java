package com.example.report.service;

import com.example.report.entity.Base1PlanAreaZb;
import com.baomidou.mybatisplus.service.IService;
import com.example.report.entity.Base1PlanWork;

import java.util.List;

/**
 * <p>
 * 地区占比 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface IBase1PlanAreaZbService extends IService<Base1PlanAreaZb> {

    List<Base1PlanAreaZb> selectByWvid(Base1PlanWork plan);

    boolean saveList(String body);

}
