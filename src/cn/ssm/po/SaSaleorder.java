package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class SaSaleorder {
    private Integer id;

    private String code;

    private String name;

    private BigDecimal discountrate;

    private BigDecimal discountamount;

    private BigDecimal origdiscountamount;

    private BigDecimal exchangerate;

    private String address;

    private String linkman;

    private String contractcode;

    private BigDecimal origearnestmoney;

    private BigDecimal earnestmoney;

    private String memo;

    private BigDecimal origamount;

    private BigDecimal amount;

    private BigDecimal origtaxamount;

    private BigDecimal taxamount;

    private String contactphone;

    private String maker;

    private String auditor;

    private String reviser;

    private Byte iscarriedforwardout;

    private Byte iscarriedforwardin;

    private Byte ismodifiedcode;

    private String docid;

    private String updatedby;

    private String priuserdefnvc1;

    private BigDecimal priuserdefdecm1;

    private String priuserdefnvc2;

    private BigDecimal priuserdefdecm2;

    private String priuserdefnvc3;

    private BigDecimal priuserdefdecm3;

    private String priuserdefnvc4;

    private BigDecimal priuserdefdecm4;

    private String priuserdefnvc5;

    private BigDecimal priuserdefdecm5;

    private String priuserdefnvc6;

    private BigDecimal priuserdefdecm6;

    private String pubuserdefnvc1;

    private BigDecimal pubuserdefdecm1;

    private String pubuserdefnvc2;

    private BigDecimal pubuserdefdecm2;

    private String pubuserdefnvc3;

    private BigDecimal pubuserdefdecm3;

    private String pubuserdefnvc4;

    private BigDecimal pubuserdefdecm4;

    private String pubuserdefnvc5;

    private BigDecimal pubuserdefdecm5;

    private String pubuserdefnvc6;

    private BigDecimal pubuserdefdecm6;

    private Byte isautogeneratesaleorderbom;

    private Byte isautogeneraterouting;

    private String sourcevouchercode;

    private String isnomodify;

    private String changer;

    private String mobilephone;

    private String memberaddress;

    private Integer printcount;

    private Byte isseparatebywarehouse;

    private BigDecimal origreceiveamount;

    private BigDecimal receiveamount;

    private String collaboratevouchercode;

    private String externalcode;

    private Integer idbusinesstype;

    private Integer idcurrency;

    private Integer iddepartment;

    private Integer idmember;

    private Integer idmarketingorgan;

    private Integer idcustomer;

    private Integer idsettlecustomer;

    private Integer idclerk;

    private Integer idproject;

    private Integer idwarehouse;

    private Integer idcollaborateupvouchertype;

    private Integer idcollaborateupvoucher;

    private Integer datasource;

    private Integer deliverymode;

    private Integer iscancel;

    private Integer issaledelivery;

    private Integer issaleout;

    private Integer recivetype;

    private Integer voucherstate;

    private Integer auditorid;

    private Integer makerid;

    private Integer changerid;

    private Integer idcollaboratedownvouchertype;

    private Integer collaboratestate;

    private Integer idcollaboratedowndraftvoucher;

    private Integer sourcevoucherid;

    private Integer idsourcevouchertype;

    private Date deliverydate;

    private Date voucherdate;

    private Date madedate;

    private Date auditeddate;

    private Date createdtime;

    private Date updated;

    private Date changedate;

    private String externalvouchercode;

    private String customerphone;

    private Boolean hasrecordcredit;

    private Boolean isautowarehouse;

    private Integer inputdatasource;

    private String closer;

    private Date closedate;

    private byte[] ts;
    
    //新加映射子表
    private Integer idinventory;
    
    private Integer idbaseunit;
    
    private BigDecimal quantity;
    
    private String compositionquantity;

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

    public BigDecimal getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(BigDecimal discountrate) {
        this.discountrate = discountrate;
    }

    public BigDecimal getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(BigDecimal discountamount) {
        this.discountamount = discountamount;
    }

    public BigDecimal getOrigdiscountamount() {
        return origdiscountamount;
    }

    public void setOrigdiscountamount(BigDecimal origdiscountamount) {
        this.origdiscountamount = origdiscountamount;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode == null ? null : contractcode.trim();
    }

    public BigDecimal getOrigearnestmoney() {
        return origearnestmoney;
    }

    public void setOrigearnestmoney(BigDecimal origearnestmoney) {
        this.origearnestmoney = origearnestmoney;
    }

    public BigDecimal getEarnestmoney() {
        return earnestmoney;
    }

    public void setEarnestmoney(BigDecimal earnestmoney) {
        this.earnestmoney = earnestmoney;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public BigDecimal getOrigamount() {
        return origamount;
    }

    public void setOrigamount(BigDecimal origamount) {
        this.origamount = origamount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOrigtaxamount() {
        return origtaxamount;
    }

    public void setOrigtaxamount(BigDecimal origtaxamount) {
        this.origtaxamount = origtaxamount;
    }

    public BigDecimal getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public String getReviser() {
        return reviser;
    }

    public void setReviser(String reviser) {
        this.reviser = reviser == null ? null : reviser.trim();
    }

    public Byte getIscarriedforwardout() {
        return iscarriedforwardout;
    }

    public void setIscarriedforwardout(Byte iscarriedforwardout) {
        this.iscarriedforwardout = iscarriedforwardout;
    }

    public Byte getIscarriedforwardin() {
        return iscarriedforwardin;
    }

    public void setIscarriedforwardin(Byte iscarriedforwardin) {
        this.iscarriedforwardin = iscarriedforwardin;
    }

    public Byte getIsmodifiedcode() {
        return ismodifiedcode;
    }

    public void setIsmodifiedcode(Byte ismodifiedcode) {
        this.ismodifiedcode = ismodifiedcode;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid == null ? null : docid.trim();
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
    }

    public String getPriuserdefnvc1() {
        return priuserdefnvc1;
    }

    public void setPriuserdefnvc1(String priuserdefnvc1) {
        this.priuserdefnvc1 = priuserdefnvc1 == null ? null : priuserdefnvc1.trim();
    }

    public BigDecimal getPriuserdefdecm1() {
        return priuserdefdecm1;
    }

    public void setPriuserdefdecm1(BigDecimal priuserdefdecm1) {
        this.priuserdefdecm1 = priuserdefdecm1;
    }

    public String getPriuserdefnvc2() {
        return priuserdefnvc2;
    }

    public void setPriuserdefnvc2(String priuserdefnvc2) {
        this.priuserdefnvc2 = priuserdefnvc2 == null ? null : priuserdefnvc2.trim();
    }

    public BigDecimal getPriuserdefdecm2() {
        return priuserdefdecm2;
    }

    public void setPriuserdefdecm2(BigDecimal priuserdefdecm2) {
        this.priuserdefdecm2 = priuserdefdecm2;
    }

    public String getPriuserdefnvc3() {
        return priuserdefnvc3;
    }

    public void setPriuserdefnvc3(String priuserdefnvc3) {
        this.priuserdefnvc3 = priuserdefnvc3 == null ? null : priuserdefnvc3.trim();
    }

    public BigDecimal getPriuserdefdecm3() {
        return priuserdefdecm3;
    }

    public void setPriuserdefdecm3(BigDecimal priuserdefdecm3) {
        this.priuserdefdecm3 = priuserdefdecm3;
    }

    public String getPriuserdefnvc4() {
        return priuserdefnvc4;
    }

    public void setPriuserdefnvc4(String priuserdefnvc4) {
        this.priuserdefnvc4 = priuserdefnvc4 == null ? null : priuserdefnvc4.trim();
    }

    public BigDecimal getPriuserdefdecm4() {
        return priuserdefdecm4;
    }

    public void setPriuserdefdecm4(BigDecimal priuserdefdecm4) {
        this.priuserdefdecm4 = priuserdefdecm4;
    }

    public String getPriuserdefnvc5() {
        return priuserdefnvc5;
    }

    public void setPriuserdefnvc5(String priuserdefnvc5) {
        this.priuserdefnvc5 = priuserdefnvc5 == null ? null : priuserdefnvc5.trim();
    }

    public BigDecimal getPriuserdefdecm5() {
        return priuserdefdecm5;
    }

    public void setPriuserdefdecm5(BigDecimal priuserdefdecm5) {
        this.priuserdefdecm5 = priuserdefdecm5;
    }

    public String getPriuserdefnvc6() {
        return priuserdefnvc6;
    }

    public void setPriuserdefnvc6(String priuserdefnvc6) {
        this.priuserdefnvc6 = priuserdefnvc6 == null ? null : priuserdefnvc6.trim();
    }

    public BigDecimal getPriuserdefdecm6() {
        return priuserdefdecm6;
    }

    public void setPriuserdefdecm6(BigDecimal priuserdefdecm6) {
        this.priuserdefdecm6 = priuserdefdecm6;
    }

    public String getPubuserdefnvc1() {
        return pubuserdefnvc1;
    }

    public void setPubuserdefnvc1(String pubuserdefnvc1) {
        this.pubuserdefnvc1 = pubuserdefnvc1 == null ? null : pubuserdefnvc1.trim();
    }

    public BigDecimal getPubuserdefdecm1() {
        return pubuserdefdecm1;
    }

    public void setPubuserdefdecm1(BigDecimal pubuserdefdecm1) {
        this.pubuserdefdecm1 = pubuserdefdecm1;
    }

    public String getPubuserdefnvc2() {
        return pubuserdefnvc2;
    }

    public void setPubuserdefnvc2(String pubuserdefnvc2) {
        this.pubuserdefnvc2 = pubuserdefnvc2 == null ? null : pubuserdefnvc2.trim();
    }

    public BigDecimal getPubuserdefdecm2() {
        return pubuserdefdecm2;
    }

    public void setPubuserdefdecm2(BigDecimal pubuserdefdecm2) {
        this.pubuserdefdecm2 = pubuserdefdecm2;
    }

    public String getPubuserdefnvc3() {
        return pubuserdefnvc3;
    }

    public void setPubuserdefnvc3(String pubuserdefnvc3) {
        this.pubuserdefnvc3 = pubuserdefnvc3 == null ? null : pubuserdefnvc3.trim();
    }

    public BigDecimal getPubuserdefdecm3() {
        return pubuserdefdecm3;
    }

    public void setPubuserdefdecm3(BigDecimal pubuserdefdecm3) {
        this.pubuserdefdecm3 = pubuserdefdecm3;
    }

    public String getPubuserdefnvc4() {
        return pubuserdefnvc4;
    }

    public void setPubuserdefnvc4(String pubuserdefnvc4) {
        this.pubuserdefnvc4 = pubuserdefnvc4 == null ? null : pubuserdefnvc4.trim();
    }

    public BigDecimal getPubuserdefdecm4() {
        return pubuserdefdecm4;
    }

    public void setPubuserdefdecm4(BigDecimal pubuserdefdecm4) {
        this.pubuserdefdecm4 = pubuserdefdecm4;
    }

    public String getPubuserdefnvc5() {
        return pubuserdefnvc5;
    }

    public void setPubuserdefnvc5(String pubuserdefnvc5) {
        this.pubuserdefnvc5 = pubuserdefnvc5 == null ? null : pubuserdefnvc5.trim();
    }

    public BigDecimal getPubuserdefdecm5() {
        return pubuserdefdecm5;
    }

    public void setPubuserdefdecm5(BigDecimal pubuserdefdecm5) {
        this.pubuserdefdecm5 = pubuserdefdecm5;
    }

    public String getPubuserdefnvc6() {
        return pubuserdefnvc6;
    }

    public void setPubuserdefnvc6(String pubuserdefnvc6) {
        this.pubuserdefnvc6 = pubuserdefnvc6 == null ? null : pubuserdefnvc6.trim();
    }

    public BigDecimal getPubuserdefdecm6() {
        return pubuserdefdecm6;
    }

    public void setPubuserdefdecm6(BigDecimal pubuserdefdecm6) {
        this.pubuserdefdecm6 = pubuserdefdecm6;
    }

    public Byte getIsautogeneratesaleorderbom() {
        return isautogeneratesaleorderbom;
    }

    public void setIsautogeneratesaleorderbom(Byte isautogeneratesaleorderbom) {
        this.isautogeneratesaleorderbom = isautogeneratesaleorderbom;
    }

    public Byte getIsautogeneraterouting() {
        return isautogeneraterouting;
    }

    public void setIsautogeneraterouting(Byte isautogeneraterouting) {
        this.isautogeneraterouting = isautogeneraterouting;
    }

    public String getSourcevouchercode() {
        return sourcevouchercode;
    }

    public void setSourcevouchercode(String sourcevouchercode) {
        this.sourcevouchercode = sourcevouchercode == null ? null : sourcevouchercode.trim();
    }

    public String getIsnomodify() {
        return isnomodify;
    }

    public void setIsnomodify(String isnomodify) {
        this.isnomodify = isnomodify == null ? null : isnomodify.trim();
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer == null ? null : changer.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getMemberaddress() {
        return memberaddress;
    }

    public void setMemberaddress(String memberaddress) {
        this.memberaddress = memberaddress == null ? null : memberaddress.trim();
    }

    public Integer getPrintcount() {
        return printcount;
    }

    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
    }

    public Byte getIsseparatebywarehouse() {
        return isseparatebywarehouse;
    }

    public void setIsseparatebywarehouse(Byte isseparatebywarehouse) {
        this.isseparatebywarehouse = isseparatebywarehouse;
    }

    public BigDecimal getOrigreceiveamount() {
        return origreceiveamount;
    }

    public void setOrigreceiveamount(BigDecimal origreceiveamount) {
        this.origreceiveamount = origreceiveamount;
    }

    public BigDecimal getReceiveamount() {
        return receiveamount;
    }

    public void setReceiveamount(BigDecimal receiveamount) {
        this.receiveamount = receiveamount;
    }

    public String getCollaboratevouchercode() {
        return collaboratevouchercode;
    }

    public void setCollaboratevouchercode(String collaboratevouchercode) {
        this.collaboratevouchercode = collaboratevouchercode == null ? null : collaboratevouchercode.trim();
    }

    public String getExternalcode() {
        return externalcode;
    }

    public void setExternalcode(String externalcode) {
        this.externalcode = externalcode == null ? null : externalcode.trim();
    }

    public Integer getIdbusinesstype() {
        return idbusinesstype;
    }

    public void setIdbusinesstype(Integer idbusinesstype) {
        this.idbusinesstype = idbusinesstype;
    }

    public Integer getIdcurrency() {
        return idcurrency;
    }

    public void setIdcurrency(Integer idcurrency) {
        this.idcurrency = idcurrency;
    }

    public Integer getIddepartment() {
        return iddepartment;
    }

    public void setIddepartment(Integer iddepartment) {
        this.iddepartment = iddepartment;
    }

    public Integer getIdmember() {
        return idmember;
    }

    public void setIdmember(Integer idmember) {
        this.idmember = idmember;
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public Integer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Integer getIdsettlecustomer() {
        return idsettlecustomer;
    }

    public void setIdsettlecustomer(Integer idsettlecustomer) {
        this.idsettlecustomer = idsettlecustomer;
    }

    public Integer getIdclerk() {
        return idclerk;
    }

    public void setIdclerk(Integer idclerk) {
        this.idclerk = idclerk;
    }

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
    }

    public Integer getIdwarehouse() {
        return idwarehouse;
    }

    public void setIdwarehouse(Integer idwarehouse) {
        this.idwarehouse = idwarehouse;
    }

    public Integer getIdcollaborateupvouchertype() {
        return idcollaborateupvouchertype;
    }

    public void setIdcollaborateupvouchertype(Integer idcollaborateupvouchertype) {
        this.idcollaborateupvouchertype = idcollaborateupvouchertype;
    }

    public Integer getIdcollaborateupvoucher() {
        return idcollaborateupvoucher;
    }

    public void setIdcollaborateupvoucher(Integer idcollaborateupvoucher) {
        this.idcollaborateupvoucher = idcollaborateupvoucher;
    }

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    public Integer getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(Integer deliverymode) {
        this.deliverymode = deliverymode;
    }

    public Integer getIscancel() {
        return iscancel;
    }

    public void setIscancel(Integer iscancel) {
        this.iscancel = iscancel;
    }

    public Integer getIssaledelivery() {
        return issaledelivery;
    }

    public void setIssaledelivery(Integer issaledelivery) {
        this.issaledelivery = issaledelivery;
    }

    public Integer getIssaleout() {
        return issaleout;
    }

    public void setIssaleout(Integer issaleout) {
        this.issaleout = issaleout;
    }

    public Integer getRecivetype() {
        return recivetype;
    }

    public void setRecivetype(Integer recivetype) {
        this.recivetype = recivetype;
    }

    public Integer getVoucherstate() {
        return voucherstate;
    }

    public void setVoucherstate(Integer voucherstate) {
        this.voucherstate = voucherstate;
    }

    public Integer getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(Integer auditorid) {
        this.auditorid = auditorid;
    }

    public Integer getMakerid() {
        return makerid;
    }

    public void setMakerid(Integer makerid) {
        this.makerid = makerid;
    }

    public Integer getChangerid() {
        return changerid;
    }

    public void setChangerid(Integer changerid) {
        this.changerid = changerid;
    }

    public Integer getIdcollaboratedownvouchertype() {
        return idcollaboratedownvouchertype;
    }

    public void setIdcollaboratedownvouchertype(Integer idcollaboratedownvouchertype) {
        this.idcollaboratedownvouchertype = idcollaboratedownvouchertype;
    }

    public Integer getCollaboratestate() {
        return collaboratestate;
    }

    public void setCollaboratestate(Integer collaboratestate) {
        this.collaboratestate = collaboratestate;
    }

    public Integer getIdcollaboratedowndraftvoucher() {
        return idcollaboratedowndraftvoucher;
    }

    public void setIdcollaboratedowndraftvoucher(Integer idcollaboratedowndraftvoucher) {
        this.idcollaboratedowndraftvoucher = idcollaboratedowndraftvoucher;
    }

    public Integer getSourcevoucherid() {
        return sourcevoucherid;
    }

    public void setSourcevoucherid(Integer sourcevoucherid) {
        this.sourcevoucherid = sourcevoucherid;
    }

    public Integer getIdsourcevouchertype() {
        return idsourcevouchertype;
    }

    public void setIdsourcevouchertype(Integer idsourcevouchertype) {
        this.idsourcevouchertype = idsourcevouchertype;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getVoucherdate() {
        return voucherdate;
    }

    public void setVoucherdate(Date voucherdate) {
        this.voucherdate = voucherdate;
    }

    public Date getMadedate() {
        return madedate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public Date getAuditeddate() {
        return auditeddate;
    }

    public void setAuditeddate(Date auditeddate) {
        this.auditeddate = auditeddate;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public String getExternalvouchercode() {
        return externalvouchercode;
    }

    public void setExternalvouchercode(String externalvouchercode) {
        this.externalvouchercode = externalvouchercode == null ? null : externalvouchercode.trim();
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone == null ? null : customerphone.trim();
    }

    public Boolean getHasrecordcredit() {
        return hasrecordcredit;
    }

    public void setHasrecordcredit(Boolean hasrecordcredit) {
        this.hasrecordcredit = hasrecordcredit;
    }

    public Boolean getIsautowarehouse() {
        return isautowarehouse;
    }

    public void setIsautowarehouse(Boolean isautowarehouse) {
        this.isautowarehouse = isautowarehouse;
    }

    public Integer getInputdatasource() {
        return inputdatasource;
    }

    public void setInputdatasource(Integer inputdatasource) {
        this.inputdatasource = inputdatasource;
    }

    public String getCloser() {
        return closer;
    }

    public void setCloser(String closer) {
        this.closer = closer == null ? null : closer.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }

	public Integer getIdinventory() {
		return idinventory;
	}

	public void setIdinventory(Integer idinventory) {
		this.idinventory = idinventory;
	}

	public Integer getIdbaseunit() {
		return idbaseunit;
	}

	public void setIdbaseunit(Integer idbaseunit) {
		this.idbaseunit = idbaseunit;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getCompositionquantity() {
		return compositionquantity;
	}

	public void setCompositionquantity(String compositionquantity) {
		this.compositionquantity = compositionquantity;
	}
}