package top.glory.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

/**
 * 用户表
 * @author 春秋 2020-04-15
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;



    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

}