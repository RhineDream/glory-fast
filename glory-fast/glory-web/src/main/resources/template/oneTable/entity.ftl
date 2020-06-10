package ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ${(genCodeRecord.moduleAuthor)!""}
 * @Description: ${(genCodeRecord.moduleDesc)!""} 实体类
 * @Date: ${(currDate)!""}
 */

@Data
@TableName("${(tableInfo.tableName)!""}")
public class ${(tableInfo.clazzName)!""} extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId(type = IdType.UUID)
    private String id;

<#list fieldLists as field>
    /**
     * ${(field.fieldComment)!""}
     */
    private ${(field.javaType)!""} ${(field.javaFieldName)!""};
</#list>

}
