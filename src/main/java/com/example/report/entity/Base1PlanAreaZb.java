package com.example.report.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 地区占比
 * </p>
 *
 * @author xlw
 * @since 2020-01-03
 */
@TableName("base1_plan_area_zb")
public class Base1PlanAreaZb extends Model<Base1PlanAreaZb> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 平台ID：2-暴风、4-芒果
     */
    private Integer pf;
    private Integer wvid;
    /**
     * 地域编号
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 地域名称
     */
    @TableField("area_name")
    private String areaName;
    /**
     * 该地区占比的量
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
        return "Base1PlanAreaZb{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", areaCode=" + areaCode +
        ", areaName=" + areaName +
        ", num=" + num +
        "}";
    }
}
