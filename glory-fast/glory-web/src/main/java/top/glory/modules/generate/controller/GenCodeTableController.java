package top.glory.modules.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.generate.GenCodeTable;
import top.glory.modules.generate.GenCodeTableService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/api/genCodeTable")
@RestController
public class GenCodeTableController {

    @Resource
    private GenCodeTableService genCodeTableService;


    @GetMapping(value = "/list")
    public ResponseResult queryPageList(GenCodeTable genCodeTable,
                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                        HttpServletRequest req) {
        QueryWrapper<GenCodeTable> queryWrapper = QueryGenerator.initQueryWrapper(genCodeTable, req.getParameterMap());
//        QueryWrapper<GenCodeTable> queryWrapper = new QueryWrapper<>();
        Page<GenCodeTable> page = new Page<GenCodeTable>(pageNo, pageSize);
        IPage<GenCodeTable> pageList = genCodeTableService.page(page, queryWrapper);
        return ResponseResult.ok(pageList);
    }


}
