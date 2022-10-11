package sjht.erp.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sjht.erp.common.vo.BoardVO;
import sjht.erp.home.Mapper.HomeMapper;
import sjht.erp.home.dto.NoticeDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeMapper homeMapper;

    public List<NoticeDto> getNoticeList() {

        //BoardVO 불러오기 및 Dto 변환
        List<BoardVO> noticeVo = homeMapper.getNoticeList();
        List<NoticeDto> noticeDto = new ArrayList();

        if (noticeVo.size() > 6) {
            for(int i=0; i<6; i++){
                noticeDto.add(new NoticeDto(noticeVo.get(i)));
            }
        }else{
            for (BoardVO board : noticeVo) {
                noticeDto.add(new NoticeDto(board));
            }
        }

        return noticeDto;
    }
}
