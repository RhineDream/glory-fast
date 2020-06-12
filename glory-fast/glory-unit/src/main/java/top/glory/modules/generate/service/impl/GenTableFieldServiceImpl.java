package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.generate.entity.GenTableField;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.generate.service.GenTableFieldService;
import top.glory.modules.generate.mapper.GenTableFieldMapper;
import top.glory.modules.system.entity.SysTableField;

import java.util.List;

@Service
public class GenTableFieldServiceImpl extends ServiceImpl<GenTableFieldMapper, GenTableField> implements GenTableFieldService {


    @Override
    public ResponseResult saveField(GenTableInfo tableInfo) {
        List<SysTableField> list = this.baseMapper.getTableField(tableInfo);
        List<GenTableField> fieldList = Lists.newArrayList();
        for (SysTableField sysTableField : list) {
            GenTableField _tableField = new GenTableField();

            _tableField.setGenTableInfoId(tableInfo.getId());
            _tableField.setGenTableName(sysTableField.getTableName());
            _tableField.setFieldName(sysTableField.getColumnName());
            String columnComment = sysTableField.getColumnComment();
            if(StringUtils.isBlank(columnComment)){
                columnComment = sysTableField.getColumnName();
            }
            _tableField.setFieldComment(columnComment);
            _tableField.setJdbcType(sysTableField.getColumnType());
            _tableField.setJavaType(transDataType(sysTableField.getColumnType()));
            _tableField.setJavaFieldName(transFieldName(sysTableField.getColumnName()));
            _tableField.setIfPrimaryKey(checkIfPrimaryKey(sysTableField.getColumnName()));
            //TODO 判断字段
            _tableField.setIfBlank("1");
            _tableField.setIfInsert("1");
            _tableField.setIfEdit("1");
            _tableField.setIfList("1");
            _tableField.setIfQuery("1");
            _tableField.setQueryType("like");
            _tableField.setSort(Double.valueOf(sysTableField.getOrdinalPosition()));

            fieldList.add(_tableField);
        }
        boolean flag = super.saveBatch(fieldList);
        if(!flag){
            return ResponseResult.fail(500,"保存失败");
        }
        return ResponseResult.ok();
    }

    /*校验是否是主键*/
    private String checkIfPrimaryKey(String columnName){
        if(StringUtils.equals("id",columnName)){
            return "1";
        }
        return "2";
    }

    /*转换字段类型 数据库到java*/
    private String transDataType(String columnType){
        String javaType = "";
        switch (columnType){
            case "varchar":
                javaType = "String";
                break;
            case "char":
                javaType = "String";
                break;
            case "date":
                javaType = "Date";
                break;
            case "datetime":
                javaType = "Date";
                break;
            case "bigint":
                javaType = "Long";
                break;
            case "longtext":
                javaType = "String";
                break;
            case "text":
                javaType = "String";
                break;
            case "int":
                javaType = "Integer";
                break;
            case "decimal":
                javaType = "Double";
                break;
            case "double":
                javaType = "Double";
                break;
            case "float":
                javaType = "Float";
                break;
            case "blob":
                javaType = "String";
                break;
            default:
                javaType = "String";
                break;
        }
        return javaType;
    }

    /*转换字段名称 数据库到java*/
    private String transFieldName(String columnName){
        String filedName = StringUtil.underline2Camel(columnName);
        return filedName;
    }




}
