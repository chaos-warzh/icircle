package github.chaoswarzh.icircle.util;

import github.chaoswarzh.icircle.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * get current user from session
 */
@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    public User getCurrentUser(){
        return (User)httpServletRequest.getSession().getAttribute("currentUser");
    }
}
