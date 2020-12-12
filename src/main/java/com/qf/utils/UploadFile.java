package com.qf.utils;

import com.google.gson.Gson;
import com.qf.common.ResultResp;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Component
public class UploadFile {

    @Value("${qinniu.ak}")
    private String ak;
    @Value("${qinniu.sk}")
    private String sk;
    @Value("${qinniu.bucket}")
    private String bucket;
    @Value("${qinniu.url}")
    private String url;


    public ResultResp uploadFile(MultipartFile file){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

//默认不指定key的情况下，以文件内容的hash值作为文件名
        ResultResp resultResp = new ResultResp();
        String key = null;
        try {
            byte[] bytes = file.getBytes();
            Auth auth = Auth.create(ak, sk);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);

                resultResp.setCode(200);
                resultResp.setData(url+putRet.key);
                resultResp.setMessage("文件上传成功！");
                return resultResp;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultResp.setCode(201);
        resultResp.setMessage("文件上传失败！");
        return resultResp;
    }

}
