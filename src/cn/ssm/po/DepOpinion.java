package cn.ssm.po;

public class DepOpinion {
    private Integer opinionId;

    private Integer abnormalId;

    private String department;

    private String opinion;

    private String advisor;

    private String date;

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public Integer getAbnormalId() {
        return abnormalId;
    }

    public void setAbnormalId(Integer abnormalId) {
        this.abnormalId = abnormalId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor == null ? null : advisor.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}