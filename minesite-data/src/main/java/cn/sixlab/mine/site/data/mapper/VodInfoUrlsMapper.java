package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.VodInfoUrls;

public interface VodInfoUrlsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodInfoUrls record);

    int insertSelective(VodInfoUrls record);

    VodInfoUrls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodInfoUrls record);

    int updateByPrimaryKeyWithBLOBs(VodInfoUrls record);

    int updateByPrimaryKey(VodInfoUrls record);
}