package top.glory.modules.system.entity;

import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
public class SysTable extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;
}
