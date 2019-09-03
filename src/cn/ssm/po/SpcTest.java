package cn.ssm.po;

public class SpcTest {
    private Integer testId;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String processName;

    private String characterVal;

    private Float testVal1;

    private Float testVal2;

    private Float testVal3;

    private Float testVal4;

    private Float testVal5;

    private Double sumX;

    private Double aveX;

    private Double r;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getClientMaterialNo() {
        return clientMaterialNo;
    }

    public void setClientMaterialNo(String clientMaterialNo) {
        this.clientMaterialNo = clientMaterialNo == null ? null : clientMaterialNo.trim();
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getCharacterVal() {
        return characterVal;
    }

    public void setCharacterVal(String characterVal) {
        this.characterVal = characterVal == null ? null : characterVal.trim();
    }

    public Float getTestVal1() {
        return testVal1;
    }

    public void setTestVal1(Float testVal1) {
        this.testVal1 = testVal1;
    }

    public Float getTestVal2() {
        return testVal2;
    }

    public void setTestVal2(Float testVal2) {
        this.testVal2 = testVal2;
    }

    public Float getTestVal3() {
        return testVal3;
    }

    public void setTestVal3(Float testVal3) {
        this.testVal3 = testVal3;
    }

    public Float getTestVal4() {
        return testVal4;
    }

    public void setTestVal4(Float testVal4) {
        this.testVal4 = testVal4;
    }

    public Float getTestVal5() {
        return testVal5;
    }

    public void setTestVal5(Float testVal5) {
        this.testVal5 = testVal5;
    }

    public Double getSumX() {
        return sumX;
    }

    public void setSumX(Double sumX) {
        this.sumX = sumX;
    }

    public Double getAveX() {
        return aveX;
    }

    public void setAveX(Double aveX) {
        this.aveX = aveX;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}