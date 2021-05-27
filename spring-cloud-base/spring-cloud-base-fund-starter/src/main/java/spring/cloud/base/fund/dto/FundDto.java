package spring.cloud.base.fund.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author haozai
 * @description 基金表
 * @date 2021/5/06  15:13
 */
@Data
@Accessors(chain = true)
public class FundDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String fundCode;

    private String fundName;

    private String fundType;

    private String companyCode;

    private String companyName;
}
