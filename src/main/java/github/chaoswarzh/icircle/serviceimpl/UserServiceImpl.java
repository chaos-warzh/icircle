package github.chaoswarzh.icircle.serviceimpl;

import github.chaoswarzh.icircle.enums.RoleEnum;
import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.repository.StoreRepository;
import github.chaoswarzh.icircle.repository.UserRepository;
import github.chaoswarzh.icircle.po.Store;
import github.chaoswarzh.icircle.po.User;
import github.chaoswarzh.icircle.service.UserService;
import github.chaoswarzh.icircle.util.SecurityUtil;
import github.chaoswarzh.icircle.util.TokenUtil;
import github.chaoswarzh.icircle.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Boolean register(UserVO userVO) {
        logger.info("register phone:{}",userVO.getPhone());

        User user = userRepository.findByPhone(userVO.getPhone());
        if (user != null) {
            throw ICircleException.phoneAlreadyExists();
        }

        User newUser = userVO.toPO();
        newUser.setDeliveryPhoneNumbers(new ArrayList<>());
        newUser.setDeliveryAddresses(new ArrayList<>());

        if(newUser.getRole() == RoleEnum.USER){
            newUser.getDeliveryAddresses().add(newUser.getAddress());
            newUser.getDeliveryPhoneNumbers().add(newUser.getPhone());
        }
        newUser.setCreateTime(new Date());
        userRepository.save(newUser);

        logger.info("register success");
        return true;
    }

    @Override
    public String login(String phone, String password) {
        logger.info("login phone:{}",phone);

        User user = userRepository.findByPhoneAndPassword(phone, password);
        if (user == null) {
            logger.info("login failed");
            throw ICircleException.phoneOrPasswordError();
        }

        logger.info("login success");
        return tokenUtil.getToken(user);
    }

    @Override
    public UserVO getInformation() {
        User user=securityUtil.getCurrentUser();
        logger.info("get information: userId:{}",user.getId());

        if (user.getRole().equals(RoleEnum.STAFF)) {
            return wrapWithStoreName(user.toVO());
        }

        logger.info("get success");
        return user.toVO();
    }

    @Override
    public Boolean updateInformation(UserVO userVO) {

        User user=securityUtil.getCurrentUser();
        logger.info("update information: userId:{}",user.getId());

        Optional.ofNullable(userVO.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(userVO.getName()).ifPresent(user::setName);
        Optional.ofNullable(userVO.getAddress()).ifPresent(user::setAddress);

        // address and phone number:
        if(user.getRole().equals(RoleEnum.USER)){
            Optional.ofNullable(userVO.getDeliveryAddresses()).ifPresent(user::setDeliveryAddresses);
            Optional.ofNullable(userVO.getDeliveryPhoneNumbers()).ifPresent(user::setDeliveryPhoneNumbers);
        }

        logger.info("update success");
        userRepository.save(user);
        return true;
    }

    private UserVO wrapWithStoreName(UserVO userVO){
        Integer storeId = userVO.getStoreId();
        if (storeId==null){
            return userVO;
        }
        Store store = storeRepository.findById(storeId).get();
        userVO.setStoreName(store.getName());
        return userVO;
    }
}
