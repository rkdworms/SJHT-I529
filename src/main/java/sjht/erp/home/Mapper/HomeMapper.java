package sjht.erp.home.Mapper;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.common.vo.BoardVO;

import java.util.List;

@Mapper
public interface HomeMapper {
    public List<BoardVO> getNoticeList();
}
