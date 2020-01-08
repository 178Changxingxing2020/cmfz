package com.baizhi.cxx.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.cxx.annotation.LogAnnotation;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.Dto.BannerDto;
import com.baizhi.cxx.util.BannerDateListener;
import com.baizhi.cxx.service.BannerService;
import com.baizhi.cxx.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Banner> queryAll(){
        List<Banner> list = bannerService.queryAll();
        return list;
    }

    @RequestMapping("queryByPage")
    @ResponseBody
    @LogAnnotation(value = "分页查所有轮播图")
    public BannerDto queryByPage(Integer rows, Integer page){
        BannerDto dto = bannerService.queryPersonByPage(rows, page);
        return  dto;
    }

//    @RequestMapping("modify")
//    @ResponseBody
//    public void modify(Banner banner,String oper){
//        if("add".equals(oper)){
//            banner.setId(UUID.randomUUID().toString());
//            bannerService.insert(banner);
//        } else if ("del".equals(oper)){
//            bannerService.remove(banner);
//        } else {
//            bannerService.modifly(banner);
//        }
//    }
    @RequestMapping("modify")
    @ResponseBody
    public Map modify(Banner banner, String oper,String[] id){
    HashMap hashMap = new HashMap();
    if("add".equals(oper)){
            String bannerId = UUID.randomUUID().toString();
            banner.setId(bannerId);
            bannerService.insert(banner);
            hashMap.put("bannerId",bannerId);
        System.out.println(bannerId);
        } else if ("del".equals(oper)){
            bannerService.remove(Arrays.asList(id));
            hashMap.put("bannerId",banner.getId());
        } else {
            if(banner.getUrl().equals("")){
                banner.setUrl(null);
            }
            hashMap.put("bannerId",banner.getId());
            bannerService.modifly(banner);

        }
        return  hashMap;
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        HashMap hashMap = new HashMap();
        //String realPath = session.getServletContext().getRealPath("/upload/img");
//        String newName=new Date().getTime()+url.getOriginalFilename();
//        String scheme = request.getScheme();
//        String localhost = InetAddress.getLocalHost().toString();
//        int serverPort = request.getServerPort();
//        String contextPath = request.getContextPath();
//        String uri=scheme+"://"+localhost.split("/")[1]+":"+serverPort+contextPath+"/upload/img/"+newName;
        String uri = HttpUtil.getHttp(url, request, "/upload/img/");



            Banner banner = new Banner();
            banner.setId(bannerId);
//            banner.setUrl("/upload/img/"+newName);
            banner.setUrl(uri);
            bannerService.modifly(banner);


        hashMap.put("status",200);
        return hashMap;
    }


    @RequestMapping("outBanner")
    public void outBanner(String url, HttpServletResponse response, HttpSession session) throws IOException {

        String name2=new Date().getTime()+"轮播图信息";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String encode = URLEncoder.encode(name2, "UTF-8");
        String fileName = encode.replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        List<Banner> list = bannerService.queryAll();
        EasyExcel.write(outputStream, Banner.class)
                .sheet("轮播图")
                .doWrite(list);
    }


    @RequestMapping("inBanner")
    @ResponseBody
    public void inBanner(MultipartFile file, HttpServletResponse response, HttpSession session,HttpServletRequest request) throws IOException {
//        ServletInputStream inputStream = request.getInputStream();
//        EasyExcel.read();
        //url="D:\\用户目录\\下载"+"1577967611227banner.xls";
        EasyExcel.read(file.getInputStream(),Banner.class,new BannerDateListener()).sheet().doRead();
    }
//    @RequestMapping("outBanner")
//    public void outBanner(String url, HttpServletResponse response, HttpSession session) throws IOException {
//
//        List<Banner> list = bannerService.queryAll();
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet();
//        HSSFRow row = sheet.createRow(0);
//        String[] str={"id","标题","图片","超链接","创建时间","描述","状态"};
//        for (int i = 0; i < str.length; i++) {
//            row.createCell(i).setCellValue(str[i]);
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            Banner banner = list.get(i);
//            HSSFRow row1 = sheet.createRow(i + 1);
//            row1.createCell(0).setCellValue(banner.getId());
//            row1.createCell(1).setCellValue(banner.getTitle());
//            row1.createCell(2).setCellValue(banner.getUrl());
//            row1.createCell(3).setCellValue(banner.getHref());
//            row1.createCell(4).setCellValue(banner.getCreateDate());
//            row1.createCell(5).setCellValue(banner.getDescription());
//            row1.createCell(6).setCellValue(banner.getStatus());
//        }
//
//
//        String name2=new Date().getTime()+".xls";
//        response.setHeader("Content-Disposition", "attachment; filename="+name2);
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        //workbook.write(new File("name2"),outputStream);
//    }



}
