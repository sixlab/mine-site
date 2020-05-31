package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.VodPlayer;

public interface VodPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodPlayer record);

    int insertSelective(VodPlayer record);

    VodPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodPlayer record);

    int updateByPrimaryKey(VodPlayer record);
}