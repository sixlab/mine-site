package cn.sixlab.mine.site.data.mapper;

import cn.sixlab.mine.site.data.models.VodInfoUrls;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VodInfoUrlsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VodInfoUrls record);

    int insertSelective(VodInfoUrls record);

    VodInfoUrls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VodInfoUrls record);

    int updateByPrimaryKeyWithBLOBs(VodInfoUrls record);

    int updateByPrimaryKey(VodInfoUrls record);

    void insertList(@Param("infoId") Integer infoId, @Param("urls") List<VodInfoUrls> urlsList);

    @Select(" select * " +
            " from vod_info_urls " +
            " where info_id = #{infoId,jdbcType=INTEGER} " +
            " and player_code = #{playerCode,jdbcType=VARCHAR} " +
            " limit 1 ")
    VodInfoUrls selectPlayerUrl(@Param("infoId") Integer infoId, @Param("playerCode") String playerCode);

    @Select(" select * " +
            " from vod_info_urls " +
            " where info_id = #{infoId,jdbcType=INTEGER} ")
    List<VodInfoUrls> selectByInfoId(@Param("infoId") Integer infoId);
}