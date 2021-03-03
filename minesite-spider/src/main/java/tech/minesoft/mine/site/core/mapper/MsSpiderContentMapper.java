package tech.minesoft.mine.site.core.mapper;

import org.apache.ibatis.annotations.Param;
import tech.minesoft.mine.site.core.models.MsSpiderContent;

public interface MsSpiderContentMapper {
    int deleteByPrimaryKey(@Param("jobId")Integer jobId, @Param("id") Integer id);

    int insert(MsSpiderContent record);

    int insertSelective(MsSpiderContent record);

    MsSpiderContent selectByPrimaryKey(@Param("jobId")Integer jobId, @Param("id")  Integer id);

    int updateByPrimaryKeySelective(MsSpiderContent record);

    int updateByPrimaryKeyWithBLOBs(MsSpiderContent record);

    int updateByPrimaryKey(MsSpiderContent record);

    void createTable(@Param("jobId")Integer jobId);
}