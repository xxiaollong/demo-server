package com.example.report.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.report.entity.*;
import com.example.report.service.*;
import com.example.system.util.PageUtils;
import com.example.system.vo.Json;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 结案报告相关接口
 *
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private IMbAreaUaZbNumService iMbAreaUaZbNumService;

    @Autowired
    private IBase1PlanWorkService iBase1PlanWorkService;

    @Autowired
    private IBase1PlanDramaNumService iBase1PlanDramaNumService;

    @Autowired
    private IBase1PlanMaterNumService iBase1PlanMaterNumService;

    @Autowired
    private IDataSynService iDataSynService;

    @Autowired
    private IBase1PlanDaysNumService iBase1PlanDaysNumService;

    @Autowired
    private IBase1PlanAreaZbService iBase1PlanAreaZbService;

    @Autowired
    private IBase2DaysAdidNumService iBase2DaysAdidNumService;


    // 查询计划列表页
    @PostMapping("/work_plan_list")
    public Json queryPlanList(@RequestBody String body) {
        String oper = "query WorkPlan List";
        log.info("{}, body: {}", oper, body);
        Page page = iBase1PlanWorkService.queryWorkPlanList(PageUtils.getPageParam(JSON.parseObject(body)));
        return Json.succ(oper).data("page", page);
    }

    // 修改计划状态
    @PostMapping("/u_plan_stu")
    public Json updatePlanStatus(@RequestBody String body){
        boolean b = iBase1PlanWorkService.updatePlanStatus(body);
        return Json.succ("updatePlanStatus", b).data("update", b);
    }

    // 保存总量
    @PostMapping("i_nums")
    public Json saveNums(@RequestBody String body){
        boolean b = iBase1PlanWorkService.setNums(JSON.parseObject(body, Base1PlanWork.class));
        return Json.succ("setNums", b).data("update", b);
    }

    // 查询天
    @PostMapping("s_days")
    public Json queryDays(@RequestBody String body){
        String oper = "query Days List";
        Base1PlanWork plan = JSON.parseObject(body, Base1PlanWork.class);
        List<Base1PlanDaysNum> dayList = new ArrayList<>();
        if (plan.getStatus() ==1 && plan.getPlanDaysNum() == 0){
            Calendar cal = Calendar.getInstance();
            cal.setTime(plan.getStime());
            while (cal.getTimeInMillis() < plan.getEtime().getTime()){
                Base1PlanDaysNum day = new Base1PlanDaysNum();
                day.setPf(plan.getPf());
                day.setWvid(plan.getWvid());
                day.setVday(cal.getTime());
                day.setNum(10);

                dayList.add(day);
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }else{
            try {
                dayList = iBase1PlanDaysNumService.selectByWvid(plan);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return Json.succ(oper).data("days", dayList);
    }

    // 保存天
    @PostMapping("i_days")
    public Json saveDays(@RequestBody String body){
        System.out.println(body);
        boolean b = iBase1PlanDaysNumService.saveList(body);
        if (b){
            return Json.succ("setDays", b).data("save", b);
        }
        return Json.succ("setDays", false).data("save", false);
    }

    // 查询地域
    @PostMapping("s_areas")
    public Json queryArea(@RequestBody String body){
        Base1PlanWork plan = JSON.parseObject(body, Base1PlanWork.class);
        List<Base1PlanAreaZb> areaZbList = null;
        if (plan.getPlanAreaZb() == 0){
            areaZbList = iMbAreaUaZbNumService.selestDemoAll(plan);
        }else{
            areaZbList = iBase1PlanAreaZbService.selectByWvid(plan);
        }
        return Json.succ("").data("areas", areaZbList);
    }

    // 保存地域
    @PostMapping("i_areas")
    public Json saveArea(@RequestBody String body){
        System.out.println(body);
        boolean b = iBase1PlanAreaZbService.saveList(body);
        if (b){
            return Json.succ("setDays", b).data("save", b);
        }
        return Json.succ("setDays", false).data("save", false);
    }

    // 查询集
    @PostMapping("s_maters")
    public Json queryMater(@RequestBody String body){
        Base1PlanWork plan = JSON.parseObject(body, Base1PlanWork.class);
        List<Base1PlanMaterNum> planMaterNumList = iBase1PlanMaterNumService.selectByWvid(plan);
        return Json.succ("").data("maters", planMaterNumList);
    }

    // 保存集
    @PostMapping("i_maters")
    public Json saveMater(@RequestBody String body){
        System.out.println(body);

        boolean b = iBase1PlanMaterNumService.saveList(body);
        if (b){
            return Json.succ("setDays", b).data("save", b);
        }
        return Json.succ("setDays", false).data("save", false);

    }

    // 查询剧
    @PostMapping("s_dramas")
    public Json queryDrama(@RequestBody String body){
        Base1PlanWork plan = JSON.parseObject(body, Base1PlanWork.class);
        List<Base1PlanDramaNum> planDramaList = iBase1PlanDramaNumService.selectByWvid(plan);
        return Json.succ("").data("dramas", planDramaList);
    }

    // 保存剧
    @PostMapping("i_dramas")
    public Json saveDrama(@RequestBody String body){
        System.out.println(body);
        boolean b = iBase1PlanDramaNumService.saveList(body);
        if (b){
            return Json.succ("setDays", b).data("save", b);
        }
        return Json.succ("setDays", false).data("save", false);

    }

    // 导出计划分量数据
    @RequestMapping("exportDetails")
    public void exportDetails(Integer planId, HttpServletRequest request, HttpServletResponse response){
        try{
            Base1PlanWork planWork = iBase1PlanWorkService.selectById(planId);

            HSSFWorkbook workbook = iBase1PlanWorkService.exportDetails(planWork);

            //设置文件名称和响应信息，通过流输出到页面提供下载
            String ua = request.getHeader("USER-AGENT");
            String fileName = null;
            String name_temp = planWork.getWvname() + ".xls";
            if (ua.contains("MSIE") || ua.contains("Trident")) {
                fileName = URLEncoder.encode(name_temp, "UTF-8");
            } else if (ua.contains("Mozilla")) {
                fileName = new String(name_temp.getBytes(), "ISO-8859-1");
            } else {
                fileName = URLEncoder.encode(name_temp, "UTF-8");
            }

            response.setCharacterEncoding("utf-8");
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Type", "application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
            OutputStream out = response.getOutputStream();

            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 数据同步
    @RequestMapping("/dataSynchronization")
    public void dataSynchronization(){
        iDataSynService.dataSynchronization();
    }


    @RequestMapping("/test")
    public void test(){
        Base1PlanWork plan = new Base1PlanWork();
        plan.setPf(1);
        plan.setWvid(1);
        List<Map<String, Object>> mapList = iBase2DaysAdidNumService.sumGroupByDay(plan);
        List<Map<String, Object>> mapList1 = iBase2DaysAdidNumService.sumGroupByDrama(plan);
        List<Map<String, Object>> mapList2 = iBase2DaysAdidNumService.sumGroupByMater(plan);

        System.out.println("qq");
    }


    @PostMapping("post-test")
    public void postTest(@RequestBody String body){
        System.out.println(body);
    }

    @RequestMapping("get-test")
    public void getTest(String pf, String wvid){
        System.out.println(pf +"  "+wvid);
    }


}
