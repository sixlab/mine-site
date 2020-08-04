package tech.minesoft.demo.mapper;

import tech.minesoft.demo.models.VodPlayer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VodPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodPlayer record);

    int insertSelective(VodPlayer record);

    VodPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodPlayer record);

    int updateByPrimaryKey(VodPlayer record);

    @Select(" select * " +
            " from vod_player " +
            " where player_code = #{playerCode,jdbcType=VARCHAR} ")
    VodPlayer selectByCode(@Param("playerCode") String playerCode);

    @Select(" select * " +
            " from vod_player " +
            " where status = 1" +
            " order by weight ")
    List<VodPlayer> selectEnable();

}