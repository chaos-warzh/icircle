package github.chaoswarzh.icircle.controller;

import github.chaoswarzh.icircle.service.CommentService;
import github.chaoswarzh.icircle.service.PostService;
import github.chaoswarzh.icircle.vo.CommentVO;
import github.chaoswarzh.icircle.vo.PostVO;
import github.chaoswarzh.icircle.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @PostMapping
    public ResultVO<Boolean> create(@RequestBody PostVO postVO) {
        return ResultVO.buildSuccess(postService.create(postVO));
    }

    @GetMapping
    public ResultVO<List<PostVO>> getAll(@RequestParam("circleId") Integer circleId){
        return ResultVO.buildSuccess(postService.getAllPosts(circleId));
    }

    @GetMapping("/{id}")
    public ResultVO<PostVO> getById(@PathVariable(value = "id") Integer id){
        return ResultVO.buildSuccess(postService.getPost(id));
    }

    @GetMapping("/{postId}/comments")
    public ResultVO<List<CommentVO>> getComments(@PathVariable(value = "postId") Integer postId){
        return ResultVO.buildSuccess(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/search")
    public ResultVO<List<PostVO>> searchPosts(@RequestParam("name") String name) {
        return ResultVO.buildSuccess(postService.search(name));
    }

}
