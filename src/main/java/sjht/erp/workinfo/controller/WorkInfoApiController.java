package sjht.erp.workinfo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.login.dto.EmployeeDto;
import sjht.erp.workinfo.dto.request.WorkInfoRequestDto;
import sjht.erp.workinfo.dto.response.WorkInfoResponseDto;
import sjht.erp.workinfo.service.WorkTimeService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class WorkInfoApiController {

    private final WorkTimeService workTimeService;

    @PostMapping("/api/workList")
    public List<WorkInfoResponseDto> workTimeInfo(HttpServletRequest request, @RequestBody WorkInfoRequestDto workInfoRequestDto) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        // 리턴 리스폰스 디티오 타입 서비스 로직
        System.out.println(workInfoRequestDto.toString());
        return workTimeService.selectWhereWorkInfo(workInfoRequestDto);
    }

    @PostMapping("api/getMyWorkTime")
    public List<WorkInfoResponseDto> getMyWorkTime(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return workTimeService.myWorkTimeList(employeeDto.getEmpno());
    }

    @PostMapping("api/getROLE")
    public String getRole(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return employeeDto.getUsertype();
    }

    @PostMapping("api/getName")
    public String getName(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return employeeDto.getName();
    }

    @PostMapping("api/getOne")
    public WorkInfoResponseDto getOne(HttpServletRequest request, @RequestBody HashMap<String, String> map) {
        request.getAttribute("empNo");
        int tano = Integer.parseInt(map.get("tanos"));
        System.out.println(tano);
        WorkInfoResponseDto workInfoResponseDto = workTimeService.selectOne(tano);
        System.out.println(workInfoResponseDto);
        return workInfoResponseDto;
    }

    @PostMapping("api/checkMyId")
    public int checkMyEmpno(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return employeeDto.getEmpno();
    }
}
