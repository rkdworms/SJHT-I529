package sjht.erp.workinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.login.dto.EmployeeDto;
import sjht.erp.workinfo.dto.request.HolidayRequestDto;
import sjht.erp.workinfo.service.HolidayRegistryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HolidayRegistryApiController {

    @Autowired
    HolidayRegistryService holidayRegistryService;

    @GetMapping("/api/workTimeInsert")
    public int insert(HttpServletRequest request,
                      @RequestBody HolidayRequestDto holidayRequestDto) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");

        holidayRequestDto.setEmpno(Math.toIntExact(employeeDto.getEmpno()));
        return holidayRegistryService.insert(holidayRequestDto);
    }
}
