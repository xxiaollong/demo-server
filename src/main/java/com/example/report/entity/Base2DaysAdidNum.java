package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 每天点位详情表
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@TableName("base2_days_adid_num")
public class Base2DaysAdidNum extends Model<Base2DaysAdidNum> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 平台ID：2-暴风、4-芒果
     */
    private Integer pf;
    /**
     * 计划ID
     */
    private Integer wvid;
    /**
     * 天
     */
    private Date day;
    /**
     * 剧ID
     */
    private String did;
    /**
     * 集ID
     */
    private String mid;
    /**
     * 视频ID
     */
    private String vid;
    /**
     * 物料ID
     */
    private String proid;
    /**
     * 客户ID
     */
    private String merid;
    /**
     * 点位ID
     */
    private String adid;
    /**
     * 终端 1：PC，2：Android，3：IOS，8：iPad
     */
    private Integer terminal;
    /**
     * 数量
     */
    private Integer nums;
    /**
     * 当天开始时间
     */
    private Date stime;
    /**
     * 当天结束时间
     */
    private Date etime;
    /**
     * 等级 日志不用
     */
    private Integer level;
    /**
     * 上映时间
     */
    private Date reledate;
    /**
     * 开始时间 日志不用
     */
    private String start;
    /**
     * 顺序
     */
    private Integer ordernum;
    /**
     * 非第一个点位 相对于上一个点位的时间差
     */
    private String timediff;
    /**
     * 是否分配 0-未分配   1-分配
     */
    @TableField("is_allot")
    private Integer isAllot;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPf() {
        return pf;
    }

    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public Integer getWvid() {
        return wvid;
    }

    public void setWvid(Integer wvid) {
        this.wvid = wvid;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getReledate() {
        return reledate;
    }

    public void setReledate(Date reledate) {
        this.reledate = reledate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getTimediff() {
        return timediff;
    }

    public void setTimediff(String timediff) {
        this.timediff = timediff;
    }

    public Integer getIsAllot() {
        return isAllot;
    }

    public void setIsAllot(Integer isAllot) {
        this.isAllot = isAllot;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Base2DaysAdidNum{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", day=" + day +
        ", did=" + did +
        ", mid=" + mid +
        ", vid=" + vid +
        ", proid=" + proid +
        ", merid=" + merid +
        ", adid=" + adid +
        ", terminal=" + terminal +
        ", nums=" + nums +
        ", stime=" + stime +
        ", etime=" + etime +
        ", level=" + level +
        ", reledate=" + reledate +
        ", start=" + start +
        ", ordernum=" + ordernum +
        ", timediff=" + timediff +
        ", isAllot=" + isAllot +
        "}";
    }
}
