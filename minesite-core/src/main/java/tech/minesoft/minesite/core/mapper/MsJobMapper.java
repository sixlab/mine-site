package tech.minesoft.minesite.core.mapper;

import tech.minesoft.minesite.core.models.MsJob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MsJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsJob record);

    int insertSelective(MsJob record);

    MsJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsJob record);

    int updateByPrimaryKey(MsJob record);

    @Select(" select *" +
            " from ms_job" +
            " where job_clz = #{jobClz} " +
            " limit 1")
    MsJob selectByClz(@Param("jobClz") String jobClz);
}