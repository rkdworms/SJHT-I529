package sjht.erp.detailexpend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.service.ExpendInsertService;
import sjht.erp.login.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class ExpendInsertApiController {
    @Autowired
    ExpendInsertService expendInsertService;

    @PostMapping("api/insertExpend")
    public boolean insertExpend(
            @RequestBody InsertRequestDto insertRequestDto,
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");

        return expendInsertService.insertExpend(insertRequestDto);
    }

    @PostMapping("api/insertFile")
    public boolean insertFile(
            @RequestPart MultipartFile multipartFile,
            HttpServletRequest request
    ) throws IOException {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        FileRequestDto fileRequestDto = new FileRequestDto();
        return expendInsertService.insertFile(fileRequestDto, employeeDto, multipartFile) != 0;
    }

    @PatchMapping("api/UpdateExpendInformation")
    public boolean updateExpendInformation(
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");
        UpdateRequestDto updateRequestDto = new UpdateRequestDto();
        return expendInsertService.updateExpendInformation(employeeDto, updateRequestDto) != 0;
    }

    @GetMapping("api/selectDetailExpend/{dvno}")
    public List<DetailResponseDto> selectAllByDVNO(
            @PathVariable(name = "dvno") String dvno,
            HttpServletRequest request
    ) {
        EmployeeDto employeeDto = (EmployeeDto) request.getAttribute("empNo");

        return expendInsertService.selectAllDetailExpend(dvno);
    }

    @DeleteMapping("api/deleteDetailExpend/{dno}")
    public boolean deleteInformation(@PathVariable(name = "dno") int dno) {
        return expendInsertService.deleteDetail(dno);
    }
}
