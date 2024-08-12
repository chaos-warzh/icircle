package github.chaoswarzh.icircle.serviceimpl;

import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.po.Comment;
import github.chaoswarzh.icircle.po.Post;
import github.chaoswarzh.icircle.repository.CommentRepository;
import github.chaoswarzh.icircle.repository.PostRepository;
import github.chaoswarzh.icircle.service.CommentService;
import github.chaoswarzh.icircle.vo.CommentVO;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Boolean create(Integer postId, CommentVO commentVO) {
        logger.info("create comment on post:{}", postId);
         // set the postId which this comment belongs to
        Comment comment = commentVO.toPO();
        comment.setTime(new Date());
        comment.setLikeNum(0);

        commentRepository.save(comment);

        // save the comment to post
        if (comment.getId() == null) {
            throw ICircleException.postNotExists();
        }
        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        if (post == null) {
            throw ICircleException.postNotExists();
        }
        post.setCommentNumber(post.getCommentNumber() + 1);
        post.getCommentIdList().add(comment.getId());
        postRepository.save(post);

        logger.info("create comment success");
        return true;
    }

    @Override
    public List<CommentVO> getCommentsByPostId(Integer postId) {
        // find the comments througth order id
        logger.info("get comments by postId:{}", postId);
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw ICircleException.postNotExists();
        }

        List<Comment> comments = post.getCommentIdList().stream()
                .map(id -> {
                    if (id != null && commentRepository.findById(id).isPresent()) {
                        return commentRepository.findById(id).get();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info("get comments success");
        return comments.stream().map(Comment::toVO).collect(Collectors.toList());
    }

    @Override
    public Boolean liked(Integer commentId, Integer userId) {
        logger.info("check if user has liked commentId:{} userId:{}", commentId, userId);

        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw ICircleException.commentNotExists();
        }

        logger.info("check success");
        return comment.getLikeUserIds().contains(userId);
    }

    @Override
    public Boolean likeComment(Integer commentId, Integer userId) {
        logger.info("like comment commentId:{} userId:{}", commentId, userId);

        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw ICircleException.commentNotExists();
        }

        // check if the user has liked the comment
        if (Boolean.TRUE.equals(liked(commentId, userId))) {
            logger.info("user has liked the comment");
            return false;
        } else {
            comment.setLikeNum(comment.getLikeNum() + 1);
            comment.getLikeUserIds().add(userId);
            commentRepository.save(comment);
            logger.info("like success");
            return true;
        }
    }

    @Override
    public Boolean cancelLikeComment(Integer commentId, Integer userId) {
        logger.info("cancel like comment commentId:{} userId:{}", commentId, userId);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            logger.info("comment not exists");
            throw ICircleException.commentNotExists();
        }

        if (Boolean.TRUE.equals(liked(commentId, userId))) {
            comment.setLikeNum(comment.getLikeNum() - 1);
            comment.getLikeUserIds().remove(userId);
            commentRepository.save(comment);
            logger.info("cancel like success");
            return true;
        } else {
            logger.info("user has not liked the comment");
            return false;
        }
    }

    @Override
    public CommentVO getComment(Integer commentId) {
        logger.info("get comment commentId:{}", commentId);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw ICircleException.commentNotExists();
        }
        logger.info("get comment success");
        return comment.toVO();
    }
}
