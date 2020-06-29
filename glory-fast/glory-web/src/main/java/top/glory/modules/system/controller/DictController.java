package top.glory.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.service.DictItemService;
import top.glory.modules.system.service.DictService;
import top.glory.modules.system.entity.SysDict;
import top.glory.modules.system.entity.SysDictItem;
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
@RequestMapping("/api/dict/")
@RestController
public class DictController {

    @Resource
    private DictService dictService;

    @Resource
    private DictItemService dictItemService;

    /**
     * 列表查询
     */
    @HandleLog("查看字典列表")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody SysDict dict, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<SysDict> queryWrapper = QueryGenerator.initQueryWrapper(dict, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        //组装分页
        IPage<SysDict> pageList = dictService.page(new Page<SysDict>(dict.getPageNo(), dict.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增字典主表")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysDict dict) {

        boolean flag = dictService.save(dict);
        if (flag) {
            return ResponseResult.ok("添加成功", dict.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @HandleLog("修改字典主表")
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody SysDict dict) {
        SysDict SysDict = dictService.getById(dict.getId());
        if (SysDict == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = dictService.updateById(dict);
            if (flag) {
                return ResponseResult.ok("修改成功", dict.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @HandleLog("删除字典主表")
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = dictService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


    //-------------------子表-----------------------------------------------------

    /**
     * 列表查询
     */
    @HandleLog("查看字典子表列表")
    @RequestMapping(value = "/listItem")
    public ResponseResult listItem(@RequestBody SysDictItem dictItem, HttpServletRequest req) {
        Assert.hasText(dictItem.getDictId(), "字典主表id不能为空");
        //组装查询条件
        QueryWrapper<SysDictItem> queryWrapper = QueryGenerator.initQueryWrapper(dictItem, req.getParameterMap());
//        queryWrapper.eq("dict_id",dictItem.getDictId());    //根据主表id分页查询子表
        queryWrapper.orderByAsc("sort_order");
        //组装分页
        IPage<SysDictItem> pageList = dictItemService.page(new Page<SysDictItem>(dictItem.getPageNo(), dictItem.getPageSize()), queryWrapper);

        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageInfo);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增字典子表")
    @PostMapping(value = "/insertItem")
    public ResponseResult insertItem(@RequestBody SysDictItem dictItem) {
        Assert.hasText(dictItem.getDictId(), "字典主表id不能为空");
        boolean flag = dictItemService.save(dictItem);
        if (flag) {
            return ResponseResult.ok("添加成功", dictItem.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @HandleLog("修改字典子表")
    @PutMapping(value = "/updateItem")
    public ResponseResult updateItem(@RequestBody SysDictItem dictItem) {
        Assert.hasText(dictItem.getDictId(), "字典主表id不能为空");
        SysDictItem item = dictItemService.getById(dictItem.getId());
        if (item == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = dictItemService.updateById(dictItem);
            if (flag) {
                return ResponseResult.ok("修改成功", dictItem.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @HandleLog("删除字典子表")
    @DeleteMapping(value = "/deleteItem")
    public ResponseResult deleteItem(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = dictItemService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


    /**
     * 字典查询
     */
    @HandleLog("查看字典列表")
    @RequestMapping(value = "/dictItem")
    public ResponseResult dictItem(@RequestBody SysDict dict, HttpServletRequest req) {

        Assert.hasText(dict.getDictCode(), "字典项不能为空");
        //组装分页
        List<SysDictItem> list = dictItemService.getDictItemList(dict);

        return ResponseResult.ok(list);
    }

}
