package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.utils.MyUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    ServletContext context;

    private static final String UPLOAD_DIRECTORY = "resource" + File.separator + "img" + File.separator + "articlePic";

    @RequestMapping("/uploadPic")
    @ResponseBody
    public String uploadPic(@RequestParam("upload") MultipartFile file) {
        MyUtil myUtil = new MyUtil();
        Logger logger = org.slf4j.LoggerFactory.getLogger(UploadController.class);
        Map<Object,Object> map = new HashMap<>();
        boolean uploaded;
        String url;
        String msg;
        //取文件后缀
        //文件的完整名称
        String filename = file.getOriginalFilename();
        //文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));

        //创建文件
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
        File descFile = new File(context.getRealPath("")
                + File.separator
                + UPLOAD_DIRECTORY
                + File.separator
                + dateDirs
                + File.separator
                + myUtil.getRandomString(8)
                + suffix);

        //创建目录
        if (!descFile.getParentFile().exists()) {
            descFile.getParentFile().mkdirs();
        }
        while (descFile.exists()) {
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + myUtil.getRandomString(8) + suffix);
        }

        //写入文件
        try {
            file.transferTo(descFile);
            uploaded=true;
            map.put("uploaded",uploaded);

            url=descFile.getPath().split("resource")[1];
            map.put("url",url);

            msg="success";
            map.put("msg",msg);
            System.out.println(descFile.getPath());
            return new JSONObject(map).toString();
        } catch (Exception e) {
            logger.error("上传失败，cause:{}", e.getMessage());

            uploaded=false;
            map.put("uploaded",uploaded);

            msg="failure";
            map.put("msg",msg);

            map.put("url","/");
            System.out.println(e.getMessage());
            return new JSONObject(map).toString();
        }
    }
}
