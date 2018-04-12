package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by zhou_yanga on 2018/4/11.
 */
@Controller
public class FileUploadController {

    @RequestMapping("upload")
    @ResponseBody
    public String uploadimg(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String host =  request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String oriFlieName = file.getOriginalFilename();
        OutputStream os = null;
        InputStream is = null;
        String path = "";
        try {
            is = file.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            path = "/" + "picture";
            File outDir = new File(path);
            outDir.mkdirs();
            String fileName = System.currentTimeMillis() + oriFlieName.substring(oriFlieName.lastIndexOf("."));
            path += "/" +fileName;
            os = new FileOutputStream(path);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            os.close();
            is.close();
        }
        return host + path;
    }
}
