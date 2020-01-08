package com.baizhi.cxx.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class HttpUtil {
    public static String getHttp(MultipartFile url,
                                 HttpServletRequest request,String path) throws UnknownHostException {
        String realPath = request.getServletContext().getRealPath(path);

        String newName=new Date().getTime()+"_"+url.getOriginalFilename();

        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        try {
            url.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String scheme = request.getScheme();

        String localhost = InetAddress.getLocalHost().toString();

        int serverPort = request.getServerPort();

        String contextPath = request.getContextPath();

        String uri=scheme+"://"+localhost.split("/")[1]+":"+serverPort+contextPath+path+newName;

        return uri;
    }
}
