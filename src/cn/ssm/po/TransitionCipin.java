package cn.ssm.po;

public class TransitionCipin {
    private Integer cipinId;

    private Integer transitionId;

    private String cipinType;

    private String cipinSpecies;

    private String cipinNum;

    public Integer getCipinId() {
        return cipinId;
    }

    public void setCipinId(Integer cipinId) {
        this.cipinId = cipinId;
    }

    public Integer getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(Integer transitionId) {
        this.transitionId = transitionId;
    }

    public String getCipinType() {
        return cipinType;
    }

    public void setCipinType(String cipinType) {
        this.cipinType = cipinType == null ? null : cipinType.trim();
    }

    public String getCipinSpecies() {
        return cipinSpecies;
    }

    public void setCipinSpecies(String cipinSpecies) {
        this.cipinSpecies = cipinSpecies == null ? null : cipinSpecies.trim();
    }

    public String getCipinNum() {
        return cipinNum;
    }

    public void setCipinNum(String cipinNum) {
        this.cipinNum = cipinNum == null ? null : cipinNum.trim();
    }
}