package tech.minesoft.demo.mapper;

import tech.minesoft.demo.models.VodUserStar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VodUserStarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodUserStar record);

    int insertSelective(VodUserStar record);

    VodUserStar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodUserStar record);

    int updateByPrimaryKey(VodUserStar record);

    @Select(" select * " +
            " from vod_user_star " +
            " where user_id = #{userId,jdbcType=INTEGER} " +
            " and info_id = #{infoId,jdbcType=INTEGER} " +
            " limit 1 ")
    VodUserStar selectExist(@Param("userId") Integer userId, @Param("infoId") Integer infoId);

    @Delete(" delete from vod_user_star " +
            " where user_id = #{userId,jdbcType=INTEGER} " +
            " and info_id = #{infoId,jdbcType=INTEGER} ")
    void delExist(@Param("userId") Integer userId, @Param("infoId") Integer infoId);

    @Select(" select * " +
            " from vod_user_star " +
            " where user_id = #{userId,jdbcType=INTEGER} " +
            " order by id desc ")
    List<VodUserStar> selectByUserId(@Param("userId")Integer userId);
}