package com.project.p1.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUploadUtils {

    public static Map<String, Object> parseRequest(HttpServletRequest request){
        Map<String, Object> params = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        //该对象就是解析请求的处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //  bytes
        //upload.setFileSizeMax(1024);
        try {
            //FileItem其实就是对页面中input的封装，页面中每提交一份input，那么就会有一个FileItem
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                //input有可能是表单数据，也有可能是上传的文件
                if(item.isFormField()){
                    //是表单数据
                    processFormField(item, params);
                }else {
                    //不是表单数据
                    processUploadedFile(item, params, request);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return params;
    }

    private static void processUploadedFile(FileItem item, Map<String, Object> map, HttpServletRequest request) {
        // input的name属性
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String uuid = UUID.randomUUID().toString();
        //文件后缀名
        fileName = uuid + fileName;
        //  0x  4----1  ee1fffc------>
        //   770c4cce----->
        //   10000   100
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();
        String relativePath = "image";
        for (char aChar : chars) {
            relativePath = relativePath + "/" + aChar;
        }
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();
        System.out.println(fieldName + " " + fileName + " " + contentType + " " + isInMemory + " " + sizeInBytes);

        relativePath = relativePath + "/" + fileName;
        String realPath = request.getServletContext().getRealPath(relativePath);
        File file = new File(realPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            item.write(file);
            //   /app/image/1.jpeg
            map.put(fieldName, relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processFormField(FileItem item, Map<String, Object> map) {
        String name = item.getFieldName();
        String value = null;
        try {
            value = item.getString("utf-8");
            map.put(name, value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(name + "-" + value);
    }

}
