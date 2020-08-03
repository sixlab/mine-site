package cn.sixlab.mine.site.core.mapper;

import cn.sixlab.mine.site.core.models.MsNotifyConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsNotifyConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsNotifyConfig record);

    int insertSelective(MsNotifyConfig record);

    MsNotifyConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsNotifyConfig record);

    int updateByPrimaryKey(MsNotifyConfig record);

    @Select(" select * " +
            " from ms_notify_config " +
            " where code = #{code} ")
    List<MsNotifyConfig> queryAllByCode(@Param("code") String code);
}