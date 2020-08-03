package cn.sixlab.mine.site.core.models;

import java.io.Serializable;
import java.util.Date;

public class MsJob implements Serializable {
    private Integer id;

    private String jobClz;

    private String jobName;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobClz() {
        return jobClz;
    }

    public void setJobClz(String jobClz) {
        this.jobClz = jobClz == null ? null : jobClz.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
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
}