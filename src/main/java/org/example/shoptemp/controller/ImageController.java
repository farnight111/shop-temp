package org.example.shoptemp.controller;

import cn.hutool.core.io.FileUtil;
import org.example.shoptemp.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author czx
 * @date 2021/8/7
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private static final String BASE_PATH = "D:/shop-image";
    @Autowired
    private ServletContext servletContext;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        FileUtil.mkdir(BASE_PATH);
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(new File(BASE_PATH, originalFilename))){
            byte[] buf = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }
        return Result.success();
    }

    @GetMapping("/preview")
    public void preview(String imagename, HttpServletResponse response) throws IOException {
        response.setContentType(servletContext.getMimeType(imagename));
        String contentFilename = URLEncoder.encode(imagename, "UTF-8");
        response.setHeader("Content-Disposition", "inline;filename=" + contentFilename);
        try (FileInputStream in = new FileInputStream(new File(BASE_PATH, imagename));
             OutputStream out = response.getOutputStream()) {
            byte[] buf = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
            out.flush();
        }
    }
}
