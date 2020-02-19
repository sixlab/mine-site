package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsAuthDao extends JpaRepository<MsAuth, Integer> {

    @Query(" select a from MsAuth a,MsRoleAuth ra,MsUserRole ur " +
            " where a.id = ra.authId " +
            " and a.status = 1 " +
            " and ra.roleId = ur.roleId " +
            " and ur.userId = :userId ")
    List<MsAuth> findUserAuths(@Param("userId") Integer userId);
}
