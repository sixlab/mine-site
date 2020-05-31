package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.VodSite;

public interface VodSiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodSite record);

    int insertSelective(VodSite record);

    VodSite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodSite record);

    int updateByPrimaryKeyWithBLOBs(VodSite record);

    int updateByPrimaryKey(VodSite record);
}