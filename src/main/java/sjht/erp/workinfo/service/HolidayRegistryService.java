package sjht.erp.workinfo.service;

import sjht.erp.workinfo.dto.request.HolidayRequestDto;

public interface HolidayRegistryService {

    int insert(HolidayRequestDto holidayRequestDto);
}
