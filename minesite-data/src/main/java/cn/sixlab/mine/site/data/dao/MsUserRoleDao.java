package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsUserRoleDao extends JpaRepository<MsUserRole, Integer> {

    void deleteAllByRoleId(Integer roleId);
}
