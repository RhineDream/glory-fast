package top.glory.modules.common.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.glory.common.constant.Constants;
import top.glory.common.utils.RedisUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.SendSms;
import top.glory.modules.common.entity.BusSms;
import top.glory.modules.common.service.ApiService;
import top.glory.modules.common.service.BusSmsService;

import javax.annotation.Resource;

/**
 * @author 春秋
 * @Description: 签约合同 Service实现类
 * @Date: 2020-07-19
 */

@Service
@Transactional
public class ApiServiceImpl implements ApiService {

    @Autowired
    @Lazy
    private RedisUtils redisUtils;

    @Resource
    private BusSmsService busSmsService;
    @Override
    public ResponseResult sendSms(String phoneNumbers, String msg) {
        String result = SendSms.sendSms(phoneNumbers, msg);
        BusSms sms = new BusSms();
        sms.setPhoneNumber(phoneNumbers);
        sms.setMsgCode(msg);
        sms.setSendResult(result);
        busSmsService.save(sms);

        //验证码存放redis，有效时间30分钟
        redisUtils.hset(Constants.KEY_SMS_CODE,phoneNumbers,msg,60 * 30);   //单位秒 时间30分钟

        return ResponseResult.ok("短信发送成功，请注意查收");
    }

    @Override
    public ResponseResult checkEffectCode(String phoneNumbers, String code) {
        //验证码存放redis，有效时间30分钟
        Object redisCode = redisUtils.hget(Constants.KEY_SMS_CODE, phoneNumbers);//单位秒 时间30分钟
        if(redisCode == null){
            return ResponseResult.build(400,"验证码失效,请重新获取");
        }else {
            String oldCode = (String) redisCode;
            if(StringUtils.equals(code,oldCode)){
                return ResponseResult.ok("验证通过");
            }else {
                return ResponseResult.build(400,"验证码失效,请重新获取");
            }
        }
    }
}
