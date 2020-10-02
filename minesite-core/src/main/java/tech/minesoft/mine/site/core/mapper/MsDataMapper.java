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

    int delete(@Param("userId")Integer userId,
               @Param("dataType")String dataType,
               @Param("dataId")String dataId,
               @Param("cnd")String cnd);

    MsData selectOne(@Param("userId")Integer userId,
                     @Param("dataType")String dataType,
                     @Param("dataId")String dataId,
                     @Param("cnd")String cnd);

    List<MsData> selectList(@Param("userId")Integer userId,
                            @Param("dataType")String dataType,
                            @Param("cnd")String cnd);

    List<MsData> selectOrderList(@Param("userId")Integer userId,
                                 @Param("dataType")String dataType,
                                 @Param("cnd")String cnd,
                                 @Param("orderColumn")String orderColumn,
                                 @Param("orderDesc")Boolean orderDesc);
}