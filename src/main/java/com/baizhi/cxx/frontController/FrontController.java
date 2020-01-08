package com.baizhi.cxx.frontController;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.cxx.dao.*;
import com.baizhi.cxx.entity.*;
import com.baizhi.cxx.util.SmsUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/FrontController")
public class FrontController {
    @Autowired
    BannerDao bannerDao;
    @Autowired
    AlbumDao albumDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ChapterDao chapterDao;
    @Autowired
    GuruDao guruDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CounterDao counterDao;
    @Autowired
    UserGuruDao userGuruDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //1. 登陆接口
    @RequestMapping("/login")
    @ResponseBody
    public Map login(String phone,String password){

        HashMap hashMap = new HashMap();

        User user = new User();
        user.setPhone(phone);

            User user1 = userDao.selectOne(user);
            if(user1!=null){
                if(password.equals(user1.getPassword())){
                    hashMap.put("status",200);
                    hashMap.put("user",user1);
                    hashMap.put("message","success");
                }else {
                    hashMap.put("status",-200);
                    hashMap.put("message","password error");
                }
            }else {
                hashMap.put("status",-200);
                hashMap.put("message","phone error");
            }
        return  hashMap;
    }

        //2. 发送验证码
    @RequestMapping("sendCode")
    @ResponseBody
    public Map sendCode(String phone){
        String s = UUID.randomUUID().toString();
        String code = s.substring(0, 4);
        SmsUtil.send(phone,code);
        // 将验证码保存值Redis  Key code 1分钟有效
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("code",code);
        HashMap hashMap = new HashMap();
        hashMap.put("status","200");
        hashMap.put("message","短信发送成功");
        return hashMap;
    }


