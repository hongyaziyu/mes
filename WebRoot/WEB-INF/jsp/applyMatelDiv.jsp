<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

   
	<div id="div_0" class="hidden"> 
	 
	       
                      材料名称（及其规格）： <input  type="text" name="cailiaoMc0" class="mini-input" value=""/> 
                       材料编号：<input class="mini-input" name="cailiaoBh0" type="text" value=""/> 
                       实际用量：<input class="mini-input" name="materialNum" type="text"  value=""/> 
                       理论值参考<input class="mini-input" name="referNum" type="text"  value=""/>    
                       单位：<input class="mini-input" name="unit" type="text" value=""/>
      <br/><br/> 
         <!--    备注：<input class="mini-input" name="remark" readonly type="text" value=""/> -->
              库存数量：<input class="mini-input" name="inventory" readonly type="text" value=""/>
            <button  type="button" class="btn btn-default" onclick="jia(this)">插入</button>&nbsp;
      		<button  type="button" class="btn btn-default" onclick="jian(this)">删除</button>  
   
     </div>
     
    
   <c:forEach items="${listZijian}" var="item">
   <!-- listProductInventory显示库存数量，两个foreach并列使用，非嵌套 -->
  <%--  <c:forEach items="${listProductInventory}" var="a"> --%>
     <div id="div_1" class="used">
        
                                  材料名称：<input  type="text" class="mini-input" name="cailiaoMc"   value="${item.cailiaoMc}"/>  
                                   材料编号：<input type="text"  class="mini-input" style="width:120px"  name="cailiaoBh"  value="${item.cailiaoBh}"/> 
                                   实际用量：  <input type="text" style="width:80px" class="mini-input"  name="materialNum"  type="text" value=""/>                   
                                  理论值参考<input class="mini-input" readonly="readonly" style="width:80px" name="referNum" type="text"  value="${item.referNum}"/>                    
                                   单位：<input  type="text" style="width:50px" readonly="readonly" class="mini-input" name="unit"  value="${item.unit}"/>                                     
            <!--     备注：<input  name="remark" style="width:120px" class="mini-input" readonly type="text" value=""/>  -->                
                                 库存数量：<input class="mini-input" name="inventory" style="width:90px" readonly type="text" <%-- value="${a.productNum}" --%>value="${item.remark}"/>  
              
                <button  type="button" class="btn btn-primary" onclick="jia(this)">插入</button>&nbsp;
      			<button  type="button" class="btn btn-primary" onclick="jian(this)">删除</button>   
         <br /><br />   
     </div> 
    <%--  </c:forEach>  --%>
     </c:forEach> 