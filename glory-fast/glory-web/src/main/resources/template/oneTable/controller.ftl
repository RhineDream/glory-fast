package ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.service.${(tableInfo.clazzName)!""}Service;
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.entity.${(tableInfo.clazzName)!""};
import top.glory.modules.system.vo.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ${(genCodeRecord.moduleAuthor)!""}
 * @Description: ${(genCodeRecord.moduleDesc)!""} Controller
 * @Date: ${(currDate)!""}
 */
@Slf4j
@RequestMapping("/api/${(tableInfo.clazzName)!""}/")
@RestController
public class ${(tableInfo.clazzName)!""}Controller {

    @Resource
    private ${(tableInfo.clazzName)!""}Service ${(lowerClazzName)!""}Service;

    /**
     * ${(genCodeRecord.moduleDesc)!""}-列表查询
     */
    @HandleLog("${(genCodeRecord.moduleDesc)!""}-列表查询")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody ${(tableInfo.clazzName)!""} ${(lowerClazzName)!""}, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<${(tableInfo.clazzName)!""}> queryWrapper = QueryGenerator.initQueryWrapper(${(lowerClazzName)!""}, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<${(tableInfo.clazzName)!""}> pageList = ${(lowerClazzName)!""}Service.page(new Page<${(tableInfo.clazzName)!""}>(${(lowerClazzName)!""}.getPageNo(), ${(lowerClazzName)!""}.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * ${(genCodeRecord.moduleDesc)!""}-新增
     */
    @HandleLog("${(genCodeRecord.moduleDesc)!""}-新增")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody ${(tableInfo.clazzName)!""} ${(lowerClazzName)!""}) {
        boolean flag = ${(lowerClazzName)!""}Service.save(${(lowerClazzName)!""});
        if (flag) {
            return ResponseResult.ok("添加成功", ${(lowerClazzName)!""}.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * ${(genCodeRecord.moduleDesc)!""}-修改
     */
    @HandleLog("${(genCodeRecord.moduleDesc)!""}-修改")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody ${(tableInfo.clazzName)!""} ${(lowerClazzName)!""}) {
        ${(tableInfo.clazzName)!""} ${(lowerClazzName)!""}Old = ${(lowerClazzName)!""}Service.getById(${(lowerClazzName)!""}.getId());
        if (${(lowerClazzName)!""}Old == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = ${(lowerClazzName)!""}Service.updateById(${(lowerClazzName)!""});
            if (flag) {
                return ResponseResult.ok("修改成功", ${(lowerClazzName)!""}.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * ${(genCodeRecord.moduleDesc)!""}-删除
     */
    @HandleLog("${(genCodeRecord.moduleDesc)!""}-删除")
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = ${(lowerClazzName)!""}Service.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }

}