    //3. 注册接口
    @RequestMapping("regist")
    @ResponseBody
    public Map regist(String code){
        HashMap hashMap = new HashMap();
        try {
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            String code1 = ops.get("code");
            if(code.equals(code1)){
                hashMap.put("status","200");
                hashMap.put("message","注册成功");
            }else {
                hashMap.put("status","-200");
                hashMap.put("message","注册失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //4. 补充个人信息接口
    @RequestMapping("addUser")
    @ResponseBody
    public Map addUser(User user ){
        HashMap hashMap = new HashMap();
        try {
            user.setId(UUID.randomUUID().toString());
            userDao.insert(user);
            hashMap.put("status","200");
            hashMap.put("user",user);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {

            return hashMap;
        }
    }

    //5. 一级页面展示接口
    @RequestMapping("onePage")
    @ResponseBody
    public Map onePage(String uid,String type,String sub_type){
        HashMap hashMap = new HashMap();
        try {
            if (type.equals("all")){
                List<Banner> banners = bannerDao.queryBannersByTime();
                List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds(0, 5));
                List<Article> articles = articleDao.selectAll();
                hashMap.put("status",200);
                hashMap.put("head",banners);
                hashMap.put("albums",albums);
                hashMap.put("articles",articles);
            }else if (type.equals("wen")){
                List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds(0, 5));
                hashMap.put("status",200);
                hashMap.put("albums",albums);
            }else {
                if (sub_type.equals("ssyj")){
                    List<Article> articles = articleDao.selectAll();
                    hashMap.put("status",200);
                    hashMap.put("articles",articles);
                }else {
                    List<Article> articles = articleDao.selectAll();
                    hashMap.put("status",200);
                    hashMap.put("articles",articles);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("status","-200");
            hashMap.put("message","error");
        }

        return hashMap;
    }

    //6. 文章详情接口
    @RequestMapping("queryArticleDetile")
    @ResponseBody
    public Map queryArticleDetile(String uid,String id){
        HashMap hashMap = new HashMap();
        try {
            Article article = new Article();
            article.setId(id);
            hashMap.put("status","200");
            hashMap.put("article",article);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }


    //7. 专辑详情接口
    @RequestMapping("queryAllbumDetile")
    @ResponseBody
    public Map queryAllbumDetile(String uid,String id){
        HashMap hashMap = new HashMap();
        try {
            Album album = new Album();
            album.setId(id);
            Album album1 = albumDao.selectByPrimaryKey(album);
            Chapter chapter = new Chapter();
            chapter.setAlbumId(id);
            List<Chapter> chapterList = chapterDao.select(chapter);
            hashMap.put("status","200");
            hashMap.put("album",album1);
            hashMap.put("list",chapterList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //8. 展示功课
    @RequestMapping("queryAllCourseByUserId")
    @ResponseBody
    public Map queryAllCourseByUserId(String uid){
        HashMap hashMap = new HashMap();
        try {
            Course course = new Course();
            course.setUserId(uid);
            List<Course> courseList = courseDao.select(course);
            hashMap.put("status","200");
            hashMap.put("option",courseList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //9. 添加功课
    @RequestMapping("insertCourse")
    @ResponseBody
    public Map insertCourse(String uid,String title){
        HashMap hashMap = new HashMap();
        try {
            Course course = new Course();
            course.setUserId(uid);
            course.setId(UUID.randomUUID().toString());
            course.setTitle(title);
            course.setType("选修");
            course.setCreateDate(new Date());

            courseDao.insert(course);
            Course course2 =new Course();
            course2.setUserId(uid);
            List<Course> courseList = courseDao.select(course2);

            hashMap.put("status","200");
            hashMap.put("option",courseList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //10. 删除功课
    @RequestMapping("deleteCourseById")
    @ResponseBody
    public Map deleteCourseById(String uid,String id){
        HashMap hashMap = new HashMap();
        try {
            Course course = new Course();
            course.setUserId(uid);
            course.setId(id);
            courseDao.delete(course);
            Course course2 =new Course();
            course2.setUserId(uid);
            List<Course> courseList = courseDao.select(course2);

            hashMap.put("status","200");
            hashMap.put("option",courseList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //11. 展示计数器
    @RequestMapping("queryCounterById")
    @ResponseBody
    public Map queryCounterById(String uid,String id){
        HashMap hashMap = new HashMap();
        try {
            Counter counter = new Counter();
            counter.setUserId(uid);
            counter.setCourseId(id);
            List<Counter> counterList = counterDao.select(counter);
            hashMap.put("status","200");
            hashMap.put("counters",counterList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //12. 添加计数器
    @RequestMapping("InsertCounter")
    @ResponseBody
    public Map InsertCounter(String uid,String title,String cid){
        HashMap hashMap = new HashMap();
        try {
            Counter counter = new Counter();
            counter.setUserId(uid);
            counter.setTitle(title);
            counter.setCourseId(cid);
            counter.setId(UUID.randomUUID().toString());
            counterDao.insert(counter);
            Counter counter1 = new Counter();
            counter1.setUserId(uid);
            counter1.setCourseId(cid);
            List<Counter> counterList = counterDao.select(counter1);
            hashMap.put("status","200");
            hashMap.put("counters",counterList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }


    //13. 删除计数器
    @RequestMapping("deleteCounter")
    @ResponseBody
    public Map deleteCounter(String uid,String id){
        HashMap hashMap = new HashMap();
        try {
            Counter counter = new Counter();
            counter.setUserId(uid);
            counter.setId(id);
            Counter counter2 = counterDao.selectByPrimaryKey(counter);
            counterDao.deleteByPrimaryKey(counter);


            Counter counter1 = new Counter();
            counter1.setCourseId(counter2.getCourseId());
            List<Counter> counterList = counterDao.select(counter1);
            hashMap.put("status","200");
            hashMap.put("counters",counterList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //14. 表更计数器(修改计数器)
    @RequestMapping("updateCounter")
    @ResponseBody
    public Map updateCounter(String uid,String id,int count){
        HashMap hashMap = new HashMap();
        try {
            Counter counter = new Counter();
            counter.setId(id);
            counter = counterDao.selectByPrimaryKey(counter);
            counter.setCount(count);
            counterDao.updateByPrimaryKeySelective(counter);

            Counter counter2 = new Counter();
            counter2.setCourseId(counter.getCourseId());
            counter2.setUserId(uid);
            List<Counter> counterList = counterDao.select(counter2);
            hashMap.put("status","200");
            hashMap.put("counters",counterList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //15. 修改个人信息
    @RequestMapping("updateUser")
    @ResponseBody
    public Map updateUser(User user ){
        HashMap hashMap = new HashMap();
        try {
            userDao.updateByPrimaryKeySelective(user);
            user = userDao.selectOne(user);
            hashMap.put("status","200");
            hashMap.put("user",user);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {

            return hashMap;
        }
    }


    //16. 金刚道友
    @RequestMapping("queryFriends")
    @ResponseBody
    public Map queryFriends(String uid){
        HashMap hashMap = new HashMap();
        try {
            List<User> userList = userDao.selectAll();
            Set<User> userSet = new HashSet<User>();
            while(userSet.size()<5){
                Random random = new Random();
                int i = random.nextInt(userList.size() - 1);
                User user = userList.get(i);
                if(!user.getId().equals(uid)){
                    userSet.add(user);
                }
            }
            hashMap.put("status","200");
            hashMap.put("userSet",userSet);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    //17. 展示上师列表
    @RequestMapping("queryAllGuruByUserId")
    @ResponseBody
    public Map queryAllGuruByUserId(String uid){
        HashMap hashMap = new HashMap();
        try {

            UserGuru userGuru = new UserGuru(null, null, uid);
            List<UserGuru> userGuruList = userGuruDao.select(userGuru);
            List<Guru> guruList = new ArrayList<>();
            Guru guru = new Guru();
            for (UserGuru s : userGuruList) {
                guru.setId(s.getGuruid());
                Guru guru1 = guruDao.selectByPrimaryKey(guru);
                guruList.add(guru1);
            }
            hashMap.put("status","200");
            hashMap.put("list",guruList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }


    //18. 添加关注上师
    @RequestMapping("insertUserGuru")
    @ResponseBody
    public Map insertUserGuru(String uid,String id){
        HashMap hashMap = new HashMap();
        try {

            UserGuru userGuru = new UserGuru(UUID.randomUUID().toString(), id, uid);
            userGuruDao.insert(userGuru);
            UserGuru userGuru1 = new UserGuru(null, null, uid);
            List<UserGuru> userGuruList = userGuruDao.select(userGuru1);

            hashMap.put("status","200");
            hashMap.put("list",userGuruList);
            hashMap.put("message","success");
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","error");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }
}
