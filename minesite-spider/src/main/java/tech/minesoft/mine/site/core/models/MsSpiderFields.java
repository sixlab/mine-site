package tech.minesoft.mine.site.core.models;

import java.io.Serializable;
import java.util.Date;

public class MsSpiderFields implements Serializable {
    private Integer id;

    private Integer jobId;

    private String fieldName;

    private String fieldCode;

    private String fieldType;

    private Integer urlJobId;

    private String fieldStatus;

    private Date createTime;

    private String fieldXpath;

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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    public Integer getUrlJobId() {
        return urlJobId;
    }

    public void setUrlJobId(Integer urlJobId) {
        this.urlJobId = urlJobId;
    }

    public String getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(String fieldStatus) {
        this.fieldStatus = fieldStatus == null ? null : fieldStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFieldXpath() {
        return fieldXpath;
    }

    public void setFieldXpath(String fieldXpath) {
        this.fieldXpath = fieldXpath == null ? null : fieldXpath.trim();
    }
}