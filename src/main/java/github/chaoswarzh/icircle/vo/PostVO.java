package github.chaoswarzh.icircle.vo;

import github.chaoswarzh.icircle.po.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostVO {

    private Integer id;

    private Integer circleId;

    private String content;

    private Integer commentNumber;

    private List<Integer> commentIdList;

    private List<String> photoUrlList;

    private String name;

    public Post toPO() {
        Post post = new Post();
        post.setId(this.id);
        post.setName(this.name);
        post.setCommentNumber(this.commentNumber);
        post.setCommentIdList(this.commentIdList);
        post.setContent(this.content);
        post.setCircleId(this.circleId);
        post.setPhotoUrlList(this.photoUrlList);
        return post;
    }
}
