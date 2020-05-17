package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.MsData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MsDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsData record);

    int insertSelective(MsData record);

    MsData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsData record);

    int updateByPrimaryKey(MsData record);

    @Select(" select * " +
            " from ms_data " +
            " where code = #{code} " +
            " order by create_time desc limit 1")
    MsData selectFirstByCode(@Param("code") String code);

}