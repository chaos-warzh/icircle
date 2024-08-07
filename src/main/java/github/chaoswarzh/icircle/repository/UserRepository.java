package github.chaoswarzh.icircle.repository;

import github.chaoswarzh.icircle.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPhone(String phone);

    User findByPhoneAndPassword(String phone, String password);
}
