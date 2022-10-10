package sjht.erp.hrms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sjht.erp.common.vo.EmployeeVO;
import sjht.erp.hrms.dto.EmployeeDto;
import sjht.erp.hrms.service.HrmsApiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HrmsApiController {

    private final HrmsApiService hrmsApiService;
    /* 사원 리스트 */
    @GetMapping("/hrms")
    @ResponseBody
    public ResponseEntity<List<EmployeeVO>> getEmplist () {
        // 사원 리스트 불러오기 및 반환
        return ResponseEntity.status(HttpStatus.OK).body(hrmsApiService.getEmplist());
    }

    @GetMapping("/hrms/list")
    public String getEmplists () {
        // 사원 리스트 불러오기 및 반환
        return "/hrms/list";
    }

    /* 사원 상세 조회 */
    @GetMapping("/hrms/list/{empno}")
    @ResponseBody
    public ResponseEntity<EmployeeVO> findEmpByEmpno (@PathVariable Long empno) {
        // 사원 상세 불러오기 및 반환
        return ResponseEntity.status(HttpStatus.OK).body(hrmsApiService.findEmpByEmpno(empno));
    }

    @GetMapping("/hrms/test/{empno}")
    public String findEmpByEmpno1 () {
        // 사원 리스트 불러오기 및 반환
        return "/hrms/hrms";
    }
}
