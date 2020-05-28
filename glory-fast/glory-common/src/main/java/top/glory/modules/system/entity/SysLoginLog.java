package top.glory.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 *  登录日志表
 * @author 春秋 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLoginLog extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 登录人
     */
    private String username;

    /**
     * 登录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 登录地址
     */
    private String loginAddr;

    /**
     * 机构编码
     */
    private String orgCode;

    public SysLoginLog(String loginName, String username) {
        this.loginName = loginName;
        this.username = username;
    }

    public SysLoginLog() {
    }
}
