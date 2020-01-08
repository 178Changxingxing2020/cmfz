package com.baizhi.cxx.controller;

import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.Dto.AlbumDto;
import com.baizhi.cxx.service.AlbumService;
import com.baizhi.cxx.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.*;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public AlbumDto queryByPage(Integer rows, Integer page){
        AlbumDto dto = albumService.queryByPage(rows, page);
        return  dto;
    }


    @RequestMapping("modify")
    @ResponseBody
    public Map modify(Album album, String oper,String[] id){
    HashMap hashMap = new HashMap();
    if("add".equals(oper)){
            String albumId = UUID.randomUUID().toString();
            album.setId(albumId);
            albumService.insert(album);
            hashMap.put("albumId",albumId);
        //System.out.println(bannerId);
        } else if ("del".equals(oper)){
            albumService.remove(Arrays.asList(id));
            hashMap.put("albumId",album.getId());
        } else {
            if(album.getUrl().equals("")){
                album.setUrl(null);
            }
            hashMap.put("albumId",album.getId());
            albumService.modifly(album);

        }
        return  hashMap;
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile url, String albumId, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        HashMap hashMap = new HashMap();
        String uri = HttpUtil.getHttp(url, request, "/upload/Albumimg/");

        Album album = new Album();
        album.setId(albumId);
        album.setUrl(uri);
        albumService.modifly(album);

        hashMap.put("status",200);
        return hashMap;
    }


}
