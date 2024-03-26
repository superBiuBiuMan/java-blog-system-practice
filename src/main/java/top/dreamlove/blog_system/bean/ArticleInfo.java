package top.dreamlove.blog_system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 文章表
 */
@Data
@TableName("articleinfo")
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer uid;
    private Integer rcount;
    private Integer state;
}
