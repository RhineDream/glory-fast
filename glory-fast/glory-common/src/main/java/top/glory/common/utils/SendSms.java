package top.glory.common.utils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import top.glory.common.constant.Constants;

public class SendSms {

    public static String sendSms(String phoneNumbers,String msg) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", Constants.SIGN_NAME);
        request.putQueryParameter("TemplateCode", Constants.TEMPLATE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+msg+"\"}");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public static void main(String[] args) {
        String code = VerificationCode.getNumeral();
        SendSms.sendSms("18910015313",code);
    }
}
