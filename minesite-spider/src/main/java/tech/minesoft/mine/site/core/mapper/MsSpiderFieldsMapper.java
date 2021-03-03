package tech.minesoft.mine.site.core.mapper;

import tech.minesoft.mine.site.core.models.MsSpiderFields;

public interface MsSpiderFieldsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsSpiderFields record);

    int insertSelective(MsSpiderFields record);

    MsSpiderFields selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsSpiderFields record);

    int updateByPrimaryKeyWithBLOBs(MsSpiderFields record);

    int updateByPrimaryKey(MsSpiderFields record);
}