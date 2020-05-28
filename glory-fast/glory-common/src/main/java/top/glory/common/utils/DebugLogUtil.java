package top.glory.common.utils;

import com.google.gson.Gson;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;

import java.util.HashSet;

public class DebugLogUtil {
    private static HashSet<String> typeshset = new HashSet<String>();
    public DebugLogUtil(){
        typeshset.add("java.lang.Integer");
        typeshset.add("java.lang.com.plat.common.model");
        typeshset.add("java.lang.Float");
        typeshset.add("java.lang.Long");
        typeshset.add("java.lang.Short");
        typeshset.add("java.lang.Byte");
        typeshset.add("java.lang.Boolean");
        typeshset.add("java.lang.Char");
        typeshset.add("java.lang.String");
        typeshset.add("int");
        typeshset.add("long");
        typeshset.add("short");
        typeshset.add("byte");
        typeshset.add("boolean");
        typeshset.add("char");
        typeshset.add("float");
    }

    public static String writeLogInfo(String[] paramNames, JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        try {
            if (paramNames == null || paramNames.length == 0) {
                for (int k = 0; k < args.length; k++) {
                    Object arg = args[k];
                    sb.append("=" + getObjectToStr(arg) + "; ");
                }
            } else {
                for (int k = 0; k < args.length; k++) {
                    if(k<paramNames.length){
                        sb.append(paramNames[k] + " ");
                    }else{
                        sb.append(" ");
                    }
                    Object arg = args[k];
                    if(arg!=null){
                        sb.append("=" + getObjectToStr(arg) + "; ");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append(StringUtil.getExceptionAllinfo(e));
        }

        return sb.toString();
    }

    public static String getObjectToStr(Object obj){
        String str = "";
        if(obj==null || obj.equals("")){
            return str;
        }

        String name = obj.getClass().getName();
        try {
            if(typeshset.contains(name)){
                return String.valueOf(obj);
            }else{
                if(name.indexOf("com.plat.common")!=-1){
                    str = new Gson().toJson(obj);
                }else if(name.indexOf("java.util.ArrayList")!=-1){
                    //str = new Gson().toJson(obj);
                }else if(name.indexOf("java.util.Map")!=-1){
                    //str = new Gson().toJson(obj);
                }else if(name.indexOf("java.util.HashMap")!=-1){
                    //str = new Gson().toJson(obj);
                }else if(name.indexOf("java.util.HashSet")!=-1){
                    //str = new Gson().toJson(obj);
                }else{
                    str = "objtype:"+name+";"+String.valueOf(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "objtype : "+name + "ex:"+StringUtil.getExceptionAllinfo(e);
        }
        return str;
    }

    /**
     * 得到方法参数的名称
     * @param cls
     * @param clazzName
     * @param methodName
     * @return
     * @throws NotFoundException
     */
    @SuppressWarnings("rawtypes")
    public static String[] getFieldsName(Class cls, String clazzName, String methodName){
        try {
            ClassPool pool = ClassPool.getDefault();
            ClassClassPath classPath = new ClassClassPath(cls);
            pool.insertClassPath(classPath);
            CtClass cc = pool.get(clazzName);
            CtMethod cm = cc.getDeclaredMethod(methodName);
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                // exception
                return null;
            }

            String[] paramNames = new String[cm.getParameterTypes().length];
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++){
                paramNames[i] = attr.variableName(i + pos); //paramNames即参数名
            }
            return paramNames;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
