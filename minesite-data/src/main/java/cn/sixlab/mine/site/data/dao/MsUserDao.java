package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MsUserDao extends JpaRepository<MsUser, Integer> {

    MsUser findByUsername(String username);

    @Query(" select count(ur.id) from MsUserRole ur,MsRoleAuth ra, MsAuth a" +
            " where ur.roleId = ra.roleId " +
            " and ur.userId = :userId " +
            " and a.id = ra.authId " +
            " and a.status = 1 " +
            " and a.authCode in :codes ")
    int countUserRole(@Param("userId") Integer userId, @Param("codes") String[] codes);
    
}
