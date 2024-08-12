package github.chaoswarzh.icircle.controller;

import github.chaoswarzh.icircle.po.Circle;
import github.chaoswarzh.icircle.service.CircleService;
import github.chaoswarzh.icircle.service.UserService;
import github.chaoswarzh.icircle.vo.CircleVO;
import github.chaoswarzh.icircle.vo.ResultVO;
import github.chaoswarzh.icircle.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CircleService circleService;

    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody UserVO userVO) {
        return ResultVO.buildSuccess(userService.register(userVO));
    }

    @PostMapping("/login")
    public ResultVO<String> login(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password
    ) {
        return ResultVO.buildSuccess(userService.login(phone, password));
    }

    @GetMapping
    public ResultVO<UserVO> getInformation() {
        return ResultVO.buildSuccess(userService.getInformation());
    }

    @PostMapping
    public ResultVO<Boolean> updateInformation(@RequestBody UserVO userVO) {
        return ResultVO.buildSuccess(userService.updateInformation(userVO));
    }

    @GetMapping("/circles")
    public ResultVO<List<CircleVO>> getActiveCircles() {
        return ResultVO.buildSuccess(circleService.getUserActiveCircles());
    }
}
