package sjht.erp.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileVO {
    private int fileno;
    private String filename;
    private String filepath;
    private int filesize;
}
