package sjht.erp.detailexpend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpendInsertController {

    @GetMapping("insertExpend")
    public String insertExpend(){
        return "";
    }
}
