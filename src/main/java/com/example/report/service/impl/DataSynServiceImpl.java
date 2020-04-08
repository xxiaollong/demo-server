package com.example.report.service.impl;

import com.example.report.entity.*;
import com.example.report.mapper.Base2DaysAdidNumMapper;
import com.example.report.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *  数据同步
 *
 */
@Service
public class DataSynServiceImpl implements IDataSynService {
    // 计划
    @Autowired
    private IBase1PlanWorkService iBase1PlanWorkService;
    // 点位
    @Autowired
    private IBase1AdidZbService iBase1AdidZbService;

    @Autowired
    private Base2DaysAdidNumMapper base2DaysAdidNumMapper;


    // 80库连接查询
    private static String username = "root";
    private static String password = "Yp@Mb#691";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.1.80:3306/db_rtb?useUnicode=true&characterEncoding=UTF8";

    private Connection con = null;
    private PreparedStatement st1 = null;
    private PreparedStatement st2 = null;

    // 查询计划
    private static String S_PLAN_SQL = "SELECT wd.platform pf, wv.work_video_id wvid, wv.work_video_name wvname, wv.start_time stime, wv.end_time etime, wv.budget FROM mb_ad_work_video wv " +
            "INNER JOIN video_work_drama wd ON wv.`work_video_id` = wd.`work_video_id` AND wd.`state` = 1 " +
            "INNER JOIN video_work_mater wm ON wd.`work_video_id` = wm.`work_video_id` AND wd.`drama_id` = wm.`drama_id` AND wm.`state` = 1 " +
            "INNER JOIN video_work_point wp ON wm.`work_video_id` = wp.`work_video_id` AND wm.`drama_id` = wp.`drama_id` AND wm.`material_id` = wp.`material_id` AND wp.`state` NOT IN(4, 11, 14) " +
            "WHERE wv.`state` NOT IN(4, 11, 14) AND wv.`work_video_id` >= 3030 " +
            "GROUP BY wd.platform, wv.work_video_id ";

    // 查询点位
    private static String S_POINT_SQL = "SELECT wd.`platform` pf, wv.`work_video_id` wvid, wd.`drama_id` did, wd.drama_name dname, wd.`drama_level` `level`, " +
            "wm.`material_id` `mid`, wm.material_name mname, wm.`outer_id` vid, '' AS reledate, wp.`point_code` adid, " +
            "wp.`start_time` `start`, wp.`product_id` proid, wp.product_name proname, wv.`member_id` merid FROM mb_ad_work_video wv " +
            "INNER JOIN video_work_drama wd ON wv.`work_video_id` = wd.`work_video_id` AND wd.`state` = 1 " +
            "INNER JOIN video_work_mater wm ON wd.`work_video_id` = wm.`work_video_id` AND wd.`drama_id` = wm.`drama_id` AND wm.`state` = 1 " +
            "INNER JOIN video_work_point wp ON wm.`work_video_id` = wp.`work_video_id` AND wm.`drama_id` = wp.`drama_id` AND wm.`material_id` = wp.`material_id` AND wp.`state` NOT IN(4, 11, 14) " +
            "WHERE wv.`state` NOT IN(4, 11, 14) AND wv.`work_video_id` = ? AND wd.`platform` = ? " +
            "GROUP BY wp.`work_video_id`, wp.`point_code` ORDER BY wm.`outer_id`, wp.`start_time` ";


    // 获取数据库连接
    public void getConnection(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);

            st1 = con.prepareStatement(S_PLAN_SQL);
            st2 = con.prepareStatement(S_POINT_SQL);

