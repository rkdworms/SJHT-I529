package sjht.erp.hrmsList.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HrmsListController {

    //@EmployeeDto
    @ResponseBody
    @GetMapping("/test")
    public String dlfk (Authentication authentication) {
        System.out.println("컨트롤러 입니당 )))0");
        System.out.println();


        return "login/test";
    }
}
