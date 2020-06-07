package top.glory.modules.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.generate.GenTableField;
import top.glory.modules.generate.service.GenTableFieldService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/api/genCodeField")
@RestController
public class GenTableFieldController {

    @Resource
    private GenTableFieldService genTableFieldService;


    /**
     * 列表查询 - 不分页
     */
    @HandleLog("查看数据库表列表")
    @RequestMapping(value = "/list")
    public ResponseResult list(@RequestBody GenTableField tableField, HttpServletRequest req) {
        //组装查询条件
        QueryWrapper<GenTableField> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gen_table_info_id",tableField.getGenTableInfoId());
        queryWrapper.orderByAsc("sort");
        List<GenTableField> list = genTableFieldService.list(queryWrapper);
        return ResponseResult.ok(list);
    }

    /**
     * 新增用户
     */
    @HandleLog("新增数据库表信息")
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody GenTableField tableField) {

        boolean flag = genTableFieldService.save(tableField);
        if (flag) {
            return ResponseResult.ok("添加成功", tableField.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改数据库表字段
     */
    @HandleLog("修改数据库表信息")
    @PutMapping(value = "/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<GenTableField> list) {
        boolean flag = genTableFieldService.updateBatchById(list);
        if (flag) {
            return ResponseResult.ok("修改成功",list.size());
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
            boolean flag = genTableFieldService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


}
