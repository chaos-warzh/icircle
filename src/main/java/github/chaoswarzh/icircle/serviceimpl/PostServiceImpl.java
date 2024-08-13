package github.chaoswarzh.icircle.serviceimpl;

import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.po.Post;
import github.chaoswarzh.icircle.po.Circle;
import github.chaoswarzh.icircle.po.User;
import github.chaoswarzh.icircle.repository.PostRepository;
import github.chaoswarzh.icircle.repository.CircleRepository;
import github.chaoswarzh.icircle.repository.UserRepository;
import github.chaoswarzh.icircle.service.PostService;
import github.chaoswarzh.icircle.util.SecurityUtil;
import github.chaoswarzh.icircle.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    @Autowired
    PostRepository postRepository;

    @Autowired
    CircleRepository circleRepository;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean create(PostVO postVO) {
        logger.info("create post titled:{}", postVO.getName());

        if (postRepository.findByCircleIdAndName(postVO.getCircleId(), postVO.getName()) != null) {
            logger.info("post already exists");
            throw ICircleException.nameAlreadyExists();
        }

        Post post = postVO.toPO();
        post.setCommentNumber(0);
        postRepository.save(post);

        User user = securityUtil.getCurrentUser();
        int circleId = post.getCircleId();
        if (circleRepository.findById(circleId).isPresent()) {
            Circle circle = circleRepository.findById(circleId).get();
            circle.setCommentNumber(circle.getCommentNumber() + 1);
            if (!user.getCircleIds().contains(post.getCircleId())) {
                user.getCircleIds().add(post.getCircleId());
                userRepository.save(user);
                circle.setActiveUserNum(circle.getActiveUserNum() + 1);
                circleRepository.save(circle);
            }
        }

        logger.info("create post success");
        return true;
    }

    @Override
    public List<PostVO> getAllPosts(Integer circleId) {
        logger.info("get all posts of circle id:{}", circleId);

        Circle circle = circleRepository.findById(circleId).orElse(null);
        if (circle == null) {
            logger.info("circle not exists");
            throw ICircleException.circleNotExists();
        }

        logger.info("get all posts success");
        return postRepository.findAllByCircleId(circleId).stream().map(Post::toVO).collect(Collectors.toList());
    }

    @Override
    public PostVO getPost(Integer id) {
        logger.info("get post id:{}", id);

        Post post = postRepository.findById(id).orElse(null);
        if(post == null) {
            logger.info("post not exists");
            throw ICircleException.postNotExists();
        }

        logger.info("get post success");
        return post.toVO();
    }

    @Override
    public List<PostVO> search(String name) {
        return postRepository.searchPosts(name)
                .stream().map(Post::toVO).collect(Collectors.toList());
    }

}
