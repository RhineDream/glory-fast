package top.glory.modules.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysHandleLog extends DataEntity implements Serializable {

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
     * 操作方法名
     */
    private String urlPath;


    /**
     * 操作方法名
     */
    private String postDesc;

    /**
     * 操作方法名
     */
    private String postMethod;

    /**
     * 请求方式
     */
    private String postType;

    /**
     * 请求数据
     */
    private String postData;
    /**
     * 耗时
     */
    private String useTime;
    /**
     * 耗时
     */
    private String returnData;

    /**
     * 机构编码
     */
    private String orgCode;

}
