package top.glory.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.glory.common.annotation.Dict;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

/**
 * 菜单权限
 * @author 春秋 2020-04-16
 */
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 组件
     */
    private String component;

    /**
     * 组件名字
     */
    private String componentName;

    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    private Integer menuType;

    /**
     * 菜单权限编码
     */
    private String perms;

    /**
     * 权限策略1显示2禁用
     */
    @Dict(dicCode = "yes_no")
    private String permsType;

    /**
     * 菜单排序
     */
    private Double sortNo;

    /**
     * 聚合子路由: 1是0否
     */
    private String alwaysShow;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否路由菜单: 0:不是 1:是（默认值1）
     */
    private String isRoute;

    /**
     * 是否叶子节点:  1:是  0:不是
     */
    private String isLeaf;

    /**
     * 是否缓存该页面:  1:是  0:不是
     */
    private String keepAlive;

    /**
     * 是否隐藏路由: 0否，1是
     */
    private Integer hidden;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否添加数据权限1是0否
     */
    private Integer ruleFlag;

    /**
     * 按钮权限状态(0无效1有效)
     */
    private String status;


}