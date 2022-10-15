package sjht.erp.hrms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sjht.erp.hrms.dto.HrmsDto;
import sjht.erp.hrms.dto.EmpInfoDto;
import sjht.erp.hrms.service.HrmsApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HrmsApiController {

    private final HrmsApiService hrmsApiService;

    /* 사원 리스트 */
    @PostMapping("/api/hrms")
    public ResponseEntity<List<HrmsDto>> getEmpList () {
        // 사원 리스트 불러오기
        List<HrmsDto> empList = hrmsApiService.getEmpList();
        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(empList);
    }

    /* 사원 등록 */
    @PostMapping("/api/hrms/regist")
    public void registEmp (@RequestBody EmpInfoDto empInfoDto) {
        // 폼 데이터 등록 요청
        hrmsApiService.registEmp(empInfoDto);
    }

    /* 사원 정보 수정 */
    @PatchMapping("/api/hrms/{empno}")
    public void updateEmp (@RequestBody EmpInfoDto empInfoDto) {
        // 폼 데이터 수정 요청
        hrmsApiService.updateEmp(empInfoDto);
    }







}
