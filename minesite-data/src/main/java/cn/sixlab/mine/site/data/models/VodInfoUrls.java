package cn.sixlab.mine.site.data.models;

import java.io.Serializable;
import java.util.Date;

public class VodInfoUrls implements Serializable {
    private Integer id;

    private Integer infoId;

    private String playerCode;

    private String playerName;

    private String vodRemarks;

    private Integer status;

    private Date createTime;

    private String vodUrls;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode == null ? null : playerCode.trim();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName == null ? null : playerName.trim();
    }

    public String getVodRemarks() {
        return vodRemarks;
    }

    public void setVodRemarks(String vodRemarks) {
        this.vodRemarks = vodRemarks == null ? null : vodRemarks.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVodUrls() {
        return vodUrls;
    }

    public void setVodUrls(String vodUrls) {
        this.vodUrls = vodUrls == null ? null : vodUrls.trim();
    }
}