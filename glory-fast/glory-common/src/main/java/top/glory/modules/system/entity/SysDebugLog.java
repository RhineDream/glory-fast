package top.glory.modules.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDebugLog  extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String id;

    /**
     * 操作账号
     */
    private String loginName;

    /**
     * 操作人
     */
    private String username;

    /**
     * 报错方法名
     */
    private String postMethod;
    /**
     * 报错方法名
     */
    private String clazzName;
    /**
     * 报错方法名
     */
    private String ifException;

    /**
     * 报错数据
     */
    private String errorData;


    /**
     * 机构编码
     */
    private String orgCode;

}
