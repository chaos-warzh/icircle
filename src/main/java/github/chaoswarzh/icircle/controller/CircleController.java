package github.chaoswarzh.icircle.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import github.chaoswarzh.icircle.service.CircleService;
import github.chaoswarzh.icircle.vo.ResultVO;
import github.chaoswarzh.icircle.vo.CircleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/circles")
public class CircleController {
    
    @Autowired
    CircleService circleService;

    @PostMapping
    public ResultVO<Boolean> create(@RequestBody CircleVO circleVO){
        return ResultVO.buildSuccess(circleService.create(circleVO));
    }

    @GetMapping("/{id}")
    public ResultVO<CircleVO> getById(@PathVariable(value = "id") Integer id){
        return ResultVO.buildSuccess(circleService.getCircle(id));
    }

    @GetMapping("/all")
    public ResultVO<List<CircleVO>> getAll(@RequestParam(required = false, defaultValue = "rating") String key){
        return ResultVO.buildSuccess(circleService.getAllCircles(key));
    }

    @GetMapping("/{id}/score")
    public ResultVO<Double> getScore(@PathVariable(value = "id") Integer id){
        return ResultVO.buildSuccess(circleService.getScore(id));
    }

    @GetMapping( "/{id}/commentCount")
    public ResultVO<Integer> getCommentCount(@PathVariable(value = "id") Integer id){
        return ResultVO.buildSuccess(circleService.getCommentCount(id));
    }

    @GetMapping("/{id}/activeUserCount")
    public ResultVO<Integer> getActiveUserCount(@PathVariable(value = "id") Integer id) {
        return ResultVO.buildSuccess(circleService.getActiveUserCount(id));
    }

}
