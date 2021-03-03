package tech.minesoft.mine.site.core.mapper;

import tech.minesoft.mine.site.core.models.MsSpiderUrls;

public interface MsSpiderUrlsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsSpiderUrls record);

    int insertSelective(MsSpiderUrls record);

    MsSpiderUrls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsSpiderUrls record);

    int updateByPrimaryKey(MsSpiderUrls record);
}