package sjht.erp.workinfo.repository;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.workinfo.dto.request.HolidayRequestDto;

@Mapper
public interface HolidayRegistryMapper {
    int insertHoliday(HolidayRequestDto holidayRequestDto);
}
