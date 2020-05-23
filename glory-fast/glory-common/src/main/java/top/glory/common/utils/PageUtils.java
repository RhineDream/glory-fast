package top.glory.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.glory.modules.system.vo.PageInfo;

public class PageUtils {
    public static PageInfo transPageData(IPage page) {
        PageInfo pageInfo = new PageInfo(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
        return pageInfo;
    }
}
