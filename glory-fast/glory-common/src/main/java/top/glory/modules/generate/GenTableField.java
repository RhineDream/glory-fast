package top.glory.modules.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

/**
 *  业务表字段
 * @author 春秋 2020-06-07
 */

@Data
@TableName("gen_table_field")
public class GenTableField extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 业务表id
     */
    private String genTableInfoId;
    /**
     * 业务表名
     */
    private String genTableName;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段备注
     */
    private String fieldComment;

    /**
     * 数据库字段类型
     */
    private String jdbcType;

    /**
     * java类型
     */
    private String javaType;

    /**
     * java字段名
     */
    private String javaFieldName;

    /**
     * 是否主键
     */
    private String ifPrimaryKey;

    /**
     * 是否可为空
     */
    private String ifBlank;

    /**
     * 是否为插入字段
     */
    private String ifInsert;

    /**
     * 是否编辑字段
     */
    private String ifEdit;

    /**
     * 是否列表字段
     */
    private String ifList;

    /**
     * 是否查询字段
     */
    private String ifQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围、左like、右like、左右like）
     */
    private String queryType;

    /**
     * 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
     */
    private String showType;

    /**
     * 字典类型-1字典表/2数据库表
     */
    private String dictType;

    /**
     * dict_type2为2时，字典表名
     */
    private String dictTable;

    /**
     * 字典表字段
     */
    private String dictCode;

    /**
     * dict_type2为2时，字典表展示字段
     */
    private String dictText;

    /**
     * 排序
     */
    private Double sort;

}
