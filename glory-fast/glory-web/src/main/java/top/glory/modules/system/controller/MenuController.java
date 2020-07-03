package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.SysMenu;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.entity.TreeSupportEntity;
import top.glory.modules.system.service.MenuService;
import top.glory.modules.system.service.RoleMenuService;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Slf4j
@RequestMapping("/api/menu/")
@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取当前登录用户拥有的菜单权限
     */
    @GetMapping(value = "/getUserMenuList")
    public ResponseResult getUserMenuList() {
        List<SysMenu> list =  menuService.getUserMenuList();
        return ResponseResult.ok(list);
    }

    /**
     * 授权需要展示列表
     */
    @GetMapping(value = "/getMenuListByRole")
    public ResponseResult getMenuListByRole(String roleId) {
        List<String> list =  menuService.getMenuListByRole(roleId);
        return ResponseResult.ok(list);
    }

    /**
     * 列表查询
     */
    @HandleLog("查看菜单列表")
    @GetMapping(value = "/list")
    public ResponseResult list() {
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<SysMenu>();
        query.orderByAsc(SysMenu::getSortNo);
        List<SysMenu> list = menuService.list(query);
        List<SysMenu> servicePlaces = TreeSupportEntity.list2tree(list, SysMenu::setChildren,
                (Predicate<SysMenu>) servicePlace ->
                        // parentId为空或者为-1的菜单则认为是根菜单
                        StringUtils.isEmpty(servicePlace.getParentId()) || servicePlace.getParentId().equals("-1")
                                || servicePlace.getParentId().equals("0"));

        return ResponseResult.ok(servicePlaces);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增菜单")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysMenu menu) {

        boolean flag = menuService.save(menu);
        if (flag) {
            return ResponseResult.ok("添加成功", menu.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @HandleLog("修改菜单")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody SysMenu menu) {
        SysMenu SysPermission = menuService.getById(menu.getId());
        if (SysPermission == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = menuService.updateById(menu);
            if (flag) {
                return ResponseResult.ok("修改成功", menu.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @HandleLog("删除菜单")
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = menuService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }

}
