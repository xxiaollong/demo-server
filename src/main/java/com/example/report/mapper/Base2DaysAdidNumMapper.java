package com.example.report.mapper;

import com.example.report.entity.Base2DaysAdidNum;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每天点位详情表 Mapper 接口
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
public interface Base2DaysAdidNumMapper extends BaseMapper<Base2DaysAdidNum> {
    // 查询天
    @Select("SELECT `day`, SUM(nums) nums FROM base2_days_adid_num WHERE pf=#{pf} AND wvid=#{wvid} GROUP BY `day` ")
    List<Map<String, Object>> sumGroupByDay(@Param("pf") Integer pf, @Param("wvid") Integer wvid);


    // 查询剧
    @Select("SELECT t1.did did, t2.dname dname, t1.nums nums FROM " +
            "(SELECT did, SUM(nums) nums FROM base2_days_adid_num WHERE pf=#{pf} AND wvid=#{wvid} GROUP BY did) t1 " +
            "INNER JOIN " +
            "(SELECT did, dname FROM base1_adid_zb WHERE pf=#{pf} AND wvid=#{wvid} GROUP BY did) t2 " +
            "ON t1.did=t2.did ")
    List<Map<String, Object>> sumGroupByDrama(@Param("pf") Integer pf, @Param("wvid") Integer wvid);


    // 查询集
    @Select("SELECT t1.mid mid, t2.mname mname, t1.nums nums FROM " +
            "(SELECT mid, SUM(nums) nums FROM base2_days_adid_num WHERE pf=#{pf} AND wvid=#{wvid} GROUP BY mid) t1 " +
            "INNER JOIN " +
            "(SELECT mid, mname FROM base1_adid_zb WHERE pf=#{pf} AND wvid=#{wvid} GROUP BY mid) t2 " +
            "ON t1.mid=t2.mid ")
    List<Map<String, Object>> sumGroupByMater(@Param("pf") Integer pf, @Param("wvid") Integer wvid);

    // 查询集的上线时间
    @Select("SELECT vid, sub_title, pt_create_time FROM tb_series_information WHERE source_id=4 AND vid = #{vid} ")
    List<Map<String, Object>> getVideoReleTime(@Param("vid") String vid);
}
