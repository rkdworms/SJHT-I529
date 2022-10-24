package sjht.erp.workinfo.service;

import sjht.erp.workinfo.dto.request.HolidayRequestDto;

public interface HolidayRegistryService {

    boolean insert(HolidayRequestDto holidayRequestDto);
}
