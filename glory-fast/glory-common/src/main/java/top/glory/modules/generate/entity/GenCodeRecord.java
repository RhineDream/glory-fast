package top.glory.modules.generate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
@TableName("gen_code_record")
public class GenCodeRecord extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 记录名称
     */
    private String recordName;

    /**
     * 分类
     */
    private String genType;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 功能名称
     */
    private String moduleDesc;

    /**
     * 生成功能作者
     */
    private String moduleAuthor;

    /**
     * 生成表编号
     */
    private String genTableInfoId;

}

