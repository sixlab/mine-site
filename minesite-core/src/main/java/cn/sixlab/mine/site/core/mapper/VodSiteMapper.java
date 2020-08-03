package cn.sixlab.mine.site.core.mapper;

import cn.sixlab.mine.site.core.models.VodSite;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VodSiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodSite record);

    int insertSelective(VodSite record);

    VodSite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodSite record);

    int updateByPrimaryKeyWithBLOBs(VodSite record);

    int updateByPrimaryKey(VodSite record);

    @Select(" select * " +
            " from vod_site " +
            " where site_code = #{siteCode} ")
    VodSite selectByCode(@Param("siteCode") String siteCode);

    @Select(" select * " +
            " from vod_site " +
            " where beginTime is null ")
    List<VodSite> selectInit();
}