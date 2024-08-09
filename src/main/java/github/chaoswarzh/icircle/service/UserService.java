package github.chaoswarzh.icircle.service;

import github.chaoswarzh.icircle.vo.UserVO;

public interface UserService {

    Boolean register(UserVO userVO);

    String login(String phone,String password);

    UserVO getInformation();

    Boolean updateInformation(UserVO userVO);
}
