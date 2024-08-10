package github.chaoswarzh.icircle.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import github.chaoswarzh.icircle.vo.CircleVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Circle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "logo_url")
    private String logoUrl;

    @Basic
    @Column(name = "rating")
    private Double rating;

    @Basic
    @Column(name = "active_user_num")
    private Integer activeUserNum;

    @Basic
    @Column(name = "comment_number")
    private Integer commentNumber;

    public CircleVO toVO(){
        CircleVO circleVO =new CircleVO();
        circleVO.setId(this.id);
        circleVO.setLogoUrl(this.logoUrl);
        circleVO.setName(this.name);
        circleVO.setActiveUserNum(this.activeUserNum);
        circleVO.setRating(this.rating);
        return circleVO;
    }
}
