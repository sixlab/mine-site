package tech.minesoft.mine.site.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.mysql.mapper.MsUserMapper;
import tech.minesoft.mine.site.mysql.models.MsUser;
import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.common.vo.LoginUser;
import tech.minesoft.mine.site.core.vo.MineAuthority;
import tech.minesoft.mine.site.core.vo.MineException;

import java.util.Date;
import java.util.List;

@Service
public class MsUserService implements UserDetailsService {
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

    public List<MsUser> loadAll() {
        return userMapper.selectAll();
    }

    public MsUser select(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public void add(MsUser data) {
        data.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        data.setCreateTime(new Date());

        userMapper.insert(data);
    }

    public void modify(MsUser data) {
        MsUser old = userMapper.selectByPrimaryKey(data.getId());

        old.setUsername(data.getUsername());
        old.setNickname(data.getNickname());
        if(StringUtils.isNotEmpty(data.getPassword())){
            old.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        }
        old.setRole(data.getRole());
        old.setStatus(data.getStatus());
        old.setToken(null);
        old.setExpireTime(null);

        userMapper.updateByPrimaryKey(old);
    }
}
