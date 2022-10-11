package sjht.erp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private int bno;
    private EmployeeVO empno;
    private String title;
    private LocalDateTime regdate;
    private String contents;
    private String boardtype;
    private int boardcount;
}
