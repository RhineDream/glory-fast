package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.SysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Slf4j
@RequestMapping("/api/user/")
@RestController
public class SysUserController {

    @Resource
    private UserService userService;

    /**
     * 列表查询
     */
    @GetMapping(value = "/list")
    public ResponseResult list(@RequestBody SysUser sysUser,HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
        //组装分页
        IPage<SysUser> pageList = userService.page(new Page<SysUser>(sysUser.getPageNo(), sysUser.getPageSize()), queryWrapper);
        return ResponseResult.ok(pageList);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysUser sysUser) {
        boolean flag = userService.save(sysUser);
        if (flag) {
            return ResponseResult.ok("添加成功", sysUser.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody SysUser sysUser) {
        SysUser user = userService.getById(sysUser.getId());
        if (user == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = userService.updateById(sysUser);
            if (flag) {
                return ResponseResult.ok("修改成功", sysUser.getId());
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
            boolean flag = userService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }
}
