package Qly_dn.interceptor;

import Qly_dn.model.Role;
import Qly_dn.model.User;
import Qly_dn.repository.UserRepository;
import Qly_dn.stereotype.NoAuthentication;
import Qly_dn.stereotype.RequiredRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nhkha on 25/03/2017.
 */
@Component
public class TokenAuthenticationInterceptor extends HandlerInterceptorAdapter {
    private final
    UserRepository userRepository;
    @Autowired
    public TokenAuthenticationInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("auth-token");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getMethodAnnotation(NoAuthentication.class) != null) {
                // No required authentication
                return true; // by  ss
            } else if (token == null) {
                throw new NullPointerException("UNAUTHORIZED");
            }
//            if (userService.isUser(token)){
//                 role = "login";
//            }

            // 1. Find user by token
            // If user doesn't exist, mean token is invalid
            // Else check the role for user is match with method in controller
            User user = userRepository.findByToken(token);
            Role role = user.getRole();
            RequiredRoles requiredRoles = handlerMethod.getMethodAnnotation(RequiredRoles.class);
            if (requiredRoles != null) {
                for (Role requiredRole : requiredRoles.value()) {
                    if (requiredRole.equals(role)) {
                        return true;
                    }
                }
                throw new NullPointerException("Bạn không đủ quyền để thực hiện hành động này!");
            }
        }
        return true;
    }
}
