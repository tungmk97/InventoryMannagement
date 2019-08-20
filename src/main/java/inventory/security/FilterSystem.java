package inventory.security;

import inventory.model.Auth;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.ultil.Constant;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class FilterSystem implements HandlerInterceptor {
    Logger log = Logger.getLogger(FilterSystem.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Pre handle " + request.getRequestURI());

        Users users = (Users) request.getSession().getAttribute(Constant.USER_INFO);
        if(users == null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        else {
            String url = request.getServletPath();
            if(!hasPermission(url, users)){
                response.sendRedirect(request.getContextPath()+"/access-denied");
                return false;
            }
        }

        return true;
    }

    public boolean hasPermission(String url,Users users) {
        if(url.contains("/index") || url.contains("/access-denied") || url.contains("/logout")) {
            return true;
        }

        UserRole userRole = (UserRole) users.getUserRoles().iterator().next();
        Set<Auth> auths = userRole.getRole().getAuths();
        for(Object obj:auths) {
            Auth auth = (Auth) obj;
            if(url.contains(auth.getMenu().getUrl())){
                return auth.getPermission() == 1;
            }
        }

        return false;
    }
}
