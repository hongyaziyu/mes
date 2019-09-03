<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
       <c:forEach items="${listShopDelivery}" var="item">     
        <div id="div_1" class="form-group used order">
         <table class="table table-hover">
         <tr>
                <td>生产工艺安排：<select  name="chejian1" class="big-input">
                    <option disabled selected style="display: none;" >--请选择--</option>
			      		<c:forEach items="${listShop}" var="shop">
						     <c:choose>
				      <c:when test="${item.shopName==shop.shopName}"><option value="${shop.shopName}" selected="selected">${shop.shopName}</option></c:when>
			          <c:otherwise ><option value="${shop.shopName}" >${shop.shopName}</option></c:otherwise>  </c:choose>                  
		               </c:forEach>
                </select></td>
                <td>预交数量：<input class="mini-input" name="jiaofu_num1" type="text" value="${item.deliveryNum}"/></td>
                
               <td class="hidden">预交日期：<div class="input-group date form_date "  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="jiaofu_date1" value="${item.deliveryDate}"/> 
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
              </div></td>
           </tr> 
           <tr>  
              <td>预计交付日期：<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="plan_finish_date1" value="${item.planFinishDate}"/> 
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div></td>
                
      	<td>实交数量：<input class="mini-input" name="shijiao_num1" type="text" value="${item.sendNum}"/></td>
                
                <td>实际交付日期：<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="shijiao_date1" value="${item.sendDate}"/> 
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
              </div></td>
              </tr>
            </table>
            <div class="pull-right juyou">
                <input  type="button" class="btn btn-default" onclick="jia(this)" value="插入"/>&nbsp;
      			<input  type="button" class="btn btn-default" onclick="jian(this)" value="删除"/>           
           	</div>
           </div>
         </c:forEach>        
           
     