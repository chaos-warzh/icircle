package github.chaoswarzh.icircle.vo;

import github.chaoswarzh.icircle.po.Circle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CircleVO {

    private Integer id;
    
    private String name;

    private String logoUrl;

    private Double rating;

    private Integer activeUserNum;

    public Circle toPO() {
        Circle circle =new Circle();
        circle.setId(this.id);
        circle.setLogoUrl(this.logoUrl);
        circle.setName(this.name);
        circle.setActiveUserNum(this.activeUserNum);
        circle.setRating(this.rating);
        return circle;
    }
}
