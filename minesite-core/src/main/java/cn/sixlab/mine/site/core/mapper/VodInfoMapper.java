package cn.sixlab.mine.site.core.mapper;

import cn.sixlab.mine.site.core.models.VodInfo;
import org.apache.ibatis.annotations.Select;

public interface VodInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodInfo record);

    int insertSelective(VodInfo record);

    VodInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodInfo record);

    int updateByPrimaryKeyWithBLOBs(VodInfo record);

    int updateByPrimaryKey(VodInfo record);

    @Select(" select * " +
            " from vod_info " +
            " where vod_name = #{vodName,jdbcType=VARCHAR} " +
            " and vod_director = #{vodDirector,jdbcType=VARCHAR} " +
            " and vod_year = #{vodYear,jdbcType=VARCHAR} " +
            " order by id limit 1")
    VodInfo selectExist(VodInfo info);
}