package cn.ssm.po;

public class TestProcess {
    private Integer processId;

    private String clientMaterialNo;

    private String materialNo;

    private String process;

    private String standardVal;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    public String getStandardVal() {
        return standardVal;
    }

    public void setStandardVal(String standardVal) {
        this.standardVal = standardVal == null ? null : standardVal.trim();
    }
}