package top.glory.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class DocumentHandler {
    private static Configuration configuration = null;

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc(Map<String, Object> objectMap, String dirPath, String filePath, String tempPath) {
        //要填入模本的数据文件
        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        //这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(this.getClass(), "/template");
        Template t = null;
        try {
            //test.ftl为要装载的模板
            t = configuration.getTemplate(tempPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出文档路径及名称

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File outFile = new File(filePath);

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(objectMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            t.process(objectMap, out);
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        // DocumentHandler dh=new DocumentHandler();
        // dh.createDoc();
        // MyTest mt=new MyTest();
        // mt.createDoc();
    }

}
