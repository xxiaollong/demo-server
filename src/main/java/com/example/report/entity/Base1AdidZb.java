package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 点位详情表
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
@TableName("base1_adid_zb")
public class Base1AdidZb extends Model<Base1AdidZb> {

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
     * 计划名称
     */
    private String wvname;
    /**
     * 剧ID
     */
    private String did;
    /**
     * 剧名称
     */
    private String dname;
    /**
     * 等级 日志不用
     */
    private Integer level;
    /**
     * 集ID
     */
    private String mid;
    /**
     * 集名称
     */
    private String mname;
    /**
     * 视频ID
     */
    private String vid;
    /**
     * 上映时间
     */
    private Date reledate;
    /**
     * 点位ID
     */
    private String adid;

    /**
     * 开始时间 日志不用
     */
    private String start;
    /**
     * 物料ID
     */
    private String proid;
    /**
     * 物料名称
     */
    private String proname;
    /**
     * 客户ID
     */
    private String merid;
    /**
     * 顺序
     */
    private Integer ordernum;
    /**
     * 非第一个点位 相对于上一个点位的时间差
     */
    private String timediff;
    /**
     * 占比量
     */
    private Integer zb;


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

    public String getWvname() {
        return wvname;
    }

    public void setWvname(String wvname) {
        this.wvname = wvname;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public Date getReledate() {
        return reledate;
    }

    public void setReledate(Date reledate) {
        this.reledate = reledate;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
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

    public Integer getZb() {
        return zb;
    }

    public void setZb(Integer zb) {
        this.zb = zb;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Base1AdidZb{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", wvname=" + wvname +
        ", did=" + did +
        ", dname=" + dname +
        ", level=" + level +
        ", mid=" + mid +
        ", mname=" + mname +
        ", vid=" + vid +
        ", reledate=" + reledate +
        ", adid=" + adid +
        ", start=" + start +
        ", proid=" + proid +
        ", proname=" + proname +
        ", merid=" + merid +
        ", ordernum=" + ordernum +
        ", timediff=" + timediff +
        ", zb=" + zb +
        "}";
    }
}
