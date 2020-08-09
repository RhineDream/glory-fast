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
import top.glory.modules.generate.entity.GenCodeRecord;
import top.glory.modules.generate.service.GenCodeRecordService;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/api/genCodeRecord")
@RestController
public class GenCodeRecordController {

    @Resource
    private GenCodeRecordService genCodeRecordService;


    /**
     * 列表查询
     */
    @HandleLog("查看数据库表列表")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody GenCodeRecord codeRecord, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<GenCodeRecord> queryWrapper = QueryGenerator.initQueryWrapper(codeRecord, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<GenCodeRecord> pageList = genCodeRecordService.page(new Page<GenCodeRecord>(codeRecord.getPageNo(), codeRecord.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增数据库表信息")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody GenCodeRecord codeRecord) {

        boolean flag = genCodeRecordService.save(codeRecord);
        if (flag) {
            return ResponseResult.ok("添加成功", codeRecord.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @HandleLog("修改数据库表信息")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody GenCodeRecord codeRecord) {
        GenCodeRecord genCodeRecord = genCodeRecordService.getById(codeRecord.getId());
        if (genCodeRecord == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = genCodeRecordService.updateById(codeRecord);
            if (flag) {
                return ResponseResult.ok("修改成功", codeRecord.getId());
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
            boolean flag = genCodeRecordService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


    /**
     * 生成代码
     */
    @HandleLog("生成代码")
    @PostMapping(value = "/createCodeFile")
    public ResponseResult createCodeFile(@RequestBody GenCodeRecord codeRecord) {
        ResponseResult res = genCodeRecordService.createCodeFile(codeRecord);
        return res;
    }


}
