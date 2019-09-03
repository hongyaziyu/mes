<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<title>跟踪单详细信息</title>

<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<%-- 隐藏次品类型 --%>

<style type="text/css">


#hide{display: none;}


.float-right {
	position: absolute;
}

.box {
	width: 50%;
	margin-top: 10%;
	margin: auto;
	padding: 28px;
	height: 450px;
	border: 1px #111 solid;
	display: none; /* 默认对话框隐藏 */
}
/* .box.show{display:block;}  */
.box .x {
	font-size: 18px;
	text-align: right;
	display: block;
}
</style>


</head>
<body>

	<div class="container">
		<h4>跟踪单详细信息</h4>
		<form id="form" class="form-inline" method="post" action="${pageContext.request.contextPath}/record/editTrackCard.action">

			<input class="btn btn-primary" type="hidden" name="cardId" value="${trackCard.cardId}" />
			<table class="table table-hover">
				<tr>
					<td><label for="planNo">客户代码:</label>
					</td>
					<td><input type="text" class="form-control" name="client"
						value="${trackCard.client}" />
					</td>
					<td><label for="planNo">计划单号:</label>
					</td>
					<td><input type="text" class="form-control" name="planNo"
						value="${trackCard.planNo}" />
					</td>
					<td><label for="clientMaterialNo">图号:</label>
					</td>
					<td><input type="text" class="form-control"
						name="clientMaterialNo" value="${trackCard.clientMaterialNo}" />
					</td>
					<td><label for="materialNo">物料号:</label>
					</td>
					<td><input type="text" class="form-control" name="materialNo"
						value="${trackCard.materialNo}" />
					</td>
				</tr>
				<tr>
					<td><label for="batchNo">批次号:</label>
					</td>
					<td><input type="text" class="form-control" name="batchNo"
						value="${trackCard.batchNo}" />
					</td>
					<td><label for="materialName">产品名称:</label>
					</td>
					<td><input type="text" class="form-control"
						name="materialName" value="${trackCard.materialName}" />
					</td>
					<td><label for="materialName">产品规格:</label>
					</td>
					<td><input type="text" class="form-control" name="productSpec"
						value="${trackCard.productSpec}" />
					</td>
					<td><label for="productionOrder">计划数量:</label>
					</td>
					<td><input type="text" class="form-control"
						name="productionOrder" value="${trackCard.productionOrder}" />
					</td>

				</tr>
				<tr>
					<td><label for="lupihao">材料炉批号:</label>
					</td>
					<td><input type="text" class="form-control" name="lupihao"
						value="${trackCard.lupihao}" />
					</td>
					<td><label for="productNo">产品号:</label>
					</td>
					<td><input type="text" class="form-control" name="productNo"
						value="${trackCard.productNo}" />
					</td>
					<td><label for="productDes">产品描述:</label>
					</td>
					<td><input type="text" class="form-control" name="productDes"
						value="${trackCard.productDes}" />
					</td>
					<td><label for="productFig">产品图号:</label>
					</td>
					<td><input type="text" class="form-control" name="productFig"
						value="${trackCard.productFig}" />
					</td>

				</tr>
				
				<tr>
				<%--<td><input type="text" class="form-control"
						name="materialSpec" value="${trackCard.materialSpec}" />
					</td> --%>
					<td><label for="materialBatchNo" >材料牌号规格/材料牌号规格:</label>
					</td>
					<td><input type="text" style="width:180px" id="cailiaopicihao"
						class="form-control" name="materialBatchNo"
						value="${trackCard.materialBatchNo}" />
					</td>
					<td><span id="open" class="glyphicon glyphicon-pencil"></span></td>
					
				</tr>
			</table>
			<div id='inputbox' class="box">
				<a class='x' href='' ; id="close">关闭</a>
				<div>
					<div class="add">
						<span>组件1</span><input type="text" id="del-text1"
							class="form-control" name="text1" value=""style="width:330px"> <a
							id="addTextImput" href="#">添加</a>&nbsp;&nbsp; <a class="del-text"
							href='#'>删除</a> <br>
						<br>
						<div id="main"></div>
					</div>
				</div>
				<br> <input id="submit" type="button" value="确定">
			</div>

