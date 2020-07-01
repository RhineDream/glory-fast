package top.glory.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;
import top.glory.common.annotation.Dict;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author 春秋 2020-04-15
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

}