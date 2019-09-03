package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class AaUnit {
    private Integer id;

    private String code;

    private String name;

    private Byte ismainunit;

    private BigDecimal changerate;

    private Byte issingleunit;

    private Byte disabled;

    private String updatedby;

    private String ratedescription;

    private Byte isgroup;

    private Integer idunitgroup;

    private Integer changetype;

    private Integer changetype1;

    private Date madedate;

    private Date updated;

    private Date createdtime;

    private byte[] ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIsmainunit() {
        return ismainunit;
    }

    public void setIsmainunit(Byte ismainunit) {
        this.ismainunit = ismainunit;
    }

    public BigDecimal getChangerate() {
        return changerate;
    }

    public void setChangerate(BigDecimal changerate) {
        this.changerate = changerate;
    }

    public Byte getIssingleunit() {
        return issingleunit;
    }

    public void setIssingleunit(Byte issingleunit) {
        this.issingleunit = issingleunit;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
    }

    public String getRatedescription() {
        return ratedescription;
    }

    public void setRatedescription(String ratedescription) {
        this.ratedescription = ratedescription == null ? null : ratedescription.trim();
    }

    public Byte getIsgroup() {
        return isgroup;
    }

    public void setIsgroup(Byte isgroup) {
        this.isgroup = isgroup;
    }

    public Integer getIdunitgroup() {
        return idunitgroup;
    }

    public void setIdunitgroup(Integer idunitgroup) {
        this.idunitgroup = idunitgroup;
    }

    public Integer getChangetype() {
        return changetype;
    }

    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    public Integer getChangetype1() {
        return changetype1;
    }

    public void setChangetype1(Integer changetype1) {
        this.changetype1 = changetype1;
    }

    public Date getMadedate() {
        return madedate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}