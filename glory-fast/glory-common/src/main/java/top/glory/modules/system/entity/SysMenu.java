package top.glory.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.glory.common.annotation.Dict;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu extends DataEntity implements Serializable, TreeSupportEntity  {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限编码，例如：“sys:schedule:list,sys:schedule:info”,多个逗号隔开
     */
    private String perms;
    /**
     * 权限策略1显示2禁用
     */
    private String permsType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 组件名字
     */
    private String componentName;

    /**
     * 路径
     */
    private String url;
    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单排序
     */
    private Integer sortNo;

    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    @Dict(dicCode = "menu_type")
    private Integer menuType;

    /**
     * 是否叶子节点: 1:是  0:不是
     */
    private Integer isLeaf;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    private Integer isRoute;


    /**
     * 是否缓存页面: 0:不是  1:是（默认值1）
     */
    private Integer keepAlive;

    /**
     * 描述
     */
    private String description;



    /**
     * 是否配置菜单的数据权限 1是0否 默认0
     */
    private Integer ruleFlag;

    /**
     * 是否隐藏路由菜单: 0否,1是（默认值0）
     */
    private Integer hidden;


    /**
     * 按钮权限状态(0无效1有效)
     */
    private String status;

    /**
     * alwaysShow
     */
    private Integer alwaysShow;

    @TableField(exist = false)
    private List<SysMenu> children = Lists.newArrayList();

    /**
     * 按钮权限状态(0无效1有效)
     */
    @TableField(exist = false)
    private String ifCheck;
}
