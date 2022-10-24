package sjht.erp.detailexpend.service;

import org.springframework.web.multipart.MultipartFile;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.login.dto.EmployeeDto;

import java.io.IOException;
import java.util.List;

public interface ExpendInsertService {

    boolean insertExpend(InsertRequestDto insertRequestDto);

    int insertFile(FileRequestDto fileRequestDto, EmployeeDto employeeDto, MultipartFile multipartFile) throws IOException;

    int updateExpendInformation(EmployeeDto employeeDto, UpdateRequestDto updateRequestDto);

    List<DetailResponseDto> selectAllDetailExpend(String dvno);

    boolean deleteDetail(int dno);

}
