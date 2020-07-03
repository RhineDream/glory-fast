package top.glory.modules.system.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private List<String> menus = Lists.newArrayList();

}
