package sjht.erp.detailexpend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateMyDetailExpendRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.dto.response.MyExpendListResponseDto;
import sjht.erp.detailexpend.service.ExpendInsertService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class ExpendInsertApiController {
    @Autowired
    ExpendInsertService expendInsertService;


    @PostMapping("api/insertDVNO")
    public String inputEXPENDDVNO(
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.inputExpend();
    }

    @PostMapping("api/insertDetail")
    public List<DetailResponseDto> inputDETAIL(
            HttpServletRequest request,
            @RequestBody InsertRequestDto insertRequestDto
//            @RequestPart MultipartFile multipartFile
    ) {
        request.getAttribute("empNo");
        System.out.println(insertRequestDto.toString());
        List<DetailResponseDto> detailResponseDtoList = null;
        System.out.println(insertRequestDto.toString());
        // 파일입력부분이 들어가야 된다.
        if (expendInsertService.inputDetail(insertRequestDto/*, multipartFile*/) != -1) {
            String dvno = insertRequestDto.getDvno();
            detailResponseDtoList = expendInsertService.selectDetailExpend(dvno);
        }
        return detailResponseDtoList;
    }

    @PostMapping("api/updateExpendInformation")
    public boolean updateExpendInfo(HttpServletRequest request, @RequestBody HashMap<String, String> map) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        String dvno = map.get("dvnoOne");
        boolean isUpdate = expendInsertService.updateExpend(employeeDto.getEmpno(), dvno);
        return isUpdate;
    }

    @PostMapping("api/deleteData")
    public boolean deleteExpendData(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map

    ) {
        String dvno = map.get("dvnoOne");
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.deleteData(dvno);
    }

    @PostMapping("api/deleteDetailData")
    public boolean deleteExpendDetailData(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        String dvno = map.get("dvnoOne");
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.deleteDetailData(dvno);
    }

    @PostMapping("api/deleteDetailDataOne")
    public boolean deleteExpendDetailDataOne(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int dno = Integer.parseInt(map.get("dnum"));
        return expendInsertService.deleteOneData(dno);
    }

    @PostMapping("api/getDepartment")
    public String getDepartmentName(HttpServletRequest request) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.getDepartment(employeeDto.getEmpno());
    }

    @PostMapping("api/showMyExpendList")
    public List<MyExpendListResponseDto> getMyExpendList(
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");

        return expendInsertService.selectExpendInfo(employeeDto.getEmpno());
    }

    @PostMapping("api/showMyDetailExpendList")
    public List<DetailResponseDto> showListDetail(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        String dvno = map.get("dvnoOne");
        System.out.println(dvno);
        return expendInsertService.selectDetailExpend(dvno);
    }

    @PostMapping("api/chooseMyDetailExpend")
    public DetailResponseDto update(
            HttpServletRequest request,
            @RequestBody HashMap<String, String> map
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        int dno = Integer.parseInt(map.get("dno"));//그리스 물음표
        return expendInsertService.chooseDetailExpend(dno);
    }

    @PostMapping("api/updateMyDetailExpend")
    public boolean updateMyDetailExpend(
            HttpServletRequest request,
            @RequestBody UpdateMyDetailExpendRequestDto updateMyDetailExpendRequestDto
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        return expendInsertService.updateMyDetailExpend(updateMyDetailExpendRequestDto);
    }
}
