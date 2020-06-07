package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.annotation.HandleLog;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.service.DebugLogService;
import top.glory.modules.system.entity.SysDebugLog;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/api/debugLog/")
@RestController
public class DebugLogController {
    @Resource
    private DebugLogService debugLogService;

    /**
     * 列表查询
     */
    @HandleLog("查看调试日志列表")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody SysDebugLog debugLog, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<SysDebugLog> queryWrapper = QueryGenerator.initQueryWrapper(debugLog, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<SysDebugLog> pageList = debugLogService.page(new Page<SysDebugLog>(debugLog.getPageNo(), debugLog.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

}
