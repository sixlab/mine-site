package tech.minesoft.minesite.core.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class MineAuthority implements GrantedAuthority {
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    public static List<MineAuthority> roles(String role){
        MineAuthority authority = new MineAuthority();
        authority.setRole(role);

        List<MineAuthority> roles = new ArrayList<>();
        roles.add(authority);

        return roles;
    }
}
