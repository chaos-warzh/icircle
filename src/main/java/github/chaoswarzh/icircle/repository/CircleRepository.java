package github.chaoswarzh.icircle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import github.chaoswarzh.icircle.po.Circle;

public interface CircleRepository extends JpaRepository<Circle, Integer> {

    Circle findByName(String name);

}
