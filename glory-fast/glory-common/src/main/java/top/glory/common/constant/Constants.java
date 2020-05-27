package top.glory.common.constant;

/**
 * 2015/6/25
 * <p/>
 * 定义常量
 *
 * @author Jia_RG
 */
public abstract class Constants {

    private Constants() {
    }

    /****************************************** Exception Status *********************************************************/
    public static final int REQUEST_ACCESS_TOKEN_INVALID = 1011;	
    public static final int REQUEST_ACCESS_TOKEN_EXPIRED = 1012;
    public static final int REQUEST_REFRESH_TOKEN_INVALID = 1013;
    public static final int REQUEST_REFRESH_TOKEN_EXPIRED = 1014;
    
    public static final int REQUEST_DOES_NOT_HAVE_PERMISSION = 1015;
    
    
    /******************************************************************************************************************/
    
    
    /****************************************** Exception Message *********************************************************/
	public static final String REQUEST_MISSING_PARAM_EXCEPTION = "请求缺失参数！";
	public static final String REQUEST_URL_NOT_FOUND_EXCEPTION = "请求URL不存在！";
	public static final String REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "请求方式不支持！";
	public static final String REQUEST_RESOURCE_UNAUTHORIZED = "请求资源未授权，不允许访问！";
	public static final String REQUEST_METHOD_ARGUMENT_TYPE_MISMATCHEXCEPTION = "请求方法参数类型不匹配！";
	public static final String REQUEST_INTERFACE_EXCEEDS_THE_LIMIT_WITHIN_THE_SPECIFIED_TIME = "请求接口在规定时间内超出限定次数！";
	/******************************************************************************************************************/

    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";

    /**
     * 测试缓存key
     */
    public static final String TEST_DEMO_CACHE = "test:demo";

    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_text";

}
