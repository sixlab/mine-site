package tech.minesoft.minesite.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tech.minesoft.minesite.core.models.MsMeta;

public interface MsMetaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsMeta record);

    int insertSelective(MsMeta record);

    MsMeta selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsMeta record);

    int updateByPrimaryKeyWithBLOBs(MsMeta record);

    int updateByPrimaryKey(MsMeta record);

    void checkTable();

    void runSql(@Param("sql")String sql);

    MsMeta selectOne(@Param("metaGroup")String group,@Param("metaKey")String key);

    @Update(" update ms_meta " +
            " set meta_val = #{version} " +
            " where id = #{id} ")
    void updateVersion(@Param("id")Integer id, @Param("version")int version);
}