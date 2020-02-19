package cn.sixlab.mine.site.common.vo;

import cn.sixlab.mine.site.data.models.MsRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MineAuthority implements GrantedAuthority {
    private MsRole msRole;

    @Override
    public String getAuthority() {
        return msRole.getRoleName();
    }
}
