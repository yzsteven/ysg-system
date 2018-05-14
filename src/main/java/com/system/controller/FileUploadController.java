package com.system.controller;

import com.api.model.Response;
import com.api.model.ResultCode;
import com.system.util.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Response uploadimg(@RequestParam  MultipartFile file, HttpServletRequest request) throws IOException {
        String host =  request.getScheme() + "://" + request.getServerName();
        String oriFlieName = file.getOriginalFilename();
        OutputStream os = null;
        InputStream is = null;
        String path = "";
        try {
            is = file.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            path = "/" + "pictures";
            String savePicUrl = CommonUtils.getProperties("savePicUrl");
            File outDir = new File(savePicUrl);
            outDir.mkdirs();
            String fileName = System.currentTimeMillis() + oriFlieName.substring(oriFlieName.lastIndexOf("."));
            savePicUrl += "/" +fileName;
            path += "/" +fileName;
            os = new FileOutputStream(savePicUrl);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            os.close();
            is.close();
        }
        return new Response(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),host + path);
    }
}
