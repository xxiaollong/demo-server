package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 集的占比
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@TableName("base1_plan_mater_num")
public class Base1PlanMaterNum extends Model<Base1PlanMaterNum> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 平台ID：2-暴风、4-芒果
     */
    private Integer pf;
    private Integer wvid;
    /**
     * 集ID
     */
    private String mid;
    /**
     * 集名称
     */
    private String mname;
    /**
     * 开始时间
     */
    private Date stime;
    /**
     * 结束时间
     */
    private Date etime;
    /**
     * 该集的量
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
        return "Base1PlanMaterNum{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", mid=" + mid +
        ", mname=" + mname +
        ", stime=" + stime +
        ", etime=" + etime +
        ", num=" + num +
        "}";
    }
}
