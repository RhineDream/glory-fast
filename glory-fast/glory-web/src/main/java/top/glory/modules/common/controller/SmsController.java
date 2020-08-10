package top.glory.modules.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.VerificationCode;
import top.glory.modules.common.entity.BusSms;
import top.glory.modules.common.service.ApiService;

import javax.annotation.Resource;

/**
 * @author 春秋
 * @Description: 签约合同 Controller
 * @Date: 2020-07-19
 */
@Slf4j
@RequestMapping("/api/sms/")
@RestController
public class SmsController {

    @Resource
    private ApiService apiService;

    /**
     * 发送短信
     */
    @HandleLog("发送短信")
    @PostMapping(value = "/send")
    public ResponseResult insert(@RequestBody BusSms busSms) {
        String code = VerificationCode.getNumeral();
        String phoneNumber = busSms.getPhoneNumber();
        phoneNumber = phoneNumber.trim();
        ResponseResult res = apiService.sendSms(phoneNumber, code);
        return res;
    }

    /**
     * 发送短信
     */
    @HandleLog("发送短信")
    @PostMapping(value = "/check")
    public ResponseResult checkEffectCode(@RequestBody BusSms busSms) {
//        String code = VerificationCode.getNumeral();
        ResponseResult res = apiService.checkEffectCode(busSms.getPhoneNumber(), busSms.getMsgCode());
        return res;
    }
}
