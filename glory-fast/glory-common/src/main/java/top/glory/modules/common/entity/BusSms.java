package top.glory.modules.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

@Data
@TableName("bus_sms")
public class BusSms extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 验证码
     */
    private String msgCode;

    /**
     * 结果
     */
    private String sendResult;
}
