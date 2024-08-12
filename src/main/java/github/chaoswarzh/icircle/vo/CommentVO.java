package github.chaoswarzh.icircle.vo;

import github.chaoswarzh.icircle.po.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentVO {

    private Integer id;

    private String userName;

    private Integer postId;

    private String content;

    private Date time;

    private List<String> photoUrlList;

    private Integer likeNum;

    public Comment toPO() {
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setUserName(this.userName);
        comment.setPostId(this.postId);
        comment.setContent(this.content);
        comment.setTime(this.time);
        comment.setPhotoUrlList(this.photoUrlList);
        comment.setLikeNum(this.likeNum);
        comment.setLikeUserIds(new ArrayList<>());
        return comment;
    }

}
