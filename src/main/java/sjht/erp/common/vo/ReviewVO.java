package sjht.erp.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewVO {
    private BoardVO bno;
    private EmployeeVO empno;
    private String name;
    private String review;
    private LocalDateTime reviewdate;
}
