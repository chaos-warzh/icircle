package github.chaoswarzh.icircle.serviceimpl;

import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.po.Circle;
import github.chaoswarzh.icircle.repository.CircleRepository;
import github.chaoswarzh.icircle.service.CircleService;
import github.chaoswarzh.icircle.vo.CircleVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CircleServiceImpl implements CircleService {

    private static final Logger logger = LogManager.getLogger(CircleServiceImpl.class);

    @Autowired
    CircleRepository circleRepository;

    @Override
    public Boolean create(CircleVO circleVO) {
        logger.info("create circle name:{}", circleVO.getName());
        Circle circle = circleRepository.findByName(circleVO.getName());
        if (circle != null) {
            logger.info("circle name already exists");
            throw ICircleException.nameAlreadyExists();
        }

        Circle newCircle = circleVO.toPO();
        newCircle.setRating(0.0);
        newCircle.setActiveUserNum(0);
        newCircle.setCommentNumber(0);
        circleRepository.save(newCircle);

        logger.info("create circle success");
        return true;
    }

    @Override
    public CircleVO getCircle(Integer id) {
        logger.info("get circle id:{}", id);

        Circle circle = circleRepository.findById(id).orElse(null);
        if (circle == null) {
            logger.info("circle not exists");
            throw ICircleException.circleNotExists();
        }

        logger.info("get circle success");
        return circle.toVO();
    }

    @Override
    public List<CircleVO> getAllCircles(String key) {
        logger.info("get all circles sorted by {}", key);
        //sorted by key
        Sort sort;
        switch (key) {
            case "rating":
                sort = Sort.by(Sort.Direction.DESC, "rating");
                break;
            case "commentNumber":
                sort = Sort.by(Sort.Direction.DESC, "commentNumber");
                break;
            default:
                // throw exception
                logger.info("illegal sorted key");
                throw ICircleException.illegalSortedKey();
        }

        List<Circle> circles = circleRepository.findAll(sort);

        logger.info("get all stores success");
        return circles.stream().map(Circle::toVO).collect(Collectors.toList());
    }

    @Override
    public Double getScore(Integer id) {
        logger.info("get store score id:{}", id);

        Circle circle = circleRepository.findById(id).orElse(null);
        if (circle == null) {
            logger.info("Circle not exists");
            throw ICircleException.circleNotExists();
        }

        logger.info("get circle score success");
        return circle.getRating();
    }

    @Override
    public Integer getCommentCount(Integer id) {
        logger.info("get circle comment count id:{}", id);

        Circle circle = circleRepository.findById(id).orElse(null);
        if (circle == null) {
            logger.info("circle not exists");
            throw ICircleException.circleNotExists();
        }

        logger.info("get circle comment count success");
        return circle.getCommentNumber();
    }
}
