package sjht.erp.expendHistory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sjht.erp.expendInformation.service.ExpendInformationService;

@Controller
@RequiredArgsConstructor
public class ExpendHistoryController {

    @RequestMapping("/expendHistory")
    public String expendHistory(){
            System.out.println("Controller expendHistory");
        return "expendHistory/expendHistory";
    }


}
