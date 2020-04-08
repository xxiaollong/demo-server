package com.example.report.service;

import com.example.report.entity.Base1PlanDramaNum;
import com.baomidou.mybatisplus.service.IService;
import com.example.report.entity.Base1PlanWork;

import java.util.List;

/**
 * <p>
 * 剧的占比 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface IBase1PlanDramaNumService extends IService<Base1PlanDramaNum> {

    List<Base1PlanDramaNum> selectByWvid(Base1PlanWork plan);

    boolean saveList(String body);

}
