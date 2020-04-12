package top.glory.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {
    /**主键ID*/
    @TableId(type = IdType.UUID)
    private String id;

    private String username;

    private String password;
}
