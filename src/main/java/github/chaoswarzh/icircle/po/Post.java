package github.chaoswarzh.icircle.po;

import github.chaoswarzh.icircle.vo.PostVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "circle_id")
    private Integer circleId;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "comment_number")
    private Integer commentNumber;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Integer> commentIdList;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> photoUrlList;

    @Basic
    @Column(name = "name")
    private String name;

    public PostVO toVO() {
        PostVO postVO = new PostVO();
        postVO.setName(this.name);
        postVO.setId(this.id);
        postVO.setCommentNumber(this.commentNumber);
        postVO.setCommentIdList(this.commentIdList);
        postVO.setContent(this.content);
        postVO.setCircleId(this.circleId);
        postVO.setPhotoUrlList(this.photoUrlList);
        return postVO;
    }
}
