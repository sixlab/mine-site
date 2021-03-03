package tech.minesoft.mine.site.mysql.mapper;

import org.apache.ibatis.annotations.Select;
import tech.minesoft.mine.site.mysql.models.MsJobRecord;

import java.util.List;

public interface MsJobRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsJobRecord record);

    int insertSelective(MsJobRecord record);

    MsJobRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsJobRecord record);

    int updateByPrimaryKeyWithBLOBs(MsJobRecord record);

    int updateByPrimaryKey(MsJobRecord record);

    @Select(" select * " +
            " from ms_job_record " +
            " order by id desc " +
            " limit 100 ")
    List<MsJobRecord> loadLast();
}