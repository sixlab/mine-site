package cn.sixlab.mine.site.data.dao;

import cn.sixlab.mine.site.data.models.MsMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsMenuDao extends JpaRepository<MsMenu, Integer> {

    @Query(value = " select m from MsMenu m " +
            " where 1 = 1 " +
            " and (:level is null or m.menuLevel = :level) " +
            " and (:keyword is null or m.menuName like %:keyword%) " +
            " order by m.weight ")
    Page<MsMenu> queryMsMenu(@Param("keyword") String keyword, @Param("level") Integer level, Pageable pageable);
    // WHERE IF (:byname is not null, c.byname LIKE CONCAT('%',:byname,'%') , 1 = 1) and IF (:isMember is not null, c.is_member = :isMember , 1 = 1) and IF (:isBlacklist is not null, c.is_blacklist = :isBlacklist , 1 = 1) and

    @Query(" select m from MsUserRole ur,MsRoleAuth ra,MsAuthResource ar, MsMenu m " +
            " where ur.userId = :userId " +
            " and ur.roleId = ra.roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'menu' " +
            " and ar.resourceId = m.id " +
            " order by m.weight")
    List<MsMenu> findUserMenus(@Param("userId") Integer userId);

    @Query(" select m from MsRoleAuth ra,MsAuthResource ar, MsMenu m " +
            " where ra.roleId = :roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'menu' " +
            " and ar.resourceId = m.id " +
            " and m.status = 1 " +
            " order by m.weight")
    List<MsMenu> findRoleMenus(@Param("roleId") Integer roleId);

    @Query(" select m from MsUserRole ur,MsRoleAuth ra,MsAuthResource ar, MsMenu m " +
            " where ur.userId = :userId " +
            " and ur.roleId = ra.roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'menu' " +
            " and ar.resourceId = m.id " +
            " and m.menuLevel = :level " +
            " and m.status = 1 " +
            " order by m.weight")
    List<MsMenu> findActiveUserMenus(@Param("userId") Integer userId, @Param("level") Integer level);

    @Query(" select m from MsRoleAuth ra,MsAuthResource ar, MsMenu m " +
            " where ra.roleId = :roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'menu' " +
            " and ar.resourceId = m.id " +
            " order by m.weight")
    List<MsMenu> findActiveRoleMenus(@Param("roleId") Integer roleId);

    @Query(" select m from MsUserRole ur,MsRoleAuth ra,MsAuthResource ar, MsMenu m " +
            " where ur.userId = :userId " +
            " and ur.roleId = ra.roleId " +
            " and ra.authId = ar.authId " +
            " and ar.type = 'menu' " +
            " and ar.resourceId = m.id " +
            " and m.menuLevel = :level " +
            " and m.parentId in :folderIds " +
            " and m.status = 1 " +
            " order by m.weight")
    List<MsMenu> findActiveUserSubMenus(@Param("userId") Integer userId, @Param("folderIds") Integer[] folderIds);
}
