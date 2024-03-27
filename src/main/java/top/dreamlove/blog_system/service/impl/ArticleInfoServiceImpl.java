package top.dreamlove.blog_system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ArticleInfo> getArticleList(String page, String pageSize) {

        return null;
    }
}
