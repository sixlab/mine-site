package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.MsUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsUser record);

    int insertSelective(MsUser record);

    MsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsUser record);

    int updateByPrimaryKey(MsUser record);

    @Select("select * " +
            " from ms_user " +
            " where username = #{username} ")
    MsUser selectByUsername(@Param("username") String username);
}