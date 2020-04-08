package com.example.report.service;

import com.example.report.entity.Base1PlanAreaZb;
import com.example.report.entity.Base1PlanWork;
import com.example.report.entity.MbAreaUaZbNum;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 城市ua占比 服务类
 * </p>
 *
 * @author xlw
 * @since 2020-01-03
 */
public interface IMbAreaUaZbNumService extends IService<MbAreaUaZbNum> {

    List<Base1PlanAreaZb> selestDemoAll(Base1PlanWork plan);


}
