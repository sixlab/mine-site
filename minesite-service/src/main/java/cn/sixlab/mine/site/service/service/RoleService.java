package cn.sixlab.mine.site.service.service;

import cn.sixlab.mine.site.data.dao.MsRoleAuthDao;
import cn.sixlab.mine.site.data.dao.MsRoleDao;
import cn.sixlab.mine.site.data.dao.MsUserRoleDao;
import cn.sixlab.mine.site.data.models.MsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private MsRoleDao roleDao;
    @Autowired
    private MsUserRoleDao userRoleDao;
    @Autowired
    private MsRoleAuthDao roleAuthDao;

    public List<MsRole> loadUserRole(Integer userId) {
        return roleDao.findUserRoles(userId);
    }

    public MsRole submitRole(MsRole role) {
        if (null == role.getId()) {
            role.setStatus(1);
            role.setCreateTime(new Date());
        }

        return roleDao.save(role);
    }

    public void delRole(Integer roleId) {
        Optional<MsRole> byId = roleDao.findById(roleId);

        if (byId.isPresent()) {
            roleDao.deleteById(roleId);
            userRoleDao.deleteAllByRoleId(roleId);
            roleAuthDao.deleteAllByRoleId(roleId);
        }

    }

    public void changeRole(Integer roleId) {
        Optional<MsRole> byId = roleDao.findById(roleId);

        if (byId.isPresent()) {
            MsRole msRole = byId.get();
            if (0 == msRole.getStatus()) {
                msRole.setStatus(1);
            } else {
                msRole.setStatus(0);
            }

            roleDao.save(msRole);
        }
    }
}
