package tech.minesoft.mine.site.tools;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DbPwdTools {
    public static void main(String[] args) throws Exception {
        ConfigTools.main(new String[]{""});
        System.out.println(new BCryptPasswordEncoder().encode("qwe123"));
    }
}
