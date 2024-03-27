package top.dreamlove.blog_system.mapper;

import org.apache.ibatis.annotations.Param;
import top.dreamlove.blog_system.bean.ArticleInfo;

import java.util.List;

public interface ArticleInfoMapper {
    //分页查询文章
    List<ArticleInfo> articleList();
}
