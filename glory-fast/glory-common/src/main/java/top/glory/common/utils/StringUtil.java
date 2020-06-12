package top.glory.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import top.glory.modules.system.entity.SysUser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUtil {
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 将驼峰命名转化成下划线
     * @param para
     * @return
     */
    public static String camelToUnderline(String para){
        if(para.length()<3){
            return para.toLowerCase();
        }
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        //从第三个字符开始 避免命名不规范
        for(int i=2;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 下划线转换为驼峰
     *
     * @param line 下划线字符串
     * @param firstIsUpperCase 首字母是否转换为大写
     * @return
     */
    public static String underline2Camel(String line, boolean ... firstIsUpperCase) {
        String str = "";

        if(StringUtils.isBlank(line)){
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            String [] strArr;
            // 不包含下划线，且第二个参数是空的
            if(!line.contains("_") && firstIsUpperCase.length == 0){
                sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                str = sb.toString();
            } else if (!line.contains("_") && firstIsUpperCase.length != 0){
                if (!firstIsUpperCase[0]) {
                    sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
                    str = sb.toString();
                } else {
                    sb.append(line.substring(0, 1).toUpperCase()).append(line.substring(1));
                    str = sb.toString();
                }
            } else if (line.contains("_") && firstIsUpperCase.length == 0) {
                strArr = line.split("_");
                for (String s : strArr) {
                    sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                }
                str = sb.toString();
                str = str.substring(0, 1).toLowerCase() + str.substring(1);
            } else if (line.contains("_") && firstIsUpperCase.length != 0) {
                strArr = line.split("_");
                for (String s : strArr) {
                    sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                }
                if (!firstIsUpperCase[0]) {
                    str = sb.toString();
                    str = str.substring(0, 1).toLowerCase() + str.substring(1);
                } else {
                    str = sb.toString();
                }
            }
        }
        return str;
    }


    /**
     * 随机数
     * @param place 定义随机数的位数
     */
    public static String randomGen(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<place;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }

    /**
     * 获取类的所有属性，包括父类
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    // 获取异常信息
    public static String getExceptionAllinfo(Exception ex) {
        String sOut = "";
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
            ex.printStackTrace(pw);
            pw.flush();
            sw.flush();
            sOut = sw.toString();

            pw.close();
            sw.close();
            sw = null;
            pw = null;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw!=null){
                    pw.close();
                }
                if(sw!=null){
                    sw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sOut;
    }

    /**
     * 首字母小写
     * @param oldStr
     * @return
     */
    public static String lowerFirst(String oldStr){

        char[]chars = oldStr.toCharArray();

        chars[0] += 32;

        return String.valueOf(chars);

    }

    public static void main(String[] args) {
        String face = lowerFirst("Face");
        String user_name = underline2Camel("user_name");
        System.out.println(user_name);
    }
}
