package github.chaoswarzh.icircle.repository;

import github.chaoswarzh.icircle.po.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) ")
    List<Post> searchPosts(@Param("name") String name);

    List<Post> findAllByCircleId(Integer circleId);

    Post findByCircleIdAndName(Integer circleId, String name);

}
