package sjht.erp.detailexpend.service;

import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;

import java.util.List;

public interface ExpendInsertService {
    String inputExpend();
    int inputDetail(InsertRequestDto insertRequestDto/*,MultipartFile multipartFile*/);
    List<DetailResponseDto> selectDetailExpend(String dvno);
    boolean updateExpend(int empno,String dvno);
    boolean deleteDetailData(String dvno);
    boolean deleteData(String dvno);
    String getDepartment(int empno);
}
