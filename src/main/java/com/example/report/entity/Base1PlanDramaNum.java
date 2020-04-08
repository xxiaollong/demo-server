package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 剧的占比
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@TableName("base1_plan_drama_num")
public class Base1PlanDramaNum extends Model<Base1PlanDramaNum> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 平台ID：2-暴风、4-芒果
     */
    private Integer pf;
    private Integer wvid;
    /**
     * 剧ID
     */
    private String did;
    /**
     * 剧名称
     */
    private String dname;
    /**
     * 开始时间
     */
    private Date stime;
    /**
     * 结束时间
     */
    private Date etime;
    /**
     * 该剧的量
     */
    private Integer num;


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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Base1PlanDramaNum{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", did=" + did +
        ", dname=" + dname +
        ", stime=" + stime +
        ", etime=" + etime +
        ", num=" + num +
        "}";
    }
}
