package com.hurpods.ssm.blog.controller;

import com.alibaba.fastjson.JSON;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.UserService;
import com.hurpods.ssm.blog.utils.MyUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    ServletContext context;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    private static final String ARTICLE_PIC_UPLOAD_DIRECTORY = "resource" + File.separator + "img" + File.separator + "articlePic";
    private static final String AVATAR_UPLOAD_DIRECTORY = "resource" + File.separator + "img" + File.separator + "avatar";

    @RequestMapping("/uploadPic")
    @ResponseBody
    public String uploadPic(@RequestParam("upload") MultipartFile file) {
        MyUtil myUtil = new MyUtil();
        Logger logger = org.slf4j.LoggerFactory.getLogger(UploadController.class);
        Map<Object, Object> map = new HashMap<>();

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
                + ARTICLE_PIC_UPLOAD_DIRECTORY
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
            descFile = new File(parentPath + File.separator + myUtil.getRandomString(12) + suffix);
        }

        //写入文件
        try {
            file.transferTo(descFile);

            map.put("uploaded", true);

            url = descFile.getPath().split("resource")[1];
            map.put("url", url);

            msg = "success";
            map.put("msg", msg);
            System.out.println(descFile.getPath());
            return new JSONObject(map).toString();
        } catch (Exception e) {
            logger.error("上传失败，cause:{}", e.getMessage());

            map.put("uploaded", false);

            msg = "failure";
            map.put("msg", msg);

            map.put("url", "/");
            System.out.println(e.getMessage());
            return new JSONObject(map).toString();
        }
    }

    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, HttpSession session, HttpServletRequest req) {
        User user = (User) session.getAttribute("user");
        Integer id = user.getUserId();

        Map<String, String> map = new HashMap<>();
        String url;

        String filename = file.getOriginalFilename();
        //文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));

        File descFile = new File(context.getRealPath("")
                + File.separator
                + AVATAR_UPLOAD_DIRECTORY
                + File.separator
                + id
                + suffix);

        if (!descFile.getParentFile().exists()) {
            descFile.getParentFile().mkdirs();
        }

        while (descFile.exists()) {
            descFile.delete();
        }

        try {
            file.transferTo(descFile);

            String filePath = descFile.getPath();
            Thumbnails.of(filePath).size(300, 300).toFile(filePath);
            Thumbnails.of(filePath).sourceRegion(Positions.CENTER, 200, 200).size(160, 160).
                    keepAspectRatio(false).toFile(filePath);

            url = descFile.getPath().split("resource")[1];

            if (!url.equalsIgnoreCase(user.getUserAvatar())) {
                user.setUserAvatar(url);
                userService.updateUserInfo(user);
            }

            commentService.updateCommentUser(user.getUserNickName(), user.getUserAvatar(), user.getUserId());

            session.removeAttribute("user");
            session.invalidate();

            HttpSession session2 = req.getSession();
            session2.setAttribute("user", user);
            map.put("status", "true");
            map.put("msg", "头像上传成功，重新登陆后生效");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("status", "false");
            map.put("msg", "错误！" + e.getMessage());
        }
        return new JSONObject(map).toString();
    }
}
