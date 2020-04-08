package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 每日数据
 * </p>
 *
 * @author xlw
 * @since 2019-12-31
 */
@TableName("base1_plan_days_num")
public class Base1PlanDaysNum extends Model<Base1PlanDaysNum> {

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
     * 日期
     */
    private Date vday;
    /**
     * 当天总量
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

    public Date getVday() {
        return vday;
    }

    public void setVday(Date vday) {
        this.vday = vday;
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
        return "Base1PlanDaysNum{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", vday=" + vday +
        ", num=" + num +
        "}";
    }
}
