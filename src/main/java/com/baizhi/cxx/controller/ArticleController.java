package com.baizhi.cxx.controller;

import com.baizhi.cxx.Dto.AlbumDto;
import com.baizhi.cxx.Dto.ArticleDto;
import com.baizhi.cxx.dao.ArticleDao;
import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.Article;
import com.baizhi.cxx.service.AlbumService;
import com.baizhi.cxx.service.ArticleService;
import com.baizhi.cxx.util.HttpUtil;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleDao articleDao;

    @RequestMapping("queryByPage")
    @ResponseBody
    public ArticleDto queryByPage(Integer rows, Integer page){
        ArticleDto dto = articleService.queryByPage(rows, page);
        return  dto;
    }
    @RequestMapping("queryAll")
    @ResponseBody
    public List<Article> queryAll(Integer rows, Integer page){
        List<Article> list = articleDao.selectAll();
        return  list;
    }
//    @RequestMapping("modify")
//    @ResponseBody
//    public Map modify(Album album, String oper,String[] id){
//    HashMap hashMap = new HashMap();
//    if("add".equals(oper)){
//            String albumId = UUID.randomUUID().toString();
//            album.setId(albumId);
//            albumService.insert(album);
//            hashMap.put("albumId",albumId);
//        //System.out.println(bannerId);
//        } else if ("del".equals(oper)){
//            albumService.remove(Arrays.asList(id));
//            hashMap.put("albumId",album.getId());
//        } else {
//            if(album.getUrl().equals("")){
//                album.setUrl(null);
//            }
//            hashMap.put("albumId",album.getId());
//            albumService.modifly(album);
//
//        }
//        return  hashMap;
//    }

    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile imgFile, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        HashMap hashMap = new HashMap();
        //方法需要返回的信息 error 0 1  成功时URL
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //网络路径
        try {
            String http = HttpUtil.getHttp(imgFile, request, "/upload/articleImg/");
            hashMap.put("error",0);
            hashMap.put("url",http);
        }catch (Exception e){
            hashMap.put("error",1);
            e.printStackTrace();
        }
        return hashMap;
    }

    @RequestMapping("showAllImg")
    @ResponseBody
    public Map showAllImg(HttpServletRequest request,HttpSession session){
        HashMap hashMap = new HashMap();
        hashMap.put("current_url",request.getContextPath()+"/upload/articleImg/");
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file = new File(realPath);
        File[] files = file.listFiles();
        hashMap.put("total_count",files.length);
        ArrayList arrayList = new ArrayList();
        for (File file1 : files) {
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            String name = file1.getName();
            String extension = FilenameUtils.getExtension(name);
            fileMap.put("filetype",extension);
            fileMap.put("filename",name);
            // 通过字符串拆分获取时间戳
            String time = name.split("_")[0];
            // 创建SimpleDateFormat对象 指定yyyy-MM-dd hh:mm:ss 样式
            //  simpleDateFormat.format() 获取指定样式的字符串(yyyy-MM-dd hh:mm:ss)
            // format(参数)  参数:时间类型   new Date(long类型指定时间)long类型  现有数据:字符串类型时间戳
            // 需要将String类型 转换为Long类型 Long.valueOf(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(time)));
            fileMap.put("datetime",format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        return hashMap;
    }

    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Article article,MultipartFile inputFile,HttpServletRequest request) throws UnknownHostException {
//        System.out.println(article);
//        System.out.println(inputFile);

        HashMap hashMap = new HashMap();
        article.setId(UUID.randomUUID().toString());
        String uri = HttpUtil.getHttp(inputFile, request, "/upload/articleurl/");

        if(article.getId()==null||"".equals(article.getId())){
            article.setImg(uri);
            articleDao.insert(article);

        }else {
            if(inputFile!=null&&!"".equals(inputFile.getOriginalFilename())){
                article.setImg(uri);
                articleDao.updateByPrimaryKeySelective(article);
            }else {
                articleDao.updateByPrimaryKeySelective(article);
            }
        }
        hashMap.put("status",200);
        return  hashMap;
    }

    @RequestMapping("remove")
    @ResponseBody
    public Map remove(String articleId){
        HashMap hashMap = new HashMap();
        Article article = new Article();
        article.setId(articleId);
        articleDao.deleteByPrimaryKey(article);
        hashMap.put("status",200);
        return  hashMap;
    }


}
