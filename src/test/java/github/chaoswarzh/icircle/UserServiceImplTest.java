package github.chaoswarzh.icircle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import github.chaoswarzh.icircle.enums.RoleEnum;
import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.repository.UserRepository;
import github.chaoswarzh.icircle.po.User;
import github.chaoswarzh.icircle.serviceimpl.UserServiceImpl;
import github.chaoswarzh.icircle.util.SecurityUtil;
import github.chaoswarzh.icircle.util.TokenUtil;
import github.chaoswarzh.icircle.vo.UserVO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenUtil tokenUtil;

    @Mock
    private SecurityUtil securityUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegister() {
        // 假设数据
        UserVO userVO = new UserVO();
        userVO.setPhone("1234567890");
        userVO.setRole(RoleEnum.USER);

        // 插桩，当根据电话号码查询用户时返回null，模拟用户不存在
        when(userRepository.findByPhone(anyString())).thenReturn(null);

        // 调用方法
        Boolean result = userService.register(userVO);

        // 验证返回结果和插桩行为
        assertTrue(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegister_UserAlreadyExists() {
        // 假设数据
        UserVO userVO = new UserVO();
        userVO.setPhone("1234567890");

        // 插桩，当根据电话号码查询用户时返回一个User实例，模拟用户已存在
        when(userRepository.findByPhone(anyString())).thenReturn(new User());

        // 验证抛出异常
        assertThrows(ICircleException.class, () -> {
            userService.register(userVO);
        });
    }

    @Test
    void testLogin_Success() {
        String phone = "1234567890";
        String password = "password";
        User mockUser = new User();
        mockUser.setId(1);

        // 插桩，当根据电话号码和密码查询用户时返回一个User实例
        when(userRepository.findByPhoneAndPassword(phone, password)).thenReturn(mockUser);
        when(tokenUtil.getToken(any(User.class))).thenReturn("mockToken");

        // 调用方法
        String token = userService.login(phone, password);

        // 验证结果和插桩行为
        assertEquals("mockToken", token);
    }

    @Test
    void testLogin_Failure() {
        String phone = "wrongPhone";
        String password = "wrongPassword";

        // 插桩，当根据电话号码和密码查询用户时返回null
        when(userRepository.findByPhoneAndPassword(phone, password)).thenReturn(null);

        // 验证抛出异常
        assertThrows(ICircleException.class, () -> {
            userService.login(phone, password);
        });
    }
}
