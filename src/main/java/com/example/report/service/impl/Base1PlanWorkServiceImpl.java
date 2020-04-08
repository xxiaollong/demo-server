package com.example.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.report.entity.Base1PlanAreaZb;
import com.example.report.entity.Base1PlanWork;
import com.example.report.mapper.Base1PlanWorkMapper;
import com.example.report.service.IBase1PlanAreaZbService;
import com.example.report.service.IBase1PlanWorkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.report.service.IBase2DaysAdidNumService;
import com.example.report.service.IMbAreaUaZbNumService;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 计划表 服务实现类
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
@Service
public class Base1PlanWorkServiceImpl extends ServiceImpl<Base1PlanWorkMapper, Base1PlanWork> implements IBase1PlanWorkService {

    @Autowired
    private IBase2DaysAdidNumService iBase2DaysAdidNumService;

    @Autowired
    private IBase1PlanAreaZbService iBase1PlanAreaZbService;

    @Autowired
    private IMbAreaUaZbNumService iMbAreaUaZbNumService;


    @Override
    public Page queryWorkPlanList(Page page) {
        return page.setRecords(baseMapper.selectPage(page, null));
    }

    @Override
    public boolean setNums(Base1PlanWork plan) {
        Base1PlanWork temp = new Base1PlanWork();
        temp.setId(plan.getId());
        temp.setNums(plan.getNums());

        Integer i = baseMapper.updateById(temp);
        return i == 1;
    }

    @Override
    public boolean updatePlanStatus(String body) {
        JSONObject jsonObject = JSON.parseObject(body);

        // 如果是开始分量的操作,调用分量系统
        if (jsonObject.getInteger("status") == 3){
            new Thread(() -> sendGETRequest(body)).start();
        }

        Base1PlanWork  set = new Base1PlanWork();
        set.setStatus(jsonObject.getInteger("status"));

        Base1PlanWork  where = new Base1PlanWork();
        where.setPf(jsonObject.getInteger("pf"));
        where.setWvid(jsonObject.getInteger("wvid"));

        Integer update = baseMapper.update(set, new EntityWrapper<>(where));
        return update==1;
    }

    // 发送请求
    public void sendGETRequest(String body){
        try{
            JSONObject jo = JSON.parseObject(body);
            String httpurl = "http://10.65.55.23:5011/admin/externalAllot?" +
                    "pf="+jo.getInteger("pf") + "&wvid="+jo.getInteger("wvid");

            URL url = new URL(httpurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);

            // 发送请求
            connection.connect();
            System.out.println(connection.getResponseCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 发送请求
    public void sendPOSTRequest(String body){
        try{
            URL url = new URL("http://localhost:8089/report/post-test");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(body);
            pw.flush();
            pw.close();

            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public HSSFWorkbook exportDetails(Base1PlanWork plan) {
        List<Map<String, Object>> dayMapList = iBase2DaysAdidNumService.sumGroupByDay(plan);
        List<Map<String, Object>> dramaMapList = iBase2DaysAdidNumService.sumGroupByDrama(plan);
        List<Map<String, Object>> materMapList = iBase2DaysAdidNumService.sumGroupByMater(plan);
        List<Base1PlanAreaZb> areaZbList;
        if (plan.getPlanAreaZb() == 0){
            areaZbList = iMbAreaUaZbNumService.selestDemoAll(plan);
        }else {
            areaZbList = iBase1PlanAreaZbService.selectByWvid(plan);
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("1");

        //设置字体
        HSSFFont font1 = workbook.createFont();
        font1.setFontName("微软雅黑");
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font1.setFontHeightInPoints((short) 11);

        HSSFFont font2 = workbook.createFont();
        font2.setFontName("仿宋_GB2312");

        //样式一：边框
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle1.setFont(font2);

        //样式四：边框+字体1+水平居中
        CellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle4.setFont(font1);

        // 第一行
        HSSFRow row = sheet.createRow(0);
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 10);
        sheet.addMergedRegion(cra);
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle4);
        cell.setCellValue(plan.getWvname());

        // 第三行
        row = sheet.createRow(2);
        cra = new CellRangeAddress(2, 2, 0, 1);
        sheet.addMergedRegion(cra);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("地域分布");

        cra = new CellRangeAddress(2, 2, 3, 4);
        sheet.addMergedRegion(cra);
        cell = row.createCell(3);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("天");

        cra = new CellRangeAddress(2, 2, 6, 7);
        sheet.addMergedRegion(cra);
        cell = row.createCell(6);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("剧");

        cra = new CellRangeAddress(2, 2, 9, 10);
        sheet.addMergedRegion(cra);
        cell = row.createCell(9);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("集");

        // 第四行
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("名称");
        cell = row.createCell(1);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("占比");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("日期");
        cell = row.createCell(4);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("数量");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("名称");
        cell = row.createCell(7);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("数量");

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("名称");
        cell = row.createCell(10);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("数量");

        // 地域
        for (Base1PlanAreaZb areaZb : areaZbList) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            cell = row.createCell(0);
            cell.setCellValue(areaZb.getAreaName());
            cell = row.createCell(1);
            cell.setCellValue(((areaZb.getNum() * 1.0) / 10) + "%");
        }

        int rowIndex = 4;

        // 天
        for (Map<String, Object> map : dayMapList) {
            row = sheet.getRow(rowIndex);
            if (row == null){
                row = sheet.createRow(rowIndex);
            }

            cell = row.createCell(3);
            cell.setCellValue(map.get("day").toString());
            cell = row.createCell(4);
            cell.setCellValue(map.get("nums").toString());

            rowIndex++;
        }

        rowIndex = 4;
        // 剧
        for (Map<String, Object> map : dramaMapList) {
            row = sheet.getRow(rowIndex);
            if (row == null){
                row = sheet.createRow(rowIndex);
            }

            cell = row.createCell(6);
            cell.setCellValue(map.get("dname").toString());
            cell = row.createCell(7);
            cell.setCellValue(map.get("nums").toString());

            rowIndex++;
        }

        rowIndex = 4;
        // 集
        for (Map<String, Object> map : materMapList) {
            row = sheet.getRow(rowIndex);
            if (row == null){
                row = sheet.createRow(rowIndex);
            }

            cell = row.createCell(9);
            cell.setCellValue(map.get("mname").toString());
            cell = row.createCell(10);
            cell.setCellValue(map.get("nums").toString());

            rowIndex++;
        }



        return workbook;
    }

}
