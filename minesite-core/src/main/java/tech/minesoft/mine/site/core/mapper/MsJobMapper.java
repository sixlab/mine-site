package tech.minesoft.mine.site.core.mapper;

import tech.minesoft.mine.site.core.models.MsJob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsJob record);

    int insertSelective(MsJob record);

    MsJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsJob record);

    int updateByPrimaryKey(MsJob record);

    @Select(" select * " +
            " from ms_job " +
            " where job_code = #{jobCode,jdbcType=VARCHAR} " +
            " and status=1 " +
            " limit 1 ")
    MsJob selectByCode(@Param("jobCode") String jobCode);

    @Select(" select * " +
            " from ms_job " +
            " order by id ")
    List<MsJob> selectAll();
}