package github.chaoswarzh.icircle.po;

import github.chaoswarzh.icircle.enums.RoleEnum;
import github.chaoswarzh.icircle.vo.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "password")
    private String password;


    @ElementCollection(fetch = FetchType.LAZY)
    private List<Integer> circleIds;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Basic
    @Column(name = "create_time")
    private Date createTime;

    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setId(this.id);
        userVO.setName(this.name);
        userVO.setPhone(this.phone);
        userVO.setPassword(this.password);
        userVO.setCircleIds(this.circleIds);
        userVO.setRole(this.role);
        userVO.setCreateTime(this.createTime);
        return userVO;
    }
}
