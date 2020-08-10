package top.glory.modules.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.glory.common.entity.DataEntity;

import java.io.Serializable;

/**
 * @author 春秋
 * @Description: 文件管理 实体类
 * @Date: 2020-07-25
 */

@Data
@TableName("bus_file")
public class BusFile extends DataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 关联id
     */
    private String referId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 缩略图路径
     */
    private String fileThumb;
    /**
     * 文件mime类型
     */
    private String fileMime;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件描述
     */
    private String fileDesc;

}
