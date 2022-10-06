package sjht.erp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import sjht.erp.login.configure.JwtTokenProvider;
import sjht.erp.login.dto.EmployeeDto;
import sjht.erp.login.service.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class Interceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public EmployeeDto StaySignedIn(Authentication authentication){
        Long userId = ((EmployeeDto) authentication.getPrincipal()).getEmpno();
        EmployeeDto findUser = loginService.findByUserId(userId);

        System.out.println(" ******** ****************       **************       *************** * ");
        System.out.println(userId);
        System.out.println(findUser);
        System.out.println(findUser.getName());
        return findUser;
    }
    //오토 와이어드 jwt필터해서 프리핸드에 메소드를 가져다 쓰는거는???????????????????
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        String token =  null;
        if(cookies!=null){
            if(Arrays.stream(cookies).filter(c -> c.getName().equals("token")).findAny().isPresent()){
                token =  Arrays.stream(cookies).filter(c -> c.getName().equals("token")).findAny().get().getValue();
            }
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Long userId = ((EmployeeDto) authentication.getPrincipal()).getEmpno();
        EmployeeDto findUser = loginService.findByUserId(userId);



        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
