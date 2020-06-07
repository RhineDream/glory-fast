package top.glory.modules.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("gen_table_info")
public class GenTableInfo extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 名称
     */
    private String tableName;

    /**
     * 注释
     */
    private String tableComment;

    /**
     * 实体类名称
     */
    private String clazzName;
    /**
     * 实体类名称
     */
    private String ifSon;

    /**
     * 关联父表
     */
    private String parentTable;

    /**
     * 关联父表外键
     */
    private String parentTableFk;


}
