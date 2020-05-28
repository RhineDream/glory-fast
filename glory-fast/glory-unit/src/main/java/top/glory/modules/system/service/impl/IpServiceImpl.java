package top.glory.modules.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.glory.common.utils.IPSeekerUtil;
import top.glory.modules.system.IpService;

import java.io.File;

/**
 * @author 言曌
 * @date 2018/4/9 下午9:32
 */

@Slf4j
@Service
public class IpServiceImpl implements IpService {
    private static IPSeekerUtil ipSeeker;
    @Value("${glory.path.qqwryPath}")
    private String filepath;
    ///Users/liuyanzhao/Documents/MyUtil/qqwry.dat

    @Override
    public String getIpArea(String ip) {
        if (ipSeeker == null) {
            try {
                ipSeeker = new IPSeekerUtil(new File(filepath));
            } catch (Exception e) {
                log.error("IP地址库实例化出错", e);
            }
        }
        return ipSeeker.getAddress(ip);
    }
}
