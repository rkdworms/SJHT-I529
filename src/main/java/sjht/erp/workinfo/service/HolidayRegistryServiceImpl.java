package sjht.erp.workinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sjht.erp.workinfo.dto.request.HolidayRequestDto;
import sjht.erp.workinfo.repository.HolidayRegistryMapper;

@Service
public class HolidayRegistryServiceImpl implements HolidayRegistryService{

    @Autowired
    HolidayRegistryMapper holidayRegistryMapper;

    @Override
    public boolean insert(HolidayRequestDto holidayRequestDto) {
        return holidayRegistryMapper.insertHoliday(holidayRequestDto)!=0;
    }
}
