package sjht.erp.workinfo.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayRequestDto {
    private int empno;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private String reasonholiday;
    private String approveryn;
    private LocalDateTime registdate;

    public HolidayRequestDto(int empno, LocalDateTime startdate, LocalDateTime enddate, String reasonholiday) {
        this.empno = empno;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reasonholiday = reasonholiday;
    }
}
