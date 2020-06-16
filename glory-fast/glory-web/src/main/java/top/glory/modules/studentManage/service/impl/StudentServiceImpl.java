package top.glory.modules.studentManage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.studentManage.entity.Student;
import top.glory.modules.studentManage.service.StudentService;
import top.glory.modules.studentManage.mapper.StudentMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 春秋
 * @Description: 学生管理 Service实现类
 * @Date: 2020-06-16
 */


@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
