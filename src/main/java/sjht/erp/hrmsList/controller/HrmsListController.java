package sjht.erp.hrmsList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HrmsListController {
    @ResponseBody
    @GetMapping("/test")
    public String dlfk (HttpServletRequest request) {
        EmployeeDto e = (EmployeeDto) request.getAttribute("empNo");



        System.out.println("컨트롤러 입니당 )))0");
        System.out.println(e);


        return "login/test";
    }


}
