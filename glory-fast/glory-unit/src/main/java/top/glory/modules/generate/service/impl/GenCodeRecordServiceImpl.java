package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import io.netty.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.glory.common.constant.Constants;
import top.glory.common.utils.DateUtils;
import top.glory.common.utils.DocumentHandler;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.generate.entity.GenCodeRecord;
import top.glory.modules.generate.entity.GenTableField;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.generate.service.GenCodeRecordService;
import top.glory.modules.generate.mapper.GenCodeRecordMapper;
import top.glory.modules.generate.service.GenTableFieldService;
import top.glory.modules.generate.service.GenTableInfoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GenCodeRecordServiceImpl extends ServiceImpl<GenCodeRecordMapper, GenCodeRecord> implements GenCodeRecordService {

    @Resource
    private GenTableFieldService genTableFieldService;

    @Resource
    private GenTableInfoService genTableInfoService;

    @Override
    public ResponseResult createCodeFile(GenCodeRecord codeRecord) {
        //生成代码信息
        GenCodeRecord genCodeRecord = super.getById(codeRecord.getId());
        String genTableInfoId = genCodeRecord.getGenTableInfoId();

        //主表信息
        GenTableInfo tableInfo = genTableInfoService.getById(genTableInfoId);


        QueryWrapper<GenTableField> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gen_table_info_id",genTableInfoId);
        queryWrapper.orderByAsc("sort");

        //字段信息
        List<GenTableField> fieldList = genTableFieldService.list(queryWrapper);

        ResponseResult res = createCode(genCodeRecord,tableInfo,fieldList);

        return res;
    }

    /**
     * 生成代码主方法
     * @param genCodeRecord
     * @param tableInfo
     * @param fieldList
     */
    private ResponseResult createCode(GenCodeRecord genCodeRecord, GenTableInfo tableInfo, List<GenTableField> fieldList) {

        //获取模板
        String s = dataToCodeFile(genCodeRecord, tableInfo, fieldList);
        System.out.println(s);

        return ResponseResult.ok(s);
    }

    private String dataToCodeFile(GenCodeRecord genCodeRecord, GenTableInfo tableInfo, List<GenTableField> fieldList) {

        List<GenTableField> fieldLists = Lists.newArrayList();
        //处理字段列表
        for (GenTableField field : fieldList) {
            String fieldName = field.getFieldName();
            if(!Constants.FIELD_EXCLUDE_LIST.contains(fieldName)){
                fieldLists.add(field);
            }
        }

        //生成模板
        System.out.println("正在给模板填充数据...");
        DocumentHandler documentHandler = new DocumentHandler();
        Map<String, Object> map = new HashMap<>();

        map.put("genCodeRecord",genCodeRecord); //包信息
        map.put("tableInfo",tableInfo); //表信息
        map.put("fieldLists",fieldLists); //字段信息
        map.put("currDate", DateUtils.getCurrentDateStr("yyyy-MM-dd"));
        String clazzName = tableInfo.getClazzName();
        String lowerClazzName = StringUtil.lowerFirst(clazzName);
        map.put("lowerClazzName", lowerClazzName);

        String packageName = genCodeRecord.getPackageName();
        packageName = packageName.replaceAll("\\.","\\/");
        String moduleName = genCodeRecord.getModuleName();

        System.out.println("map" + map);
        String msg = "";
        for (String temp : Constants.GEN_CODE_ONE_TABLE_TEMP) {
            String templ = "/oneTable/"+temp;
            String dirPath = Constants.CODE_FILE_PATH+"/"+packageName+"/"+moduleName;
//            String filePath = Constants.CODE_FILE_PATH+"/"+packageName+"/"+moduleName+"/entity";
            String filePath = "";

            if(temp.contains("entity.ftl")){    //实体类
                dirPath = dirPath +"/entity";
                filePath = dirPath + "/"+tableInfo.getClazzName()+".java";
            }
            if(temp.contains("mapperXml.ftl")){    //dao层xml配置文件
                dirPath = dirPath +"/mapper/xml";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Mapper.xml";
            }
            if(temp.contains("mapper.ftl")){    //dao层接口
                dirPath = dirPath +"/mapper";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Mapper.java";
            }
            if(temp.contains("service.ftl")){    //service接口
                dirPath = dirPath +"/service";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Service.java";
            }
            if(temp.contains("serviceImpl.ftl")){    //service实现类
                dirPath = dirPath +"/service/impl";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"ServiceImpl.java";
            }
            if(temp.contains("controller.ftl")){    //controller
                dirPath = dirPath +"/controller";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Controller.java";
            }

            if(temp.contains("reactService.ftl")){    //reactService.ftl
                dirPath = dirPath +"/react/api";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Service.js";
            }
            if(temp.contains("reactModels.ftl")){    //reactModels.ftl
                dirPath = dirPath +"/react/service";
                filePath = dirPath + "/"+lowerClazzName+"Models.js";
            }
            if(temp.contains("reactList.ftl")){    //reactList.ftl
                dirPath = dirPath +"/react/list";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"List.js";
            }
            if(temp.contains("reactListStyle.ftl")){    //reactListStyle.ftl
                dirPath = dirPath +"/react/list";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"List.less";
            }
            if(temp.contains("reactModal.ftl")){    //reactModal.ftl
                dirPath = dirPath +"/react/list";
                filePath = dirPath + "/"+tableInfo.getClazzName()+"Modal.js";
            }

            //生成代码
            documentHandler.createDoc(map,dirPath,filePath,templ);
            log.info("代码生成路径 = {}",filePath);

            msg = "代码已生成，路径==>【"+Constants.CODE_FILE_PATH+"/"+packageName+"/"+moduleName+"】";

        }


        return msg;
    }

}
