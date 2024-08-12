package github.chaoswarzh.icircle.service;

import java.util.List;

import github.chaoswarzh.icircle.vo.CircleVO;

public interface CircleService {

    Boolean create(CircleVO circleVO);

    CircleVO getCircle(Integer id);

    List<CircleVO> getAllCircles(String key);

    Double getScore(Integer id);

    Integer getCommentCount(Integer id);

    Integer getActiveUserCount(Integer id);

    List<CircleVO> getUserActiveCircles();
}
