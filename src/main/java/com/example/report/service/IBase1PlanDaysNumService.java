package com.example.report.service;

import com.example.report.entity.Base1PlanDaysNum;
import com.baomidou.mybatisplus.service.IService;
import com.example.report.entity.Base1PlanWork;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 每日数据 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface IBase1PlanDaysNumService extends IService<Base1PlanDaysNum> {
    boolean saveList(String body);
    List<Base1PlanDaysNum> selectByWvid(Base1PlanWork plan) throws ParseException;
}
