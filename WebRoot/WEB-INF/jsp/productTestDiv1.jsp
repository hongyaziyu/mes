<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

	<c:forEach items="${listProductTest}" var="productTest">
	<script type="text/javascript" >	
	$(function(){
		$('.date').datetimepicker({
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 0,
			format: 'yyyy-mm-dd hh:ii',
			minuteStep:1,
			forceParse: 0
		});
				
	});	
	</script>
	
	<div  class="used">	   
	   <h5>详细记录</h5>
             <table class="table table-hover" style="table-layout: fixed;">
	
        <tr>
              <td style="width: 180px;">检测日期：<div class="input-group date form_date"  data-date-format="yyyy-mm-dd hh:ii">
                        <input  size="16" type="text" name="produce_date" value="${productTest.checkDate}"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
               		     </div></td>
               <c:set var="string1" value="${productTest.processName}"/>
               <c:set var="string2" value="${fn:split(string1, '/')}" />		    
              <td style="width: 180px;">检测工序：<input   name="gongxu" type="text" value="${string2[0]}"/> </td>
              <td style="width: 180px;">特征值：<input   name="tezheng" type="text" value="${string2[1]}"/></td>
              <c:set var="string3" value="${productTest.standardVal}"/>
               <c:set var="string4" value="${fn:split(string3, '+')}" />
               <c:set var="string5" value="${string4[1]}"/>
               <c:set var="string6" value="${fn:split(string5, '/-')}" />
              <td style="width: 200px;">
              		标准值：<br />
              		<input   style="width:50px;" name="biaozhunzhi" type="text" value="${string4[0]}"/>
              		+<input   style="width:40px;margin-left:10px" name="biaozhunzhiPlus" type="text" value="${string6[0]}"/>
              		-<input   style="width:40px;margin-left:10px" name="biaozhunzhiMinus" type="text" value="${string6[1]}"/>
              </td>
              
              <td style="width: 180px;">测量值1：<input  name="clz1" type="text" value="${productTest.testVal1}"/></td>
              <td style="width: 180px;">测量值2：<input  name="clz2" type="text" value="${productTest.testVal2}"/></td>
              
        </tr> 
        <tr> 
              <td>测量值3：<input   name="clz3" type="text" value="${productTest.testVal3}"/></td>
              <td>测量值4：<input   name="clz4" type="text" value="${productTest.testVal4}"/></td>    
              <td>测量值5：<input   name="clz5" type="text" value="${productTest.testVal5}"/></td>
              <td>操作工：<input  name="caozuogong" type="text" value="${productTest.operator}"/></td>
              <td>批号：<input   name="pihao" type="text" value="${productTest.batchNumber}"/></td>
              <td>判定：<input   name="panding" type="text" value="${productTest.isOk}"/>
              		  <input   name="processId" type="hidden" value="${productTest.processId}"/>
              </td>           
             <td> <input  type="button" class="btn btn-default" onclick="jia(this)" value="插入"/>
     		 <input  type="button" class="btn btn-default" onclick="jian(this)" value="删除"/></td>
        </tr>
          </table> 
        
      </div>         
       </c:forEach>
   