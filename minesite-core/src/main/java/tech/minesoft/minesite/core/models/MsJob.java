package tech.minesoft.minesite.core.models;

import java.io.Serializable;
import java.util.Date;

public class MsJob implements Serializable {
    private Integer id;

    private String jobClz;

    private String jobName;

    private Integer status;

    private Date lastTime;

    private Integer lastStatus;

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

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Integer lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}