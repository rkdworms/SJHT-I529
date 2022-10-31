package sjht.erp.workinfo.service;

import sjht.erp.workinfo.dto.request.HolidayRequestDto;
import sjht.erp.workinfo.dto.request.UpdateHolidayRequestDto;

public interface HolidayRegistryService {

    boolean insert(HolidayRequestDto holidayRequestDto);

    boolean updateHoliday(UpdateHolidayRequestDto updateHolidayRequestDto);
}
