package cn.ssm.po;

public class MiddleProductDetail {
    private Integer detailId;

    private Integer id;

    private String editPerson;

    private String editTime;

    private Integer oldNum;

    private Integer newNum;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEditPerson() {
        return editPerson;
    }

    public void setEditPerson(String editPerson) {
        this.editPerson = editPerson == null ? null : editPerson.trim();
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    public Integer getOldNum() {
        return oldNum;
    }

    public void setOldNum(Integer oldNum) {
        this.oldNum = oldNum;
    }

    public Integer getNewNum() {
        return newNum;
    }

    public void setNewNum(Integer newNum) {
        this.newNum = newNum;
    }
}