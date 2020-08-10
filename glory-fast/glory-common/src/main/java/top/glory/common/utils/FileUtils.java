package top.glory.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class FileUtils {

    /***
     * 计算字符串的Content-MD5
     * @param str 文件路径
     * @return
     */
    public static String getStringContentMD5(String str) {
        // 获取文件MD5的二进制数组（128位）
        byte[] bytes = getFileMD5Bytes1282(str);
        // 对文件MD5的二进制数组进行base64编码
        return new String(Base64.encodeBase64(bytes));
    }

    /***
     * 获取文件MD5-二进制数组（128位）
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getFileMD5Bytes1282(String filePath) {
        FileInputStream fis = null;
        byte[] md5Bytes = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md5.update(buffer, 0, length);
            }
            md5Bytes = md5.digest();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return md5Bytes;
    }


    /**
     * @Description: 下载文件名
     * @CreateTime: 2020年7月29日
     * @param remoteFilePath 远程文件路径
     * @param localPath 本地文件路径
     */
    public static void downFile(String remoteFilePath, String localPath,String dirPath) {

        File file2 = new File(dirPath); 	//创建文件夹 如果不存在
        if(!file2.exists()){
            file2.mkdirs();

            log.info(dirPath);
            log.info("创建文件夹成功======================"+dirPath);
        }

        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localPath);
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bis != null){
                    bis.close();
                }
                if(bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

//        System.out.println("结果：" + getStringContentMD5("D:/gloryFile/1001001/20200725/app设计初稿_20200725122106.docx"));
        String aaa= "https://esignoss.esign.cn/1111563786/56948945-3898-44c7-a5ef-1f2f79b22de4/%E5%8A%B3%E5%8A%A8%E5%90%88%E5%90%8C.pdf?Expires=1596100175&OSSAccessKeyId=LTAIdvHfiVrzDKbE&Signature=xvf0J2%2FE%2F8AlyvLkWrVw5vzwbtE%3D";
        String localPath = "C://gloryFile/1001003/20200730/廊坊艾瑞思电子商务有限公司20200730160916.pdf";
        String dirPath = localPath.substring(0,localPath.lastIndexOf("/")+1);
        System.out.println(dirPath);
        downFile(aaa, localPath,dirPath);
    }
}
