package sjht.erp.detailexpend.service;

import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.UpdateMyDetailExpendRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;
import sjht.erp.detailexpend.dto.response.MyExpendListResponseDto;

import java.util.List;

public interface ExpendInsertService {
    String inputExpend();
    int inputDetail(InsertRequestDto insertRequestDto/*,MultipartFile multipartFile*/);
    List<DetailResponseDto> selectDetailExpend(String dvno);
    boolean updateExpend(int empno,String dvno);
    boolean deleteDetailData(String dvno);
    boolean deleteData(String dvno);
    String getDepartment(int empno);
    List<MyExpendListResponseDto> selectExpendInfo(int empno);
    boolean deleteOneData(int dno);
    DetailResponseDto chooseDetailExpend(int dno);
    boolean updateMyDetailExpend(UpdateMyDetailExpendRequestDto updateMyDetailExpendRequestDto);

}
