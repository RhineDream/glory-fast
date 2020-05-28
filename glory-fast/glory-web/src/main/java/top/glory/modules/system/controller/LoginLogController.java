package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.LoginLogService;
import top.glory.modules.system.entity.SysLoginLog;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/api/loginLog/")
@RestController
public class LoginLogController {
    @Resource
    private LoginLogService loginLogService;

    /**
     * 列表查询
     */
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody SysLoginLog loginLog, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<SysLoginLog> queryWrapper = QueryGenerator.initQueryWrapper(loginLog, req.getParameterMap());
        //组装分页
        IPage<SysLoginLog> pageList = loginLogService.page(new Page<SysLoginLog>(loginLog.getPageNo(), loginLog.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

}
