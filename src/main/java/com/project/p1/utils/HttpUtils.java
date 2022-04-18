package com.project.p1.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpUtils {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int lenght = 0;
        byte[] bytes = new byte[1024];
        while ((lenght = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, lenght);
        }
        String requestBody = outputStream.toString("utf-8");
        return requestBody;
    }
}
