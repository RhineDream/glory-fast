package top.glory.modules.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.file.entity.BusFile;
import top.glory.modules.file.service.BusFileService;
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 春秋
 * @Description: 文件管理 Controller
 * @Date: 2020-07-25
 */
@Slf4j
@RequestMapping("/api/file/")
@RestController
public class BusFileController {

    @Resource
    private BusFileService busFileService;

    /**
     * 文件管理-列表查询
     */
    @HandleLog("文件管理-列表查询")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody BusFile busFile, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<BusFile> queryWrapper = QueryGenerator.initQueryWrapper(busFile, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<BusFile> pageList = busFileService.page(new Page<BusFile>(busFile.getPageNo(), busFile.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 文件管理-新增
     */
    @HandleLog("文件管理-新增")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody BusFile busFile) {
        boolean flag = busFileService.save(busFile);
        if (flag) {
            return ResponseResult.ok("添加成功", busFile.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 文件管理-修改
     */
    @HandleLog("文件管理-修改")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody BusFile busFile) {
        BusFile busFileOld = busFileService.getById(busFile.getId());
        if (busFileOld == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = busFileService.updateById(busFile);
            if (flag) {
                return ResponseResult.ok("修改成功", busFile.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 文件管理-删除
     */
    @HandleLog("文件管理-删除")
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = busFileService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }

}
