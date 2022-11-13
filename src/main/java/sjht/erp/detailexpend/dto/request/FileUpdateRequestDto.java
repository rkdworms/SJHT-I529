package sjht.erp.detailexpend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUpdateRequestDto {
    private int filesize;
    private String physicalpath;
    private String filename;
    private int dno;
}
