package sjht.erp.expendInformation.repository;

import sjht.erp.expendInformation.dto.ParameterEIDto;
import sjht.erp.expendInformation.dto.ResultEIDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpendInformationMapper {

    //지출결의 조건 검색
    List<ResultEIDto> selectExpendInformation(ParameterEIDto parameterEIDto);

    //지출결의 dvno에 따른 데이터 상세내역 검색
    List<ResultEIDto> selectExpendInformationOne(String dvno);

    Long updateExpendInformation();

}
