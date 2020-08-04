package tech.minesoft.minesite.core.service;

import tech.minesoft.minesite.core.utils.Err;
import tech.minesoft.minesite.core.vo.LoginUser;
import tech.minesoft.minesite.core.vo.MineAuthority;
import tech.minesoft.minesite.core.vo.MineException;
import tech.minesoft.minesite.core.mapper.MsUserMapper;
import tech.minesoft.minesite.core.models.MsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MsUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MsUser msUser = userMapper.selectByUsername(username);

        if (null == msUser) {
            throw new UsernameNotFoundException("login.not.exist");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setMsUser(msUser);
        loginUser.setRoles(MineAuthority.roles(msUser.getRole()));
        return loginUser;
    }

    public MsUser loadUser(String username) {
        MsUser msUser = userMapper.selectByUsername(username);
        if (null == msUser) {
            throw new MineException(Err.ERR_NOT_EXIST, "login.not.exist");
        }
        msUser.setPassword(null);
        return msUser;
    }

    public MsUser loadUserById(Integer userId) {
        MsUser msUser = userMapper.selectByPrimaryKey(userId);
        if (null == msUser) {
            throw new MineException(Err.ERR_NOT_EXIST, "login.not.exist");
        }
        msUser.setPassword(null);
        return msUser;
    }
}
