package tech.minesoft.mine.site.core.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.minesoft.mine.site.core.models.MsMenu;

import java.util.List;

public interface MsMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsMenu record);

    int insertSelective(MsMenu record);

    MsMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsMenu record);

    int updateByPrimaryKey(MsMenu record);

    @Select(" select menu_position " +
            " from ms_menu" +
            " group by menu_position ")
    List<String> selectPositions();

    @Select(" select * " +
            " from ms_menu " +
            " order by menu_position,menu_order ")
    List<MsMenu> selectAll();

    @Select(" select * " +
            " from ms_menu " +
            " where menu_position = #{menuPosition,jdbcType=VARCHAR} " +
            " order by menu_order ")
    List<MsMenu> selectPositionMenu(@Param("menuPosition")String menuPosition);

    @Delete(" delete from ms_menu " +
            " where menu_position = #{menuPosition,jdbcType=VARCHAR} ")
    void deletePosition(@Param("menuPosition")String menuPosition);

    void insertPositionMenu(@Param("menuPosition")String menuPosition,
                            @Param("menuList")List<MsMenu> menuList);
}