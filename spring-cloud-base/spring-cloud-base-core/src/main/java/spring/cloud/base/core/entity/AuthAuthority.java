package spring.cloud.base.core.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author haozai
 * @description 操作权限表
 * @date 2021/8/25 12:40
 */

@Data
public class AuthAuthority implements GrantedAuthority {

    private Long id;

    private Long menuId;

    private String name;

    private String code;

    @Override
    public String getAuthority() {
        return code;
    }
}