<h4 class="navbar-left">工作记录单</h4>
<br/><br/>
<br/>
			<div class="container">
				<!--varStatus="stauts"标号循环，stauts.index 起始为0，从0开始递增 -->
           
				<c:forEach items="${listWorkCard}" var="a" varStatus="stauts">
				
				<!-- 这个div显示按钮的负责展开具体的内容 -->
			<div style="width:1210px"><input type="button"  style="width:1100px;background-color:#F0F8FF;"
				 value="工段:${a.shopName}      工序:${a.processName}      操作工:${a.operator}     合格数:${a.hegeNum}      次品总数:${a.totalCipinNum}        (点击查看详情)" onclick="show(this)"></div>	
				<br/>
				<!-- 下面一个div负责默认时把具体内容隐藏起来 -->
				<div class="hidden"> 
					<input class="btn btn-primary" type="hidden"
						name="trackId${stauts.index+1}" value="${a.trackId}" />
					<input class="btn btn-primary" type="hidden"
						name="cardId${stauts.index+1}" value="${a.cardId}" />

					<table class="table table-hover used">
						<tr>
							<td><label for="shopName">车间名称:</label>
							</td>
							<td><input type="text" class="form-control"
								name="shopName${stauts.index+1}" value="${a.shopName}" />
							</td>
							<td><label for="processName">工序名称:</label>
							</td>
							<td><input type="text" class="form-control"
								name="processName${stauts.index+1}" value="${a.processName}" />
							</td>
							<td><label for="operator">操作工:</label>
							</td>
							<td><input type="text" class="form-control"
								name="operator${stauts.index+1}" value="${a.operator}" />
							</td>
							<td><label for="asset">设备:</label>
							</td>
							<td><input type="text" class="form-control"
								name="asset${stauts.index+1}" value="${a.asset}" />
							</td>
						</tr>
						<tr>
							<td><label for="totalNum">总数:</label>
							</td>
							<td><input type="text" class="form-control"
								name="totalNum${stauts.index+1}" value="${a.totalNum}" />
							</td>
							<td><label for="hegeNum">合格数:</label>
							</td>
							<td><input type="text" class="form-control"
								name="hegeNum${stauts.index+1}" value="${a.hegeNum}" />
							</td>
							<td><label for="hegeNum">次品总数:</label>
							</td>
							<td><input type="text" class="form-control"
								name="totalCipinNum${stauts.index+1}" value="${a.totalCipinNum}" />
							</td>
						</tr>
						<tr>
							<td><label for="produceDate">生产日期:</label>
							</td>
							<td><div class="input-group date form_date"
									data-date-format="yyyy-mm-dd">
									<input class="form-control" size="16" type="text"
										name="produceDate${stauts.index+1}" value="${a.produceDate}" />
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span> </span>
								</div>
							</td>
							<td><label for="price">工价:</label>
							</td>
							<td>
							<c:if test="${(!empty a.price)&&(a.price!=' ')}">
								<c:choose>
									<c:when test="${user.position=='车间主任'}">
										<input type="text" class="form-control" name="price${stauts.index+1}"  value="${a.price}" />
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" name="price${stauts.index+1}" readonly value="${a.price}"/>
									</c:otherwise>
								</c:choose>
								
							</c:if>
							<c:if test="${(empty a.price)||(a.price==' ')}">
								<input type="text" class="form-control" name="price${stauts.index+1}"  readonly value="${a.price}"/>
							</c:if>
							</td>
						</tr>
				
						<c:forEach items="${a.cipin}" var="b" varStatus="stauts2">
							
							<tr>
								<td><label for="cipinType1">次品类型${stauts2.index+1}:</label>
								</td>
								<td><input type="text" class="form-control"
									name="${stauts.index+1}cipinType${stauts2.index+1}"
									value="${b.cipinType}" />
								</td>
								<td><label for="cipinSpecies1">次品种类${stauts2.index+1}:</label>
								</td>
								<td><input type="text" class="form-control"
									name="${stauts.index+1}cipinSpecies${stauts2.index+1}"
									value="${b.cipinSpecies}" />
								</td>
								<td><label for="cipinNum1">次品数量${stauts2.index+1}:</label>
								</td>
								<td><input type="text" class="form-control"
									name="${stauts.index+1}cipinNum${stauts2.index+1}"
									value="${b.cipinNum}" />
								</td>
							</tr>
							<input class="btn btn-primary" type="hidden"
								name="${stauts.index+1}cipinId${stauts2.index+1}"
								value="${b.cipinId}" />

							<input class="btn btn-primary getnum${stauts.index+2}"
								name="getnum${stauts.index+2}" type="hidden" value="" />
						</c:forEach>
					</table>
					<input class="btn btn-primary" id="num${stauts.index+2}"
						name="num${stauts.index+2}" type="hidden" value="" />
					
					<p>------------------------------------------------------------------------------
						---------------------------------------------------------------------------------
						---------------------------</p>
				</div>
				</c:forEach>
             
			</div>

			<div align="right" id="sub">
				<input class="btn btn-primary" id="num1" name="num1" type="hidden"/> 
				<input class="btn btn-primary" id="tj" type="submit" value="提交" />
			</div>

		</form>
	</div>


	<script type="text/javascript">
	
	
	$(function(){
			  var len;
					  
				//弹出窗口
		        $("#open").click(function(){
		         document.getElementById("inputbox").style.display=1?"block":"none";     /* 点击按钮打开对话框 */
		          var str=$("#cailiaopicihao").val();	          
		             var sArr = new Array(); // 定义一个空数组
		            var sArr = str.split(",");
		           // alert(sArr[1]);
		           //修改内容回显
		           $("#del-text1").val(sArr[0]);
		           len=sArr.length;
		           
		           for (var i = 1; i < sArr.length; i++) {        
		            $("#main").append("<div><span>组件"+(i+1)+"</span><input type='text' name='text"+(i+1)+"' id='del-text"+(i+1)+"'  class='form-control' value='"+sArr[i]+"'/><br><br></div>");
		       }		       
		        });
		        
		        //关闭窗口
		       $("#close").click(function(){
		            document.getElementById('inputbox').style.display=0?'block':'none'; /* 点击按钮关闭对话框 */
		    });
		    
		    //修改后的内容回显
				$("#submit").click(function(){
				    var numArr = []; // 定义一个空数组
		        var txt = $('#inputbox').find(':text'); // 获取所有文本框
		        for (var i = 0; i < txt.length; i++) {
		            numArr.push(txt.eq(i).val()); // 将文本框的值添加到数组中
		        }     
		     // console.info(numArr);	
		        var data="";
		        for (var i = 0; i < numArr.length; i++) {     
		           if(i < numArr.length-1){
		            data+=numArr[i]+","; 
		           }else{
		           data+=numArr[i];
		           }      
		       }
		      $("#cailiaopicihao").val( data);
				 document.getElementById('inputbox').style.display=0?'block':'none';
				 $(".add").parent().find(":text").val ("");
				 	 $("#main").empty(); 
				});
						
					
			//添加文本框
				var str1=$("#cailiaopicihao").val();	          
		        var sArr1 = new Array(); // 定义一个空数组
		        var sArr1 = str1.split(",");
		        len=sArr1.length;			   
				var m=len+1;
				//alert(m);
				$("#addTextImput").click(function(){	
								
				if(m < 7) {
				$("#main").append("<div><span>组件"+m+"</span><input type='text' name='text"+m+"' id='del-text"+m+"'  class='form-control' value=''/><br><br></div>");
				m++;
				
				//alert(i);
				} else {
				alert("最多能添加6个");
				}
				});
		 //删除文本框
			$(".del-text").click(function(){
    			var x = 0;  			
    			x = $("#main").find("div").length;
    			//alert(x);
    			if(x>0){
    				$("#main").find("div").last().remove();
    				m--;
    			}
    			else{
    				alert("已是最后一个了！");
    			}
    		});
				
		
			$('.date').datetimepicker({
				language : 'zh-CN',
				weekStart : 0,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 3,
				minView : 2,
				forceParse : 0
			});

			$("#add").click(
							function() {
								var x = 0;
								x = $("#add").parent().parent().parent().find(
										"tr").length - 2;
								console.log(x);
								var str = "<tr><td><label for='cipinType"+x+"'>次品类型：</label></td><td><input type='text' class='form-control' name='cipinType"+x+"' value=''/></td><td><label for='cipinSpecies"+x+"'>次品种类：</label></td><td><input type='text' class='form-control' name='cipinSpecies"+x+"' value=''/></td><td><label for='cipinNum"+x+"'>次品数量：</label></td><td><input type='text' class='form-control' name='cipinNum"+x+"' value=''/></td></tr>";
								$("#add").parent().parent().parent()
										.append(str);

							});

			$("#del").click(function() {
								var a = $("#del").parent().parent().parent()
										.find("tr").length;
								console.log(a);
								if (a > 4) {
									$("#del").parent().parent().parent().find(
											"tr").last().remove();
								} else {
									alert("已是最后一项！");
								}

							});

		

		$("#tj").click(function() {
			var i = 1;
			//对class为used的table表进行循环，循环i次也就获得有几个2表，把值给num1
			$(".used").each(function() {
				$("#num1").val(i++);
				var j = 1;
				//对class为getnum i的进行循环，循环j次也就获得几个3表给num为第i的2表，（例如num2为2表的第一部分的次品的num值，循环j次也就给num2，num3为2表的第二部分的次品的num值，循环j次也就给num3）
				$(".getnum" + i).each(function() {
					//对id为num i的信息赋给j值
					$("#num" + i).val(j++);
				});
			});

			$("#form").submit();
         
		});
	});	
	//负责将上面显示的<div></div>中的class的hidden移除和显示
	function show(obj){
			
				var show = $(obj).parent().next().next();
				
				//切换样式（有则删除，没有则添加）
				show.toggleClass("hidden");
					
	}
	
	</script>

</body>
</html>

