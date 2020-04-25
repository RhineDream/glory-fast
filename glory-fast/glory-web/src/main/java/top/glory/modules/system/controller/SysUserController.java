package top.glory.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.glory.common.system.query.QueryGenerator;
import top.glory.common.utils.PageUtils;
import top.glory.common.utils.PasswordUtil;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.vo.PageInfo;
import top.glory.modules.system.vo.Role;
import top.glory.modules.system.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Slf4j
@RequestMapping("/api/user/")
@RestController
public class SysUserController {

    @Resource
    private UserService userService;

    /**
     * 列表查询
     */
    @PostMapping(value = "/list")
    public ResponseResult list(@RequestBody(required = false) SysUser sysUser,HttpServletRequest req) {
        if(sysUser == null){
            sysUser = new SysUser();
        }
        //组装查询条件
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
        //组装分页
        IPage<SysUser> pageList = userService.page(new Page<SysUser>(sysUser.getPageNo(), sysUser.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return ResponseResult.ok(pageList);
    }

    /**
     * 列表查询
     */
    @PostMapping(value = "/list2")
    public PageInfo list2(@RequestBody(required = false) SysUser sysUser,HttpServletRequest req) {
        if(sysUser == null){
            sysUser = new SysUser();
        }
        //组装查询条件
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
        //组装分页
        IPage<SysUser> pageList = userService.page(new Page<SysUser>(sysUser.getPageNo(), sysUser.getPageSize()), queryWrapper);
        PageInfo pageInfo = PageUtils.transPageData(pageList);
        return pageInfo;
    }
    /**
     * 新增用户
     */
    @PostMapping(value = "/insert")
    public ResponseResult insert(@RequestBody SysUser user) {
        //密码加密
        String salt = StringUtil.randomGen(8);
        user.setSalt(salt);
        String passwordEncode = PasswordUtil.encrypt(user.getLoginName(), user.getPassword(), salt);
        user.setPassword(passwordEncode);
        user.setStatus("1");
        boolean flag = userService.save(user);
        if (flag) {
            return ResponseResult.ok("添加成功", user.getId());
        }
        return ResponseResult.fail(500, "添加失败");
    }

    /**
     * 修改用户
     */
    @PutMapping(value = "/update")
    public ResponseResult update(@RequestBody SysUser sysUser) {
        SysUser user = userService.getById(sysUser.getId());
        if (user == null) {
            ResponseResult.fail(500, "id找不到");
        } else {
            boolean flag = userService.updateById(sysUser);
            if (flag) {
                return ResponseResult.ok("修改成功", sysUser.getId());
            }
        }
        return ResponseResult.fail(500, "修改失败");
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/delete")
    public ResponseResult delete(@RequestBody List<String> idList) {
        if (idList.size() == 0) {
            ResponseResult.fail(500, "参数错误");
        } else {
            boolean flag = userService.removeByIds(idList);
            if (flag) {
                return ResponseResult.ok("删除成功，共" + idList.size() + "条");
            }
        }
        return ResponseResult.fail(500, "删除失败");
    }


    @RequestMapping("info")
    public ResponseResult Login(){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID()+"");
        userInfo.setName("管理员");
        userInfo.setUsername("撒旦法看就看");
        userInfo.setPassword("");
        userInfo.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
        userInfo.setStatus("1");
        userInfo.setTelephone("");
        userInfo.setLastLoginIp("27.154.74.117");
        userInfo.setLastLoginTime("1534837621348");
        userInfo.setRoleId("admin");
        userInfo.setToken("4291d7da9005377ec9aec4a71ea837f");

        Role role = new Role();

        role.setId("admin");
        role.setName("管理员");
        role.setDescribe("拥有所有权限");
        role.setStatus("1");
        role.setCreatorId("system");
        role.setCreateTime("1497160610259");


        String aa = "[{'roleId':'admin','permissionId':'dashboard','permissionName':'仪表盘22222','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'exception','permissionName':'异常页面权限','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'result','permissionName':'结果权限','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'profile','permissionName':'详细页权限','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'table','permissionName':'表格权限','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"import\",\"defaultCheck\":false,\"describe\":\"导入\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'import','describe':'导入','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'form','permissionName':'表单权限','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'order','permissionName':'订单管理','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'permission','permissionName':'权限管理','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'role','permissionName':'角色管理','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'table','permissionName':'桌子管理','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"query\",\"defaultCheck\":false,\"describe\":\"查询\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'query','describe':'查询','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false}],'actionList':null,'dataAccess':null},{'roleId':'admin','permissionId':'user','permissionName':'用户管理','actions':'[{\"action\":\"add\",\"defaultCheck\":false,\"describe\":\"新增\"},{\"action\":\"import\",\"defaultCheck\":false,\"describe\":\"导入\"},{\"action\":\"get\",\"defaultCheck\":false,\"describe\":\"详情\"},{\"action\":\"update\",\"defaultCheck\":false,\"describe\":\"修改\"},{\"action\":\"delete\",\"defaultCheck\":false,\"describe\":\"删除\"},{\"action\":\"export\",\"defaultCheck\":false,\"describe\":\"导出\"}]','actionEntitySet':[{'action':'add','describe':'新增','defaultCheck':false},{'action':'import','describe':'导入','defaultCheck':false},{'action':'get','describe':'详情','defaultCheck':false},{'action':'update','describe':'修改','defaultCheck':false},{'action':'delete','describe':'删除','defaultCheck':false},{'action':'export','describe':'导出','defaultCheck':false}],'actionList':null,'dataAccess':null}]";
//        JSONObject jsStr = JSONObject.parseObject(aa);
        JSONArray jsonArray=JSONArray.parseArray(aa);
        role.setPermissions(jsonArray);
        userInfo.setRole(role);

        return ResponseResult.ok(userInfo);
    }

//    @RequestMapping("nav")
//    public ResponseResult nav(){
//
//        String json = "[{'name':'dashboard','parentId':0,'id':1,'meta':{'icon':'dashboard','title':'仪表盘','show':true},'component':'RouteView','redirect':'/dashboard/workplace'},{'name':'workplace','parentId':1,'id':7,'meta':{'title':'工作台','show':true},'component':'Workplace'},{'name':'monitor','path':'https://www.baidu.com/','parentId':1,'id':3,'meta':{'title':'监控页（外部）','target':'_blank','show':true}},{'name':'Analysis','parentId':1,'id':2,'meta':{'title':'分析页','show':true},'component':'Analysis','path':'/dashboard/analysis'},{'name':'tests','parentId':1,'id':8,'meta':{'title':'测试功能','show':true},'component':'TestWork'},{'name':'form','parentId':0,'id':10,'meta':{'icon':'form','title':'表单页'},'redirect':'/form/base-form','component':'PageView'},{'name':'basic-form','parentId':10,'id':6,'meta':{'title':'基础表单'},'component':'BasicForm'},{'name':'step-form','parentId':10,'id':5,'meta':{'title':'分步表单'},'component':'StepForm'},{'name':'advanced-form','parentId':10,'id':4,'meta':{'title':'高级表单'},'component':'AdvanceForm'},{'name':'list','parentId':0,'id':10010,'meta':{'icon':'table','title':'列表页','show':true},'redirect':'/list/table-list','component':'PageView'},{'name':'table-list','parentId':10010,'id':10011,'path':'/list/table-list/:pageNo([1-9]\\\\d*)?','meta':{'title':'查询表格','show':true},'component':'TableList'},{'name':'basic-list','parentId':10010,'id':10012,'meta':{'title':'标准列表','show':true},'component':'StandardList'},{'name':'card','parentId':10010,'id':10013,'meta':{'title':'卡片列表','show':true},'component':'CardList'},{'name':'search','parentId':10010,'id':10014,'meta':{'title':'搜索列表','show':true},'redirect':'/list/search/article','component':'SearchLayout'},{'name':'article','parentId':10014,'id':10015,'meta':{'title':'搜索列表（文章）','show':true},'component':'SearchArticles'},{'name':'project','parentId':10014,'id':10016,'meta':{'title':'搜索列表（项目）','show':true},'component':'SearchProjects'},{'name':'application','parentId':10014,'id':10017,'meta':{'title':'搜索列表（应用）','show':true},'component':'SearchApplications'},{'name':'profile','parentId':0,'id':10018,'meta':{'title':'详情页','icon':'profile','show':true},'redirect':'/profile/basic','component':'RouteView'},{'name':'basic','parentId':10018,'id':10019,'meta':{'title':'基础详情页','show':true},'component':'ProfileBasic'},{'name':'advanced','parentId':10018,'id':10020,'meta':{'title':'高级详情页','show':true},'component':'ProfileAdvanced'},{'name':'result','parentId':0,'id':10021,'meta':{'title':'结果页','icon':'check-circle-o','show':true},'redirect':'/result/success','component':'PageView'},{'name':'success','parentId':10021,'id':10022,'meta':{'title':'成功','hiddenHeaderContent':true,'show':true},'component':'ResultSuccess'},{'name':'fail','parentId':10021,'id':10023,'meta':{'title':'失败','hiddenHeaderContent':true,'show':true},'component':'ResultFail'},{'name':'exception','parentId':0,'id':10024,'meta':{'title':'异常页','icon':'warning','show':true},'redirect':'/exception/403','component':'RouteView'},{'name':'403','parentId':10024,'id':10025,'meta':{'title':'403','show':true},'component':'Exception403'},{'name':'404','parentId':10024,'id':10026,'meta':{'title':'404','show':true},'component':'Exception404'},{'name':'500','parentId':10024,'id':10027,'meta':{'title':'500','show':true},'component':'Exception500'},{'name':'account','parentId':0,'id':10028,'meta':{'title':'个人页','icon':'user','show':true},'redirect':'/account/center','component':'RouteView'},{'name':'center','parentId':10028,'id':10029,'meta':{'title':'个人中心','show':true},'component':'AccountCenter'},{'name':'settings','parentId':10028,'id':10030,'meta':{'title':'个人设置','hideHeader':true,'hideChildren':true,'show':true},'redirect':'/account/settings/base','component':'AccountSettings'},{'name':'BaseSettings','path':'/account/settings/base','parentId':10030,'id':10031,'meta':{'title':'基本设置','show':false},'component':'BaseSettings'},{'name':'SecuritySettings','path':'/account/settings/security','parentId':10030,'id':10032,'meta':{'title':'安全设置','show':false},'component':'SecuritySettings'},{'name':'CustomSettings','path':'/account/settings/custom','parentId':10030,'id':10033,'meta':{'title':'个性化设置','show':false},'component':'CustomSettings'},{'name':'BindingSettings','path':'/account/settings/binding','parentId':10030,'id':10034,'meta':{'title':'账户绑定','show':false},'component':'BindingSettings'},{'name':'NotificationSettings','path':'/account/settings/notification','parentId':10030,'id':10034,'meta':{'title':'新消息通知','show':false},'component':'NotificationSettings'}]";
////        JSONObject jsStr = JSONObject.parseObject(json);
////        JSONObject obj = JSONArray.parseObject(json);
//        JSONArray jsonArray=JSONArray.parseArray(json);
//        return ResponseResult.ok(jsonArray);
//
//    }
}
