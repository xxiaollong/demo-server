package com.example.report.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.report.entity.Base1PlanWork;
import com.baomidou.mybatisplus.service.IService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * 计划表 服务类
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
public interface IBase1PlanWorkService extends IService<Base1PlanWork> {

    Page queryWorkPlanList(Page page);
    boolean setNums(Base1PlanWork plan);
    boolean updatePlanStatus(String body);
    HSSFWorkbook exportDetails(Base1PlanWork plan);


}
