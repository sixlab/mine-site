package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.VodGroup;

public interface VodGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodGroup record);

    int insertSelective(VodGroup record);

    VodGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodGroup record);

    int updateByPrimaryKey(VodGroup record);
}