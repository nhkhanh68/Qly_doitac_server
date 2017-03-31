package Qly_dn.interceptor;

import Qly_dn.repository.UserRepository;
import Qly_dn.stereotype.NoAuthentication;
import Qly_dn.stereotype.RequiedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
            } else {
//                RequiedToken requiedToken = handlerMethod.getMethodAnnotation(RequiedToken.class);
//                if (requiedToken != null) {
                    if(userRepository.findByToken(token) != null){
                        return true;
                    } else{
                        throw new NullPointerException("UNAUTHORIZED");
                    }
//                }
            }

        }
        return true;
    }
}
