package spring.cloud.base.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haozai
 * @description 鉴权用户信息
 * @date 2021/6/20  16:49
 */
@Data
@NoArgsConstructor
public class AuthUserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String avatar = "https://gw.alipayobjects.com/zos/rmsportal/cnrhVkzwxjPwAaCfPbdc.png";

    private String address;

    private String position = "Java工程师 | 蚂蚁金服-计算服务事业群-微信平台部";
}