            System.out.println("-------80数据库连接初始化成功-------");
        } catch (Exception e) {
            System.out.println("-------80数据库连接初始化失败-------");
            e.printStackTrace();
        }
    }

    // 关闭数据库连接
    public void  closeConnection(){
        if(st1 != null){
            try{
                st1.close();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                st1 = null;
            }
        }
        if(st2 != null){
            try{
                st2.close();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                st2 = null;
            }
        }

        if(con != null){
            try{
                //关闭Connection数据库连接对象
                con.close();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                con = null;
            }
        }

        System.out.println("-------80数据库连接正常关闭-------");
    }

    @Override
    @Transactional
    public void dataSynchronization() {
        try{
            if (con == null){
                getConnection();
            }

            List<Base1PlanWork> planList =  new ArrayList<>();

            // 查询计划数据
            ResultSet planSet = st1.executeQuery();
            while (planSet.next()){
                Base1PlanWork plan = new Base1PlanWork();
                plan.setPf(planSet.getInt("pf"));
                plan.setWvid(planSet.getInt("wvid"));
                plan.setWvname(planSet.getString("wvname"));
                plan.setStime(planSet.getDate("stime"));
                plan.setEtime(planSet.getDate("etime"));
                plan.setNums(0);
                plan.setPrice("0");
                plan.setBudget(planSet.getInt("budget"));
                plan.setWtype(1);
                plan.setPlanAreaZb(0);
                plan.setPlanDaysNum(0);
                plan.setPlanDramaNum(0);
                plan.setPlanMaterNum(0);
                plan.setMemo("");
                plan.setIsVdy(1);
                plan.setStatus(1);

                planList.add(plan);
            }

            Map<String, List<Base1AdidZb>> adidMap= new HashMap<>();

            // 查询点位数据
            for (Base1PlanWork planWork : planList) {
                // 清空数据
                adidMap.clear();

                // 查询点位信息
                st2.setInt(1, planWork.getWvid());
                st2.setInt(2, planWork.getPf());
                ResultSet adidSet = st2.executeQuery();

                while (adidSet.next()){
                    Base1AdidZb adid = new Base1AdidZb();

                    // 设置点位数据
                    adid.setPf(planWork.getPf());
                    adid.setWvid(planWork.getWvid());
                    adid.setWvname(planWork.getWvname());
                    adid.setDid(adidSet.getString("did"));
                    adid.setDname(adidSet.getString("dname"));
                    adid.setLevel(adidSet.getInt("level"));
                    adid.setMid(adidSet.getString("mid"));
                    adid.setMname(adidSet.getString("mname"));
                    adid.setVid(adidSet.getString("vid"));
                    adid.setAdid(adidSet.getString("adid"));
                    adid.setStart(adidSet.getString("start"));
                    adid.setProid(adidSet.getString("proid"));
                    adid.setProname(adidSet.getString("proname"));
                    adid.setMerid(adidSet.getString("merid"));
                    adid.setZb(0);

                    if (adid.getPf() == 4){
                        String vid;
                        String timeTemp = null;
                        Date releDate = null;
                        if (adid.getVid().contains("_")){
                            vid = adid.getVid().substring(0, adid.getVid().indexOf("_"));
                        }else {
                            vid = adid.getVid();
                        }
                        List<Map<String, Object>> videoReleTile = base2DaysAdidNumMapper.getVideoReleTime(vid);
                        if (videoReleTile != null && videoReleTile.size() > 0){
                            if (videoReleTile.get(0).get("pt_create_time") != null){
                                try{
                                    timeTemp = videoReleTile.get(0).get("pt_create_time").toString();
                                    timeTemp = timeTemp.replace(".0", "");

                                    releDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeTemp);
                                }catch (Exception e){
                                    try{
                                        timeTemp = videoReleTile.get(0).get("sub_title").toString();
                                        releDate = new SimpleDateFormat("yyyy-MM-dd").parse(timeTemp);
                                    }catch (Exception e1){
                                        System.out.println("时间解析错误: " + planWork.toString());
                                        releDate = planWork.getStime();
                                    }
                                }
                            }else {
                                try{
                                    timeTemp = videoReleTile.get(0).get("sub_title").toString();
                                    releDate = new SimpleDateFormat("yyyy-MM-dd").parse(timeTemp);
                                }catch (Exception e){
                                    System.out.println("时间解析错误: " + planWork.toString());
                                    releDate = planWork.getStime();
                                }
                            }

                            adid.setReledate(releDate);
                        }else {
                            adid.setReledate(planWork.getStime());
                        }

                    }else{
                        adid.setReledate(planWork.getStime());
                    }

                    // 添加到集合
                    List<Base1AdidZb> adidList = adidMap.get(adid.getVid());
                    if (adidList == null){
                        adidList = new ArrayList<>();
                    }
                    adidList.add(adid);
                    adidMap.put(adid.getVid(), adidList);

                }

                // 对点位进行排序，补充时差
                sortAdid(adidMap);

                System.out.println(adidMap.size());

                // 保存计划
                iBase1PlanWorkService.insert(planWork);
                // 保存点位数据
                Collection<List<Base1AdidZb>> adidLists = adidMap.values();
                for (List<Base1AdidZb> adidList : adidLists) {
                    iBase1AdidZbService.insertBatch(adidList);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

    }

    // 对点位进行排序
    void sortAdid(Map<String, List<Base1AdidZb>> map){
        for (Map.Entry<String, List<Base1AdidZb>> entity : map.entrySet()){
            List<Base1AdidZb> value = entity.getValue();
            // 排序
            Collections.sort(value, new Comparator<Base1AdidZb>() {
                @Override
                public int compare(Base1AdidZb o1, Base1AdidZb o2) {
                    if (Double.valueOf(o1.getStart()) > Double.valueOf(o2.getStart())){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            });

            // 补充时差及点位占比
            int zb = 1000;
            for (int i = 0; i < value.size(); i++) {
                // 设置占比
                value.get(i).setZb(zb);

                // 设置顺序
                value.get(i).setOrdernum(i + 1);
                // 设置时差
                if (i == 0){
                    value.get(i).setTimediff("0");
                }else {
                    Double tf = Double.valueOf(value.get(i).getStart())
                            - Double.valueOf(value.get(i - 1).getStart());
                    value.get(i).setTimediff(tf.toString());
                }

                zb -= getNum(100, 200);
            }
        }
    }

    // 获取计划投放周期数据
    List<Base1PlanDaysNum> getDayList(Base1PlanWork planWork){
        List<Base1PlanDaysNum> dayList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(planWork.getStime());

        while (cal.getTimeInMillis() < planWork.getEtime().getTime()){
            Base1PlanDaysNum day = new Base1PlanDaysNum();
            day.setPf(planWork.getPf());
            day.setWvid(planWork.getWvid());
            day.setVday(cal.getTime());
            day.setNum(0);

            dayList.add(day);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dayList;
    }

    // 随机数
    private Random random = new Random();
    public Integer getNum(Integer min, Integer max){
        return random.nextInt(max)%(max-min+1) + min;
    }


}
