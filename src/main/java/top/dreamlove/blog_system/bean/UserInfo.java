package top.dreamlove.blog_system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("userinfo")
public class UserInfo {
    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
    private Integer id;
    @NotEmpty(message = "数据不能为空")
    private String username;
    @NotEmpty(message = "数据不能为空")
    private String password;
    private String photo;
    private Date createTime;
    private Date updateTime;
    private Integer state;
}
