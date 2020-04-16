package top.glory.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 春秋
 * @Description: 基础实体类
 * @Date: 2020年4月16日
 */
public class DataEntity {

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * remarks
     */
    private String remarks;

    /**
     * 删除状态(0-正常，1-已删除)
     */
    @TableLogic(value="0",delval="1")   //配置逻辑删除
    private String delFlag;


    /**
     * 当前页
     */
    @TableField(exist = false)  //忽略非数据库表字段
    private Long pageNo;

    /**
     * 每页条数
     */
    @TableField(exist = false)
    private Long pageSize;


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    public String getDelFlag() {
        return delFlag;
    }

    @JsonProperty
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @JsonIgnore
    public Long getPageNo() {
        //当前页默认第一页
        if(pageNo == null || pageNo == 0){
            pageNo = 1L;
        }
        return pageNo;
    }

    @JsonProperty
    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    @JsonIgnore
    public Long getPageSize() {
        //分页条数默认10
        if(pageSize == null || pageSize == 0){
            pageSize = 10L;
        }
        return pageSize;
    }

    @JsonProperty
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
