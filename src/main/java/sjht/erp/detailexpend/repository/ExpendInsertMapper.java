package sjht.erp.detailexpend.repository;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.detailexpend.dto.request.FileRequestDto;
import sjht.erp.detailexpend.dto.request.InsertRequestDto;
import sjht.erp.detailexpend.dto.request.SelectEmployeeDto;
import sjht.erp.detailexpend.dto.request.UpdateRequestDto;
import sjht.erp.detailexpend.dto.response.DetailResponseDto;

import java.util.List;

@Mapper
public interface ExpendInsertMapper {
    SelectEmployeeDto selectOneEmployee(int empno);

    String selectLastOne();

    List<String> selectAll();

    int insertDvno(String dvno);

    int insertExpend(InsertRequestDto insertRequestDto);

    int insertFile(FileRequestDto fileRequestDto);

    int updateExpendInformation(UpdateRequestDto updateRequestDto);

    List<DetailResponseDto> selectDetailExpend(String dvno);

    int deleteDetailExpend(int dno);
}
