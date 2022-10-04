package sjht.erp.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/connectTest")
    public String text(){
        return "뷰 연동 테스트";
    }
}
