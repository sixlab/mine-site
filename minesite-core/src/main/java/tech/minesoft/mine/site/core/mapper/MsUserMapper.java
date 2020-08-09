package tech.minesoft.mine.site.core.mapper;

import tech.minesoft.mine.site.core.models.MsUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsUser record);

    int insertSelective(MsUser record);

    MsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsUser record);

    int updateByPrimaryKey(MsUser record);

    @Select(" select * " +
            " from ms_user " +
            " where token = #{token} " +
            "   and expire_time >= now()  ")
    MsUser selectByToken(@Param("token") String token);

    @Select("select * " +
            " from ms_user " +
            " where username = #{username} ")
    MsUser selectByUsername(@Param("username") String username);

    @Update(" update ms_user " +
            " set " +
            "     token = #{token}, " +
            "     expire_time = DATE_ADD(now(), INTERVAL ${expire} SECOND), " +
            "     login_time = now() " +
            " where id = #{userId} ")
    void updateToken(@Param("userId") Integer userId, @Param("token") String token, @Param("expire") int expire);

    @Update(" update ms_user " +
            " set " +
            "     token = null, " +
            "     expire_time = null, " +
            "     login_time = null " +
            " where id = #{userId} ")
    void delToken(@Param("token")String token);
}