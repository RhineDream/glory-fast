package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.RoleService;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Slf4j
@RequestMapping("/api/role/")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 列表查询
     */
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody SysRole role, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<SysRole> queryWrapper = QueryGenerator.initQueryWrapper(role, req.getParameterMap());
        //组装分页
        IPage<SysRole> pageList = roleService.page(new Page<SysRole>(role.getPageNo(), role.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysRole role) {

        boolean flag = roleService.save(role);
        if (flag) {
            return ResponseResult.ok("添加成功", role.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody SysRole role) {
        SysRole sysRole = roleService.getById(role.getId());
        if (sysRole == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = roleService.updateById(role);
            if (flag) {
                return ResponseResult.ok("修改成功", role.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = roleService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }

}
