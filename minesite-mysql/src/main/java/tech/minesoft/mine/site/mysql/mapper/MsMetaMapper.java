package tech.minesoft.mine.site.mysql.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.minesoft.mine.site.mysql.models.MsMeta;

import java.util.List;

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
            " set meta_val = #{val} " +
            " where id = #{id} ")
    void updateVal(@Param("id")Integer id, @Param("val")int val);

    @Select(" select * " +
            " from ms_meta " +
            " where meta_group = #{metaGroup,jdbcType=VARCHAR} ")
    List<MsMeta> selectGroup(@Param("metaGroup")String metaGroup);

    @Select(" select * " +
            " from ms_meta " +
            " order by id ")
    List<MsMeta> selectAll();
}