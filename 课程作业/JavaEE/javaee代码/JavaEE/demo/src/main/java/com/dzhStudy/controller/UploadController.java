package com.dzhStudy.controller;

import com.dzhStudy.utils.FileUploadUtil;
import com.dzhStudy.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ZiHao Deng
 * @Created: 2023/4/8 12:58
 */
@RestController
public class UploadController {

    @PostMapping("upload")
    public Result upload(MultipartFile file){
        //user:文件加名称,图片或者文件会放在user文件夹
        String imgPath = FileUploadUtil.uploadFile(file, "user");
        return new Result().success(imgPath);
    }

}

