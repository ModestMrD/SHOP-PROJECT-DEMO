package com.dq.product.background.controller;

import com.dq.common.POJO.MultiResultBean;
import com.dq.common.POJO.ResultBean;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author DuQian
 * @Date 2019/3/12
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${image.server}")
    private String image_server;

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(),extName,null);
            String fullPath = storePath.getFullPath();
            String path = new StringBuilder(image_server).append(fullPath).toString();
            return ResultBean.success(path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("请检查您的网络连接");
        }
    }

    @RequestMapping("multiUpload")
    @ResponseBody
    public MultiResultBean multiUpload(MultipartFile[] files){
        MultiResultBean resultBean = new MultiResultBean();
        String[] data = new String[files.length];
        for (int i = 0;i<data.length;i++){
            String originalFilename = files[i].getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            try {
                StorePath storePath = fastFileStorageClient.uploadFile(files[i].getInputStream(),files[i].getSize(),extName,null);
                String fullPath = storePath.getFullPath();
                String path = new StringBuilder(image_server).append(fullPath).toString();
                data[i] = path;
            } catch (IOException e) {
                e.printStackTrace();
                resultBean.setErrno("-1");
            }
        }
        resultBean.setData(data);
        resultBean.setErrno("0");
        return  resultBean;
    }
}
