package com.baizhi.cxx.controller;

import com.baizhi.cxx.entity.Chapter;
import com.baizhi.cxx.Dto.ChapterDto;
import com.baizhi.cxx.service.ChapterService;
import com.baizhi.cxx.util.HttpUtil;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public ChapterDto queryByPage(Integer rows, Integer page,String albumId){
        ChapterDto dto = chapterService.queryByPage(rows, page,albumId);
        return  dto;
    }

    @RequestMapping("modify")
    @ResponseBody
    public Map modify(Chapter chapter, String oper, String[] id,String albumId){
    HashMap hashMap = new HashMap();
    if("add".equals(oper)){
            String chapterId = UUID.randomUUID().toString();
            chapter.setId(chapterId);
            chapter.setAlbumId(albumId);
            chapterService.insert(chapter);
            hashMap.put("chapterId",chapterId);
        //System.out.println(bannerId);
        } else if ("del".equals(oper)){
            chapterService.remove(Arrays.asList(id));
            hashMap.put("chapterId",chapter.getId());
        } else {
            if(chapter.getUrl().equals("")){
                chapter.setUrl(null);
            }
            hashMap.put("chapterId",chapter.getId());
            chapterService.modifly(chapter);

        }
        return  hashMap;
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map upload(MultipartFile url, String chapterId,
                      HttpSession session, HttpServletRequest request) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        HashMap hashMap = new HashMap();
        String uri = HttpUtil.getHttp(url, request, "/upload/chapterurl/");

        Chapter chapter = new Chapter();
        chapter.setId(chapterId);
        chapter.setUrl(uri);
        //计算文件大小
        Double size=Double.valueOf(url.getSize()/1024/1024);
        chapter.setSize(size);
        //计算音频时长
        String[] split = uri.split("/");
        //获取文件名
        String name = split[split.length - 1];
        String realPath = session.getServletContext().getRealPath("/upload/chapterurl/");
        //获取音频对象
        AudioFile read = AudioFileIO.read(new File(realPath, name));
        //获取头部信息，并转换
        MP3AudioHeader audioHeader = (MP3AudioHeader) read.getAudioHeader();
        //获取音频时长
        int trackLength = audioHeader.getTrackLength();
        String time=trackLength/60+"分"+trackLength%60+"秒";
        chapter.setTime(time);

        chapterService.modifly(chapter);

        hashMap.put("status",200);
        return hashMap;
    }


    @RequestMapping("download")
    @ResponseBody
    public void download(String url, HttpServletResponse response,HttpSession session) throws IOException {
        String[] split = url.split("/");
        String name = split[split.length - 1];

        String realPath = session.getServletContext().getRealPath("/upload/chapterurl/");
        File file = new File(realPath, name);
        response.setHeader("Content-Disposition", "attachment; filename="+name);
        ServletOutputStream outputStream = response.getOutputStream();
        FileUtils.copyFile(file,outputStream);

    }
}
