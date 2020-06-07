package top.glory.modules.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.generate.GenTableInfo;
import top.glory.modules.generate.GenTableInfoService;
import top.glory.modules.system.entity.SysTable;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/api/genCodeTable")
@RestController
public class GenTableInfoController {

    @Resource
    private GenTableInfoService genTableInfoService;

    /**
     * 列表查询
     */
    @HandleLog("获取所有数据库表名")
    @RequestMapping(value = "/getTablePageList")
    public ResponseResult getTablePageList(@RequestBody SysTable table, HttpServletRequest req) {
        if (table == null) {
            table = new SysTable();
        }
        //组装查询条件
        QueryWrapper<SysTable> queryWrapper = QueryGenerator.initQueryWrapper(table, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<SysTable> pageList = genTableInfoService.getTablePageList(new Page<SysTable>(table.getPageNo(), table.getPageSize()), table);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }


    /**
     * 列表查询
     */
    @HandleLog("查看数据库表列表")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody GenTableInfo tableInfo, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<GenTableInfo> queryWrapper = QueryGenerator.initQueryWrapper(tableInfo, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<GenTableInfo> pageList = genTableInfoService.page(new Page<GenTableInfo>(tableInfo.getPageNo(), tableInfo.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增数据库表信息")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody GenTableInfo tableInfo) {

        boolean flag = genTableInfoService.save(tableInfo);
        if (flag) {
            return ResponseResult.ok("添加成功", tableInfo.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @HandleLog("修改数据库表信息")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody GenTableInfo tableInfo) {
        GenTableInfo genTableInfo = genTableInfoService.getById(tableInfo.getId());
        if (genTableInfo == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = genTableInfoService.updateById(tableInfo);
            if (flag) {
                return ResponseResult.ok("修改成功", tableInfo.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @HandleLog("删除数据库表信息")
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = genTableInfoService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


}
