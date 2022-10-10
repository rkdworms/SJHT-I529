package sjht.erp.login.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sjht.erp.exception.LoginFailedException;
import sjht.erp.login.dto.LoginDto;
import sjht.erp.login.dto.Response.BaseResponse;
import sjht.erp.login.dto.Response.SingleDataResponse;
import sjht.erp.login.dto.EmployeeDto;
import sjht.erp.login.service.LoginService;
import sjht.erp.login.service.ResponseService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String main(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();

        if(cookie==null )return "login/login";

        if(Arrays.stream(cookie).filter(c -> c.getName().equals("token")).findAny().isPresent()){
            return "redirect:/menu/menu";
        }
        return "login/login";
    }

    @GetMapping("/api/join")
    public String join(){
        return "login/signup";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute EmployeeDto employeeDto){
        loginService.join(employeeDto);
        return "redirect:/";
    }

  /*  @GetMapping("/api/join")
    @ResponseBody
    public ResponseEntity join(@ModelAttribute UserDto userDto){
        ResponseEntity responseEntity = null;
        try{
            UserDto savedUser = loginService.join(userDto);
            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "회원가입 성공",savedUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (DuplicatedUsernameException exception){
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }*/

    @SneakyThrows
    @RequestMapping("/api/login")
    public String login(@ModelAttribute LoginDto loginDto, Model model, HttpServletResponse respon){
        ResponseEntity responseEntity = null;

        try{
            String token = loginService.login(loginDto);
            System.out.println(token);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authoriztion","Bearer "+token);

            SingleDataResponse<String> response = responseService.getSingleDataResponse(true,"로그인 성공", token);

            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

            Cookie cookie = new Cookie("token", URLEncoder.encode("Bearer "+token,"UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            respon.addCookie(cookie);

        }catch(LoginFailedException exception){
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return "redirect:/menu/menu";
    }

    //api 방식
/*    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity findUserByUsername(final Authentication authentication){
        ResponseEntity responseEntity = null;
        try{
            Long userId = ((UserDto)authentication.getPrincipal()).getUserId();
            UserDto findUser = loginService.findByUserId(userId);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "조회 성공", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(LoginFailedException exception){
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        System.out.println(responseEntity.toString());

        return responseEntity;
    }*/

    //thymeleaf
    @GetMapping("/users")
    public String findUserByUsername(final Authentication authentication, Model model){
        Long userId = ((EmployeeDto)authentication.getPrincipal()).getEmpno();
        EmployeeDto findUser = loginService.findByUserId(userId);


        model.addAttribute("user", findUser);

        return "login/test";
    }
}
