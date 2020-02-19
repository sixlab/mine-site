package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsButtonDao extends JpaRepository<MsButton, Integer> {

    @Query(" select b from MsUserRole ur,MsRoleAuth ra,MsAuthResource ar, MsButton b" +
            " where ur.userId = :userId " +
            " and ur.roleId = ra.roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'button' " +
            " and ar.resourceId = b.id ")
    List<MsButton> findUserButtons(@Param("userId") Integer userId);

    @Query(" select b from MsRoleAuth ra,MsAuthResource ar, MsButton b" +
            " where ra.roleId = :roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'button' " +
            " and ar.resourceId = b.id " +
            " and b.status = 1 ")
    List<MsButton> findRoleButtons(@Param("roleId") Integer roleId);

    @Query(" select b from MsUserRole ur,MsRoleAuth ra,MsAuthResource ar, MsButton b" +
            " where ur.userId = :userId " +
            " and ur.roleId = ra.roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'button' " +
            " and ar.resourceId = b.id " +
            " and b.status = 1 ")
    List<MsButton> findActiveUserButtons(@Param("userId") Integer userId);

    @Query(" select b from MsRoleAuth ra,MsAuthResource ar, MsButton b" +
            " where ra.roleId = :roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'button' " +
            " and ar.resourceId = b.id ")
    List<MsButton> findActiveRoleButtons(@Param("roleId") Integer roleId);

}
