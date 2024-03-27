package top.dreamlove.blog_system.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.dreamlove.blog_system.bean.ArticleInfo;
import top.dreamlove.blog_system.mapper.ArticleInfoMapper;
import top.dreamlove.blog_system.service.ArticleInfoService;

import java.util.List;

@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {
    @Autowired
    ArticleInfoMapper articleInfoMapper;

    @Override
    public PageInfo<ArticleInfo> getArticleList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);//分页
        List<ArticleInfo> articleInfos = articleInfoMapper.articleList();
        PageInfo<ArticleInfo> articleInfoPageInfo = new PageInfo<>(articleInfos);



        return articleInfoPageInfo;
    }
}
