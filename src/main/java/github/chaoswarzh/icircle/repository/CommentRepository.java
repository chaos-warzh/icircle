package github.chaoswarzh.icircle.repository;

import github.chaoswarzh.icircle.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
