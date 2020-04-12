package top.glory.modules.system.vo;

import lombok.Data;

import java.util.List;

@Data
public class Role {


    private String id;
    private String name;
    private String describe;
    private String status;
    private String creatorId;
    private String createTime;
    private Object permissions;

}
