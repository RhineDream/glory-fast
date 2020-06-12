package top.glory.modules.system.entity;

import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
public class SysTableField extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 排序
     */
    private String ordinalPosition;

    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段注释
     */
    private String columnComment;


}
