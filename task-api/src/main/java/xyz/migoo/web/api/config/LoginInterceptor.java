package xyz.migoo.web.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.migoo.web.api.annotation.LoginRequired;
import xyz.migoo.web.api.service.UserService;
import xyz.migoo.web.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * @author kogome
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            try {
                // 执行认证  从 http 请求头中取出 token
                String token = request.getHeader("token");
                if (token == null) {
                    throw new RuntimeException("token == null");
                }
                // 获取 token 中的 user id
                long userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
                UserVO user = userService.selectById(userId);
                // 验证 token
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                verifier.verify(token);
                request.setAttribute("currentUser", user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.setStatus(401);
                try {
                    response.getWriter().append("{\"status\": 401, \"data\": \"invalid token\"}").flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
