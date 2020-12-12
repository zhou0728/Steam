package com.qf.controller;


import com.qf.common.ResultResp;
import com.qf.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadFileController {

    @Autowired
    UploadFile uploadFile;

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public ResultResp uploadFile(@RequestParam("file") MultipartFile file){
        return uploadFile.uploadFile(file);
    }
}
