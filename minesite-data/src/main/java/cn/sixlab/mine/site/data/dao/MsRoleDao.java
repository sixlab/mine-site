package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsRoleDao extends JpaRepository<MsRole, Integer> {

    @Query(" select u from MsRole u,MsUserRole a " +
            " where u.id = a.roleId " +
            " and u.status = 1 " +
            " and a.userId = :userId ")
    List<MsRole> findUserRoles(@Param("userId") Integer userId);

}
