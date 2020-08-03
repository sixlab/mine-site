package cn.sixlab.mine.site.core.models;

import java.io.Serializable;
import java.util.Date;

public class VodInfo implements Serializable {
    private Integer id;

    private String vodName;

    private String vodGroup;

    private String vodPic;

    private String vodArea;

    private String vodLang;

    private String vodYear;

    private String vodActor;

    private String vodDirector;

    private String vodIntro;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName == null ? null : vodName.trim();
    }

    public String getVodGroup() {
        return vodGroup;
    }

    public void setVodGroup(String vodGroup) {
        this.vodGroup = vodGroup == null ? null : vodGroup.trim();
    }

    public String getVodPic() {
        return vodPic;
    }

    public void setVodPic(String vodPic) {
        this.vodPic = vodPic == null ? null : vodPic.trim();
    }

    public String getVodArea() {
        return vodArea;
    }

    public void setVodArea(String vodArea) {
        this.vodArea = vodArea == null ? null : vodArea.trim();
    }

    public String getVodLang() {
        return vodLang;
    }

    public void setVodLang(String vodLang) {
        this.vodLang = vodLang == null ? null : vodLang.trim();
    }

    public String getVodYear() {
        return vodYear;
    }

    public void setVodYear(String vodYear) {
        this.vodYear = vodYear == null ? null : vodYear.trim();
    }

    public String getVodActor() {
        return vodActor;
    }

    public void setVodActor(String vodActor) {
        this.vodActor = vodActor == null ? null : vodActor.trim();
    }

    public String getVodDirector() {
        return vodDirector;
    }

    public void setVodDirector(String vodDirector) {
        this.vodDirector = vodDirector == null ? null : vodDirector.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVodIntro() {
        return vodIntro;
    }

    public void setVodIntro(String vodIntro) {
        this.vodIntro = vodIntro == null ? null : vodIntro.trim();
    }
}