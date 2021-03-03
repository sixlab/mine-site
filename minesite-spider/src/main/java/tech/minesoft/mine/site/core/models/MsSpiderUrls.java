package tech.minesoft.mine.site.core.models;

import java.io.Serializable;
import java.util.Date;

public class MsSpiderUrls implements Serializable {
    private Integer id;

    private Integer jobId;

    private String url;

    private String urlStatus;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlStatus() {
        return urlStatus;
    }

    public void setUrlStatus(String urlStatus) {
        this.urlStatus = urlStatus == null ? null : urlStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}