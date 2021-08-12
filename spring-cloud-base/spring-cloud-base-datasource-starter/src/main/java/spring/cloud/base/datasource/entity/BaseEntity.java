package spring.cloud.base.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author haozai
 * @description 基类 mybatis扫描
 * @date 2021/3/25  22:48
 */
@Data
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
}
