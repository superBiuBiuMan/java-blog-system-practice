package top.dreamlove.blog_system.service;

import com.github.pagehelper.PageInfo;
import top.dreamlove.blog_system.bean.ArticleInfo;

import java.util.List;

public interface ArticleInfoService {
    PageInfo<ArticleInfo> getArticleList(Integer page, Integer pageSize);
}
