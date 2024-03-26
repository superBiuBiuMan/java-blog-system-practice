package top.dreamlove.blog_system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("userinfo")
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private Date createTime;
    private Date updateTime;
    private Integer state;
}
