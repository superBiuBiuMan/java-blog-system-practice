package top.dreamlove.blog_system.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dreamlove.blog_system.bean.ArticleInfo;
import top.dreamlove.blog_system.service.ArticleInfoService;
import top.dreamlove.blog_system.utils.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")//跨域设置
public class ArticleInfoController {
    @Autowired
    ArticleInfoService articleInfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/article/list")
    public Result list(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        PageInfo<ArticleInfo> info = articleInfoService.getArticleList(page, pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("total",info.getTotal());
        map.put("page",info.getPageNum());
        map.put("pageSize",info.getPageSize());
        map.put("pages",info.getPages());//总页数
        map.put("list",info.getList());//数据列表



        return Result.ok().data(map);
    }
}
