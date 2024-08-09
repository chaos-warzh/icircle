package github.chaoswarzh.icircle.vo;

import github.chaoswarzh.icircle.enums.RoleEnum;
import github.chaoswarzh.icircle.po.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String name;

    private String phone;

    private String password;

    private List<Integer> circleIds;

    private RoleEnum role;

    private Date createTime;

    public User toPO() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setPhone(this.phone);
        user.setPassword(this.password);
        user.setCircleIds(this.circleIds);
        user.setRole(this.role);
        user.setCreateTime(this.createTime);
        return user;
    }
}
