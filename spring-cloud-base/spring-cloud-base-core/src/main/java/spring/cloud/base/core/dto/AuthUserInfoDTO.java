package spring.cloud.base.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

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

    private String avatar;

    private String address;

    private String position;

    private Collection<GrantedAuthority> authorities;
}
