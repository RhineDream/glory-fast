package top.glory.modules.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private Long pageNo;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 总数
     */
    private Long total;

    /**
     * 数据
     */
    private List<Object> records;
}
