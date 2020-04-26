package cn.sixlab.mine.site.service.service;

import cn.sixlab.mine.site.common.vo.LoginUser;
import cn.sixlab.mine.site.common.vo.MineAuthority;
import cn.sixlab.mine.site.data.dao.MsUserDao;
import cn.sixlab.mine.site.data.models.MsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MsUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MsUser msUser = userDao.findByUsername(username);

        if (null == msUser) {
            throw new UsernameNotFoundException("login.not.exist");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setMsUser(msUser);
        loginUser.setRoles(MineAuthority.roles(msUser.getRole()));
        return loginUser;
    }

    public MsUser loadUserById(Integer userId){
        MsUser msUser = userDao.getOne(userId);
        msUser.setPassword(null);
        return msUser;
    }
}
