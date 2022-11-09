package sjht.erp.hrms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sjht.erp.hrms.service.HrmsService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HrmsController {

    private final HrmsService hrmsService;

    @GetMapping("/hrms/list")
    public String getEmplists (HttpServletRequest request, Model model) {
        // 로그인 된 유저 정보 불러오기
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        // 로그인 된 유저 정보 넘겨주기
        model.addAttribute("loginName", employeeDto.getName());
        model.addAttribute("loginUserEmpno", employeeDto.getEmpno());
        // 사원 리스트 불러오기 및 반환
        return "/hrms/list";
    }
}


