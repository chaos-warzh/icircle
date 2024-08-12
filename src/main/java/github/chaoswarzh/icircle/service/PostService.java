package github.chaoswarzh.icircle.service;

import java.util.List;

import github.chaoswarzh.icircle.vo.PostVO;

public interface PostService {

    Boolean create(PostVO postVO);

    List<PostVO> getAllPosts(Integer circleId);

    PostVO getPost(Integer id);

    List<PostVO> search(String name);

}
