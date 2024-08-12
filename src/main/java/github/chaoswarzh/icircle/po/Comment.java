package github.chaoswarzh.icircle.po;

import github.chaoswarzh.icircle.vo.CommentVO;
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
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "post_id")
    private Integer postId;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "time")
    private Date time;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> photoUrlList;

    @Basic
    @Column(name = "likes")
    private Integer likeNum;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Integer> likeUserIds;

    public CommentVO toVO() {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(this.id);
        commentVO.setUserName(this.userName);
        commentVO.setPostId(this.postId);
        commentVO.setContent(this.content);
        commentVO.setTime(this.time);
        commentVO.setPhotoUrlList(this.photoUrlList);
        commentVO.setLikeNum(this.likeNum);
        return commentVO;
    }

}
