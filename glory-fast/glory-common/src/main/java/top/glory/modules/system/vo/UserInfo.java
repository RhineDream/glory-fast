package top.glory.modules.system.vo;

import lombok.Data;

@Data
public class UserInfo {


    private String id;
    private String name;
    private String username;
    private String password;
    private String avatar;
    private String status;
    private String telephone;
    private String lastLoginIp;
    private String lastLoginTime;
    private String roleId;
    private String token;

    private Role role;

}
