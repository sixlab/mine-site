package tech.minesoft.mine.site.core.mapper;

import org.apache.ibatis.annotations.Param;
import tech.minesoft.mine.site.core.models.MsData;

import java.util.List;

public interface MsDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsData record);

    int insertSelective(MsData record);

    MsData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsData record);

    int updateByPrimaryKeyWithBLOBs(MsData record);

    int updateByPrimaryKey(MsData record);

    MsData selectOne(@Param("dataType")String dataType, @Param("param")String param);

    List<MsData> selectList(@Param("dataType")String dataType,
                            @Param("param")String param);

    List<MsData> selectOrderList(@Param("dataType")String dataType,
                            @Param("param")String param,
                            @Param("orderColumn")String orderColumn,
                            @Param("orderDesc")Boolean orderDesc);
}