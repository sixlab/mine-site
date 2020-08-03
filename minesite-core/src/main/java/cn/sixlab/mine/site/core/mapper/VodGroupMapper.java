package cn.sixlab.mine.site.core.mapper;

import cn.sixlab.mine.site.core.models.VodGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VodGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodGroup record);

    int insertSelective(VodGroup record);

    VodGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodGroup record);

    int updateByPrimaryKey(VodGroup record);

    @Select(" select * " +
            " from vod_group " +
            " where group_name = #{groupName,jdbcType=VARCHAR} ")
    VodGroup selectByName(@Param("groupName") String groupName);
}