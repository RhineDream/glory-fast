package top.glory.modules.common.service;

import top.glory.common.utils.ResponseResult;

/**
 * @author 春秋
 * @Description: 签约合同 Service接口
 * @Date: 2020-07-19
 */

public interface ApiService {
    /**
     * 发短信
     */
    ResponseResult sendSms(String phoneNumbers,String msg);

    ResponseResult checkEffectCode(String phoneNumbers,String code);
}
