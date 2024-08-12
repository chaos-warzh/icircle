package github.chaoswarzh.icircle.service;

import github.chaoswarzh.icircle.vo.CommentVO;

import java.util.List;

public interface CommentService {

    Boolean create(Integer postId, CommentVO commentVO);

    List<CommentVO> getCommentsByPostId(Integer postId);

    Boolean liked(Integer commentId, Integer userId);

    Boolean likeComment(Integer commentId, Integer userId);

    Boolean cancelLikeComment(Integer commentId, Integer userId);

    CommentVO getComment(Integer commentId);
}
