package top.glory.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import top.glory.common.utils.DateUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.file.entity.BusFile;
import top.glory.modules.file.service.BusFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RequestMapping("/api/file/")
@RestController
public class FileController {

    @Resource
    private BusFileService fileService;

    @Value("${glory.path.upload}")
    private String localFilePath;

    /**
     * 异步上传图片
     * @param files
     * @return
     * @throws IOException
     * @throws IllegalStateException
     * ResponseResult 是自己定义的返回值对象，可随便定义
     */
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public ResponseResult uploadFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, String referId, String fileType, String describe) throws IllegalStateException, IOException {
        //支持多图片上传
        String result = "";
        for(int i=0;i<files.length;i++){
            if (!files[i].isEmpty()) {

                String currDate = DateUtils.getCurrentDateStr("yyyyMMdd");
                //文件夹路径：文件存储根路径 + fileType + 日期currDate + 文件名

                long size = files[i].getSize();

                //获得原始文件名;
                String fileRealName = files[i].getOriginalFilename();

                //获取服务器指定文件存取路径
                String savedDir = localFilePath;

                String dir = savedDir + "/" + fileType + "/" + currDate; //得到文件路径

                //点号的位置
                int pointIndex =  fileRealName.lastIndexOf(".");
                //截取文件后缀
                String fileSuffix = fileRealName.substring(pointIndex);

                String filePreName="";
                //获取文件名不带后缀名
                filePreName=fileRealName.substring(0,fileRealName.lastIndexOf("."));

                String date = DateUtils.getCurrentDateStr("yyyyMMddHHmmss");

                //文件存取名
                String savedFileName = filePreName + "_" +date.concat(fileSuffix);

                File file2 = new File(dir); 	//创建文件夹 如果不存在
                if(!file2.exists()){
                    file2.mkdirs();

                    System.out.println(dir);
                    System.out.println("创建文件夹成功======================"+dir);
                }

                File savedFile = new File(dir,savedFileName );
                boolean isCreateSuccess = savedFile.createNewFile();
                if(isCreateSuccess){
                    files[i].transferTo(savedFile);  //转存文件
                }

                String filepath = "/" +fileType + "/" + currDate + "/" +savedFileName;
                result = result+ filepath + ",";




                BusFile busFile = new BusFile();
                busFile.setReferId(referId);
                busFile.setFileName(savedFileName);
                busFile.setFileSize(String.valueOf(size));
                if(fileSuffix.contains(".")){
                    String _fileMime = fileSuffix.replace(".", "");
                    busFile.setFileMime(_fileMime);
                }else {
                    busFile.setFileMime(fileSuffix);
                }
                busFile.setFilePath(filepath);
                busFile.setFileDesc(describe);

                //保存文件信息
                fileService.saveFile(busFile);

            }
        }

        //多个文件以逗号分隔，然后去掉最后一个逗号
        result = result.substring(0,result.length()-1);
        return ResponseResult.ok(result);
    }


    @GetMapping(value = "/view/**")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //http://127.0.0.1:8888/pdf-server/document/1028075/20200522/20200512-%E6%B1%BD%E8%BD%A6%E5%90%88%E6%A0%BC%E8%AF%81%E7%9B%91%E7%AE%A1%E5%8D%8F%E8%AE%AE-%E8%BD%A6%E8%BE%86OBD%E7%9B%91%E7%AE%A1%E8%A1%A5%E5%85%85%E5%8D%8F%E8%AE%AE%E4%B8%89%E6%96%B9_1028075_20200522140004_89c4ab5e6ce349bd8b689f27ff3844d8.docx
        //document/1028075/20200522/20200512-%E6%B1%BD%E8%BD%A6%E5%90%88%E6%A0%BC%E8%AF%81%E7%9B%91%E7%AE%A1%E5%8D%8F%E8%AE%AE-%E8%BD%A6%E8%BE%86OBD%E7%9B%91%E7%AE%A1%E8%A1%A5%E5%85%85%E5%8D%8F%E8%AE%AE%E4%B8%89%E6%96%B9_1028075_20200522140004_89c4ab5e6ce349bd8b689f27ff3844d8.docx
        // ISO-8859-1 ==> UTF-8 进行编码转换

        String filePath = extractPathFromPattern(request);//127.0.0.1:8888/document/1.png

        localFileServer(response, filePath);
    }

    /**
     * 把指定URL后的字符串全部截断当成参数
     * 这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
     *
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }


    private boolean checkSuffix(String imgPath) {
        Boolean flag = false;
        //图片格式
        String[] FILETYPES = new String[]{
                ".jpg", ".bmp", ".jpeg", ".png", ".gif",
                ".JPG", ".BMP", ".JPEG", ".PNG", ".GIF"
        };
        if (!StringUtils.isBlank(imgPath)) {
            for (int i = 0; i < FILETYPES.length; i++) {
                String fileType = FILETYPES[i];
                if (imgPath.endsWith(fileType)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }


    //本地当前正在使用的文件服务器
    private void localFileServer(HttpServletResponse response, String filePath) {
        //文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            filePath = filePath.replace("..", "");
            if (filePath.endsWith(",")) {
                filePath = filePath.substring(0, filePath.length() - 1);
            }
            String localPath = localFilePath;
            String downloadFilePath = localPath + File.separator + filePath;
            System.out.println("本地文件服务器：文件路径==> " + downloadFilePath);
            File file = new File(downloadFilePath);
            if (file.exists()) {
                if (checkSuffix(fileName)) {
                    System.out.println("---------->此文件是图片，直接查看不下载");
                    response.setContentType("image/jpeg;charset=utf-8");    //图片走查看
                } else {
                    System.out.println("---------->此文件是非图片，不能直接查看，强行下载");
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
                }
                inputStream = new BufferedInputStream(new FileInputStream(file));
                outputStream = response.getOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                response.flushBuffer();
            }

        } catch (Exception e) {
            System.out.println("文件下载失败" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
