package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class StRdrecord {
    private Integer id;

    private String code;

    private String dispatchaddress;

    private String contact;

    private String contactphone;

    private String sourcevouchercode;

    private String saleordercode;

    private String purchasearrivalcode;

    private String productreceivecode;

    private String saledeliverycode;

    private Integer printtime;

    private BigDecimal amount;

    private Byte rddirectionflag;

    private Byte iscostaccount;

    private Byte ismergedflow;

    private Byte isautogenerate;

    private String memo;

    private String isnomodify;

    private String maker;

    private String auditor;

    private String reviser;

    private Byte iscarriedforwardout;

    private Byte iscarriedforwardin;

    private Byte ismodifiedcode;

    private Integer accountingperiod;

    private String docid;

    private Integer accountingyear;

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

    private Integer voucheryear;

    private Integer voucherperiod;

    private Date sourcevoucherdate;

    private BigDecimal exchangerate;

    private String manufactureordercode;

    private String delegatedispatchcode;

    private String beforeupgrade;

    private BigDecimal totalorigtaxamount;

    private BigDecimal totaltaxamount;

    private Integer printcount;

    private String mobilephone;

    private String address;

    private String externalcode;

    private Integer idbusitype;

    private Integer idcurrency;

    private Integer iddepartment;

    private Integer iddistrict;

    private Integer idmember;

    private Integer idstore;

    private Integer idmarketingorgan;

    private Integer idpartner;

    private Integer idsettlecustomer;

    private Integer idclerk;

    private Integer idqualityinspector;

    private Integer idproject;

    private Integer idrdstyle;

    private Integer idinwarehouse;

    private Integer idwarehouse;

    private Integer idcollaborateupvouchertype;

    private Integer idcollaborateupvoucher;

    private Integer accountstate;

    private Integer deliverystate;

    private Integer settlestatus;

    private Integer transporttype;

    private Integer voucherstate;

    private Integer auditorid;

    private Integer makerid;

    private Integer sourcevoucherid;

    private Integer purchasearrivalid;

    private Integer saledeliveryid;

    private Integer saleorderid;

    private Integer idsourcevouchertype;

    private Integer idvouchertype;

    private Integer productreceiveid;

    private Date maturitydate;

    private Date voucherdate;

    private Date madedate;

    private Date auditeddate;

    private Date createdtime;

    private Date updated;

    private Integer datasource;

    private Byte iscostaccounted;

    private String customerphone;

    private Boolean hasrecordcredit;

    private Integer inputdatasource;

    private Date accounttime;

    private String purchaseordercode;

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

    public String getDispatchaddress() {
        return dispatchaddress;
    }

    public void setDispatchaddress(String dispatchaddress) {
        this.dispatchaddress = dispatchaddress == null ? null : dispatchaddress.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getSourcevouchercode() {
        return sourcevouchercode;
    }

    public void setSourcevouchercode(String sourcevouchercode) {
        this.sourcevouchercode = sourcevouchercode == null ? null : sourcevouchercode.trim();
    }

    public String getSaleordercode() {
        return saleordercode;
    }

    public void setSaleordercode(String saleordercode) {
        this.saleordercode = saleordercode == null ? null : saleordercode.trim();
    }

    public String getPurchasearrivalcode() {
        return purchasearrivalcode;
    }

    public void setPurchasearrivalcode(String purchasearrivalcode) {
        this.purchasearrivalcode = purchasearrivalcode == null ? null : purchasearrivalcode.trim();
    }

    public String getProductreceivecode() {
        return productreceivecode;
    }

    public void setProductreceivecode(String productreceivecode) {
        this.productreceivecode = productreceivecode == null ? null : productreceivecode.trim();
    }

    public String getSaledeliverycode() {
        return saledeliverycode;
    }

    public void setSaledeliverycode(String saledeliverycode) {
        this.saledeliverycode = saledeliverycode == null ? null : saledeliverycode.trim();
    }

    public Integer getPrinttime() {
        return printtime;
    }

    public void setPrinttime(Integer printtime) {
        this.printtime = printtime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getRddirectionflag() {
        return rddirectionflag;
    }

    public void setRddirectionflag(Byte rddirectionflag) {
        this.rddirectionflag = rddirectionflag;
    }

    public Byte getIscostaccount() {
        return iscostaccount;
    }

    public void setIscostaccount(Byte iscostaccount) {
        this.iscostaccount = iscostaccount;
    }

    public Byte getIsmergedflow() {
        return ismergedflow;
    }

    public void setIsmergedflow(Byte ismergedflow) {
        this.ismergedflow = ismergedflow;
    }

    public Byte getIsautogenerate() {
        return isautogenerate;
    }

    public void setIsautogenerate(Byte isautogenerate) {
        this.isautogenerate = isautogenerate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getIsnomodify() {
        return isnomodify;
    }

    public void setIsnomodify(String isnomodify) {
        this.isnomodify = isnomodify == null ? null : isnomodify.trim();
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

    public Integer getAccountingperiod() {
        return accountingperiod;
    }

    public void setAccountingperiod(Integer accountingperiod) {
        this.accountingperiod = accountingperiod;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid == null ? null : docid.trim();
    }

    public Integer getAccountingyear() {
        return accountingyear;
    }

    public void setAccountingyear(Integer accountingyear) {
        this.accountingyear = accountingyear;
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

    public Integer getVoucheryear() {
        return voucheryear;
    }

    public void setVoucheryear(Integer voucheryear) {
        this.voucheryear = voucheryear;
    }

    public Integer getVoucherperiod() {
        return voucherperiod;
    }

    public void setVoucherperiod(Integer voucherperiod) {
        this.voucherperiod = voucherperiod;
    }

    public Date getSourcevoucherdate() {
        return sourcevoucherdate;
    }

    public void setSourcevoucherdate(Date sourcevoucherdate) {
        this.sourcevoucherdate = sourcevoucherdate;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getManufactureordercode() {
        return manufactureordercode;
    }

    public void setManufactureordercode(String manufactureordercode) {
        this.manufactureordercode = manufactureordercode == null ? null : manufactureordercode.trim();
    }

    public String getDelegatedispatchcode() {
        return delegatedispatchcode;
    }

    public void setDelegatedispatchcode(String delegatedispatchcode) {
        this.delegatedispatchcode = delegatedispatchcode == null ? null : delegatedispatchcode.trim();
    }

    public String getBeforeupgrade() {
        return beforeupgrade;
    }

    public void setBeforeupgrade(String beforeupgrade) {
        this.beforeupgrade = beforeupgrade == null ? null : beforeupgrade.trim();
    }

    public BigDecimal getTotalorigtaxamount() {
        return totalorigtaxamount;
    }

    public void setTotalorigtaxamount(BigDecimal totalorigtaxamount) {
        this.totalorigtaxamount = totalorigtaxamount;
    }

    public BigDecimal getTotaltaxamount() {
        return totaltaxamount;
    }

    public void setTotaltaxamount(BigDecimal totaltaxamount) {
        this.totaltaxamount = totaltaxamount;
    }

    public Integer getPrintcount() {
        return printcount;
    }

    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getExternalcode() {
        return externalcode;
    }

    public void setExternalcode(String externalcode) {
        this.externalcode = externalcode == null ? null : externalcode.trim();
    }

    public Integer getIdbusitype() {
        return idbusitype;
    }

    public void setIdbusitype(Integer idbusitype) {
        this.idbusitype = idbusitype;
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

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Integer getIdmember() {
        return idmember;
    }

    public void setIdmember(Integer idmember) {
        this.idmember = idmember;
    }

    public Integer getIdstore() {
        return idstore;
    }

    public void setIdstore(Integer idstore) {
        this.idstore = idstore;
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public Integer getIdpartner() {
        return idpartner;
    }

    public void setIdpartner(Integer idpartner) {
        this.idpartner = idpartner;
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

    public Integer getIdqualityinspector() {
        return idqualityinspector;
    }

    public void setIdqualityinspector(Integer idqualityinspector) {
        this.idqualityinspector = idqualityinspector;
    }

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
    }

    public Integer getIdrdstyle() {
        return idrdstyle;
    }

    public void setIdrdstyle(Integer idrdstyle) {
        this.idrdstyle = idrdstyle;
    }

    public Integer getIdinwarehouse() {
        return idinwarehouse;
    }

    public void setIdinwarehouse(Integer idinwarehouse) {
        this.idinwarehouse = idinwarehouse;
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

    public Integer getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(Integer accountstate) {
        this.accountstate = accountstate;
    }

    public Integer getDeliverystate() {
        return deliverystate;
    }

    public void setDeliverystate(Integer deliverystate) {
        this.deliverystate = deliverystate;
    }

    public Integer getSettlestatus() {
        return settlestatus;
    }

    public void setSettlestatus(Integer settlestatus) {
        this.settlestatus = settlestatus;
    }

    public Integer getTransporttype() {
        return transporttype;
    }

    public void setTransporttype(Integer transporttype) {
        this.transporttype = transporttype;
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

    public Integer getSourcevoucherid() {
        return sourcevoucherid;
    }

    public void setSourcevoucherid(Integer sourcevoucherid) {
        this.sourcevoucherid = sourcevoucherid;
    }

    public Integer getPurchasearrivalid() {
        return purchasearrivalid;
    }

    public void setPurchasearrivalid(Integer purchasearrivalid) {
        this.purchasearrivalid = purchasearrivalid;
    }

    public Integer getSaledeliveryid() {
        return saledeliveryid;
    }

    public void setSaledeliveryid(Integer saledeliveryid) {
        this.saledeliveryid = saledeliveryid;
    }

    public Integer getSaleorderid() {
        return saleorderid;
    }

    public void setSaleorderid(Integer saleorderid) {
        this.saleorderid = saleorderid;
    }

    public Integer getIdsourcevouchertype() {
        return idsourcevouchertype;
    }

    public void setIdsourcevouchertype(Integer idsourcevouchertype) {
        this.idsourcevouchertype = idsourcevouchertype;
    }

    public Integer getIdvouchertype() {
        return idvouchertype;
    }

    public void setIdvouchertype(Integer idvouchertype) {
        this.idvouchertype = idvouchertype;
    }

    public Integer getProductreceiveid() {
        return productreceiveid;
    }

    public void setProductreceiveid(Integer productreceiveid) {
        this.productreceiveid = productreceiveid;
    }

    public Date getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(Date maturitydate) {
        this.maturitydate = maturitydate;
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

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    public Byte getIscostaccounted() {
        return iscostaccounted;
    }

    public void setIscostaccounted(Byte iscostaccounted) {
        this.iscostaccounted = iscostaccounted;
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

    public Integer getInputdatasource() {
        return inputdatasource;
    }

    public void setInputdatasource(Integer inputdatasource) {
        this.inputdatasource = inputdatasource;
    }

    public Date getAccounttime() {
        return accounttime;
    }

    public void setAccounttime(Date accounttime) {
        this.accounttime = accounttime;
    }

    public String getPurchaseordercode() {
        return purchaseordercode;
    }

    public void setPurchaseordercode(String purchaseordercode) {
        this.purchaseordercode = purchaseordercode == null ? null : purchaseordercode.trim();
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}