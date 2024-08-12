package github.chaoswarzh.icircle.controller;

import github.chaoswarzh.icircle.service.CommentService;
import github.chaoswarzh.icircle.vo.CommentVO;
import github.chaoswarzh.icircle.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}/comment")
    public ResultVO<Boolean> comment(@PathVariable("postId") Integer postId, @RequestBody CommentVO commentVO) {
        return ResultVO.buildSuccess(commentService.create(postId, commentVO));
    }

    @GetMapping("/checkhasliked")
    public ResultVO<Boolean> liked(@RequestParam("commentId") Integer commentId, @RequestParam("userId") Integer userId){
        return ResultVO.buildSuccess(commentService.liked(commentId, userId));
    }

    @PostMapping("/like")
    public ResultVO<Boolean> likeComment(@RequestParam("commentId") Integer commentId, @RequestParam("userId") Integer userId){
        return ResultVO.buildSuccess(commentService.likeComment(commentId, userId));
    }

    @PostMapping("/cancellike")
    public ResultVO<Boolean> cancelLikeComment(@RequestParam("commentId") Integer commentId, @RequestParam("userId") Integer userId){
        return ResultVO.buildSuccess(commentService.cancelLikeComment(commentId, userId));
    }

    @GetMapping("/get")
    public ResultVO<CommentVO> getComment(@RequestParam("commentId") Integer commentId){
        return ResultVO.buildSuccess(commentService.getComment(commentId));
    }
}
