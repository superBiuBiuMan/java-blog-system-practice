package top.dreamlove.blog_system.service;

import top.dreamlove.blog_system.bean.ArticleInfo;

import java.util.List;

public interface ArticleInfoService {
    List<ArticleInfo> getArticleList(String page,String pageSize);
}
