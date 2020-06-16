package top.glory.modules.studentManage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.studentManage.entity.Student;

/**
 * @author 春秋
 * @Description: 学生管理 Dao接口
 * @Date: 2020-06-16
 */

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
