package sjht.erp.login.configure;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if(cookies==null){
            chain.doFilter(request, response);
        }else{
            String token =  null;

            if(Arrays.stream(cookies).filter(c -> c.getName().equals("token")).findAny().isPresent()){
                token =  Arrays.stream(cookies).filter(c -> c.getName().equals("token")).findAny().get().getValue();
            }

            token = jwtTokenProvider.resolveToken(token);

            String requestURI = ((HttpServletRequest) request).getRequestURI();

            if (token != null && jwtTokenProvider.validateToken(token, (HttpServletResponse) response)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.setAttribute("auth", authentication);
            } else {
            }

            chain.doFilter(request, response);
        }
    }
}

