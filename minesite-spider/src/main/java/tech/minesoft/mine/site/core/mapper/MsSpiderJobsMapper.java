package tech.minesoft.mine.site.core.mapper;

import tech.minesoft.mine.site.core.models.MsSpiderJobs;

public interface MsSpiderJobsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsSpiderJobs record);

    int insertSelective(MsSpiderJobs record);

    MsSpiderJobs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsSpiderJobs record);

    int updateByPrimaryKeyWithBLOBs(MsSpiderJobs record);

    int updateByPrimaryKey(MsSpiderJobs record);
}