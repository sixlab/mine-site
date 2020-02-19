package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsRoleAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsRoleAuthDao extends JpaRepository<MsRoleAuth, Integer> {

    void deleteAllByAuthId(Integer authId);

    void deleteAllByRoleId(Integer roleId);

}
