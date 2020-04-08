package com.example.report.service;

import com.example.report.entity.Base1PlanMaterNum;
import com.baomidou.mybatisplus.service.IService;
import com.example.report.entity.Base1PlanWork;

import java.util.List;

/**
 * <p>
 * 集的占比 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface IBase1PlanMaterNumService extends IService<Base1PlanMaterNum> {

    List<Base1PlanMaterNum> selectByWvid(Base1PlanWork plan);

    boolean saveList(String body);
}
