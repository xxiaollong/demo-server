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
 * 计划表
 * </p>
 *
 * @author xlw
 * @since 2019-12-30
 */
@TableName("base1_plan_work")
public class Base1PlanWork extends Model<Base1PlanWork> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 平台ID：2-暴风、4-芒果
     */
    private Integer pf;
    /**
     * 计划id
     */
    private Integer wvid;
    /**
     * 计划名称
     */
    private String wvname;
    /**
     * 开始时间
     */
    private Date stime;
    /**
     * 结束时间
     */
    private Date etime;
    /**
     * 总量
     */
    private Integer nums;
    /**
     * 单价
     */
    private String price;
    /**
     * 费用
     */
    private Integer budget;
    /**
     * 计划类型 1-热播  2-非热播
     */
    private Integer wtype;
    /**
     * 是否有地域占比：0-没有，1-有
     */
    @TableField("plan_area_zb")
    private Integer planAreaZb;
    /**
     * 是否有每日的量：0-没有，1-有
     */
    @TableField("plan_days_num")
    private Integer planDaysNum;
    /**
     * 是否有剧的量：0-没有，1-有
     */
    @TableField("plan_drama_num")
    private Integer planDramaNum;
    /**
     * 是否有集的量：0-没有，1-有
     */
    @TableField("plan_mater_num")
    private Integer planMaterNum;
    /**
     * 备注
     */
    private String memo;
    /**
     * 1:video易 2:植入易
     */
    @TableField("is_vdy")
    private Integer isVdy;
    /**
     * 状态：1-同步完成，2-正在分量，3-分量完成，4-正在生产日志，5-日志生产完成
     */
    private Integer status;


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

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getWtype() {
        return wtype;
    }

    public void setWtype(Integer wtype) {
        this.wtype = wtype;
    }

    public Integer getPlanAreaZb() {
        return planAreaZb;
    }

    public void setPlanAreaZb(Integer planAreaZb) {
        this.planAreaZb = planAreaZb;
    }

    public Integer getPlanDaysNum() {
        return planDaysNum;
    }

    public void setPlanDaysNum(Integer planDaysNum) {
        this.planDaysNum = planDaysNum;
    }

    public Integer getPlanDramaNum() {
        return planDramaNum;
    }

    public void setPlanDramaNum(Integer planDramaNum) {
        this.planDramaNum = planDramaNum;
    }

    public Integer getPlanMaterNum() {
        return planMaterNum;
    }

    public void setPlanMaterNum(Integer planMaterNum) {
        this.planMaterNum = planMaterNum;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsVdy() {
        return isVdy;
    }

    public void setIsVdy(Integer isVdy) {
        this.isVdy = isVdy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Base1PlanWork{" +
        ", id=" + id +
        ", pf=" + pf +
        ", wvid=" + wvid +
        ", wvname=" + wvname +
        ", stime=" + stime +
        ", etime=" + etime +
        ", nums=" + nums +
        ", price=" + price +
        ", budget=" + budget +
        ", wtype=" + wtype +
        ", planAreaZb=" + planAreaZb +
        ", planDaysNum=" + planDaysNum +
        ", planDramaNum=" + planDramaNum +
        ", planMaterNum=" + planMaterNum +
        ", memo=" + memo +
        ", isVdy=" + isVdy +
        ", status=" + status +
        "}";
    }
}
