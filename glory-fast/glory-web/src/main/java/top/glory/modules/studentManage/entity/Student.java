package top.glory.modules.studentManage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 春秋
 * @Description: 学生管理 实体类
 * @Date: 2020-06-16
 */

@Data
@TableName("student")
public class Student extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 学生名称
     */
    private String studentName;
    /**
     * 学生编号
     */
    private String studentNo;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 电话
     */
    private String phone;
    /**
     * 介绍
     */
    private String description;
    /**
     * 最后登录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

}
