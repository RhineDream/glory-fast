package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.system.service.MenuService;
import top.glory.modules.system.entity.SysPermission;
import top.glory.modules.system.vo.SysPermissionTree;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
     * 列表查询
     */
    @HandleLog("查看菜单列表")
    @GetMapping(value = "/list")
    public ResponseResult list() {

        LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<SysPermission>();
//        query.eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0);
        query.orderByAsc(SysPermission::getSortNo);
        List<SysPermission> list = menuService.list(query);
        List<SysPermissionTree> treeList = new ArrayList<>();
        getTreeList(treeList, list, null);
//
//        //组装查询条件
//        QueryWrapper<SysPermission> queryWrapper = QueryGenerator.initQueryWrapper(menu, req.getParameterMap());
//
//        //组装分页
//        IPage<SysPermission> pageList = menuService.page(new Page<SysPermission>(menu.getPageNo(), menu.getPageSize()), queryWrapper);
        return ResponseResult.ok(treeList);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增菜单")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysPermission menu) {

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
    public ResponseResult update(@RequestBody SysPermission menu) {
        SysPermission SysPermission = menuService.getById(menu.getId());
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



    private void getTreeList(List<SysPermissionTree> treeList, List<SysPermission> metaList, SysPermissionTree temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            SysPermissionTree tree = new SysPermissionTree(permission);
            if (temp == null && StringUtil.isEmpty(tempPid)) {
                treeList.add(tree);
                if (0 == tree.getIsLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            } else if (temp != null && tempPid != null && tempPid.equals(temp.getId())) {
                temp.getChildren().add(tree);
                if (0 == tree.getIsLeaf()) {
                    getTreeList(treeList, metaList, tree);
                }
            }

        }
    }
}
