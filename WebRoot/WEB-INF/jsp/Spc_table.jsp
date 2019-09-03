<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
     <div id="table" class="table-responsive">
       <table  class="easyui-datagrid" title="日期-时间" style="width:1140px;height:325px"  
			>
		<thead>
			<tr>
				<th data-options="field:'itemid',width:60,align:'center'"></th>
			    <th data-options="field:'1',width:43,align:'center'" class="center-block">1</th>
				<th data-options="field:'2',width:43,align:'center'" class="center-block">2</th>
				<th data-options="field:'3',width:43,align:'center'" class="center-block">3</th>
				<th data-options="field:'4',width:43,align:'center'" class="center-block">4</th>
				<th data-options="field:'5',width:43,align:'center'" class="center-block">5</th>
				<th data-options="field:'6',width:43,align:'center'" class="center-block">6</th>
				<th data-options="field:'7',width:43,align:'center'" class="center-block">7</th>
				<th data-options="field:'8',width:43,align:'center'" class="center-block">8</th>
				<th data-options="field:'9',width:43,align:'center'" class="center-block">9</th>
				<th data-options="field:'10',width:43,align:'center'" class="center-block">10</th>
				<th data-options="field:'11',width:43,align:'center'" class="center-block">11</th>
				<th data-options="field:'12',width:43,align:'center'" class="center-block">12</th>
				<th data-options="field:'13',width:43,align:'center'" class="center-block">13</th>
				<th data-options="field:'14',width:43,align:'center'" class="center-block">14</th>
				<th data-options="field:'15',width:43,align:'center'" class="center-block">15</th>
				<th data-options="field:'16',width:43,align:'center'" class="center-block">16</th>
				<th data-options="field:'17',width:43,align:'center'" class="center-block">17</th>
				<th data-options="field:'18',width:43,align:'center'" class="center-block">18</th>
				<th data-options="field:'19',width:43,align:'center'" class="center-block">19</th>
				<th data-options="field:'20',width:43,align:'center'" class="center-block">20</th>
				<th data-options="field:'21',width:43,align:'center'" class="center-block">21</th>
				<th data-options="field:'22',width:43,align:'center'" class="center-block">22</th>
				<th data-options="field:'23',width:43,align:'center'" class="center-block">23</th>
				<th data-options="field:'24',width:43,align:'center'" class="center-block">24</th>
				<th data-options="field:'25',width:43,align:'center'" class="center-block">25</th> 
			
			</tr>
		</thead>
		<tbody>
		<tr>
			    <td>1</td>
				<td><font size="1">${listValue[0]}</font></td>
				<td><font size="1">${listValue[8]}</font></td>
				<td><font size="1">${listValue[16]}</font></td>
				<td><font size="1">${listValue[24]}</font></td>
				<td><font size="1">${listValue[32]}</font></td>
				<td><font size="1">${listValue[40]}</font></td>
				<td><font size="1">${listValue[48]}</font></td>
				<td><font size="1">${listValue[56]}</font></td>
				<td><font size="1">${listValue[64]}</font></td>
				<td><font size="1">${listValue[72]}</font></td>
				<td><font size="1">${listValue[80]}</font></td>
				<td><font size="1">${listValue[88]}</font></td>
				<td><font size="1">${listValue[96]}</font></td>
				<td><font size="1">${listValue[104]}</font></td>
				<td><font size="1">${listValue[112]}</font></td>
				<td><font size="1">${listValue[120]}</font></td>
				<td><font size="1">${listValue[128]}</font></td>
				<td><font size="1">${listValue[136]}</font></td>
				<td><font size="1">${listValue[144]}</font></td>
				<td><font size="1">${listValue[152]}</font></td>
				<td><font size="1">${listValue[160]}</font></td>
				<td><font size="1">${listValue[168]}</font></td>
				<td><font size="1">${listValue[176]}</font></td>
				<td><font size="1">${listValue[184]}</font></td>
				<td><font size="1">${listValue[192]}</font></td>
		</tr>
		<tr>
			    <td>2</td>
				<td><font size="1">${listValue[1]}</font></td>
				<td><font size="1">${listValue[9]}</font></td>
				<td><font size="1">${listValue[17]}</font></td>
				<td><font size="1">${listValue[25]}</font></td>
				<td><font size="1">${listValue[33]}</font></td>
				<td><font size="1">${listValue[41]}</font></td>
				<td><font size="1">${listValue[49]}</font></td>
				<td><font size="1">${listValue[57]}</font></td>
				<td><font size="1">${listValue[65]}</font></td>
				<td><font size="1">${listValue[73]}</font></td>
				<td><font size="1">${listValue[81]}</font></td>
				<td><font size="1">${listValue[89]}</font></td>
				<td><font size="1">${listValue[97]}</font></td>
				<td><font size="1">${listValue[105]}</font></td>
				<td><font size="1">${listValue[113]}</font></td>
				<td><font size="1">${listValue[121]}</font></td>
				<td><font size="1">${listValue[129]}</font></td>
				<td><font size="1">${listValue[137]}</font></td>
				<td><font size="1">${listValue[145]}</font></td>
				<td><font size="1">${listValue[153]}</font></td>
				<td><font size="1">${listValue[161]}</font></td>
				<td><font size="1">${listValue[169]}</font></td>
				<td><font size="1">${listValue[177]}</font></td>
				<td><font size="1">${listValue[185]}</font></td>
				<td><font size="1">${listValue[193]}</font></td>
		</tr>
		<tr>
			    <td>3</td>
				<td><font size="1">${listValue[2]}</font></td>
				<td><font size="1">${listValue[10]}</font></td>
				<td><font size="1">${listValue[18]}</font></td>
				<td><font size="1">${listValue[26]}</font></td>
				<td><font size="1">${listValue[34]}</font></td>
				<td><font size="1">${listValue[42]}</font></td>
				<td><font size="1">${listValue[50]}</font></td>
				<td><font size="1">${listValue[58]}</font></td>
				<td><font size="1">${listValue[66]}</font></td>
				<td><font size="1">${listValue[74]}</font></td>
				<td><font size="1">${listValue[82]}</font></td>
				<td><font size="1">${listValue[90]}</font></td>
				<td><font size="1">${listValue[98]}</font></td>
				<td><font size="1">${listValue[106]}</font></td>
				<td><font size="1">${listValue[114]}</font></td>
				<td><font size="1">${listValue[122]}</font></td>
				<td><font size="1">${listValue[130]}</font></td>
				<td><font size="1">${listValue[138]}</font></td>
				<td><font size="1">${listValue[146]}</font></td>
				<td><font size="1">${listValue[154]}</font></td>
				<td><font size="1">${listValue[162]}</font></td>
				<td><font size="1">${listValue[170]}</font></td>
				<td><font size="1">${listValue[178]}</font></td>
				<td><font size="1">${listValue[186]}</font></td>
				<td><font size="1">${listValue[194]}</font></td>
		</tr>
		<tr>
			    <td>4</td>
				<td><font size="1">${listValue[3]}</font></td>
				<td><font size="1">${listValue[11]}</font></td>
				<td><font size="1">${listValue[19]}</font></td>
				<td><font size="1">${listValue[27]}</font></td>
				<td><font size="1">${listValue[35]}</font></td>
				<td><font size="1">${listValue[43]}</font></td>
				<td><font size="1">${listValue[51]}</font></td>
				<td><font size="1">${listValue[59]}</font></td>
				<td><font size="1">${listValue[67]}</font></td>
				<td><font size="1">${listValue[75]}</font></td>
				<td><font size="1">${listValue[83]}</font></td>
				<td><font size="1">${listValue[91]}</font></td>
				<td><font size="1">${listValue[99]}</font></td>
				<td><font size="1">${listValue[107]}</font></td>
				<td><font size="1">${listValue[115]}</font></td>
				<td><font size="1">${listValue[123]}</font></td>
				<td><font size="1">${listValue[131]}</font></td>
				<td><font size="1">${listValue[139]}</font></td>
				<td><font size="1">${listValue[147]}</font></td>
				<td><font size="1">${listValue[155]}</font></td>
				<td><font size="1">${listValue[163]}</font></td>
				<td><font size="1">${listValue[171]}</font></td>
				<td><font size="1">${listValue[179]}</font></td>
				<td><font size="1">${listValue[187]}</font></td>
				<td><font size="1">${listValue[195]}</font></td>
		</tr>
		<tr>
			    <td>5</td>
				<td><font size="1">${listValue[4]}</font></td>
				<td><font size="1">${listValue[12]}</font></td>
				<td><font size="1">${listValue[20]}</font></td>
				<td><font size="1">${listValue[28]}</font></td>
				<td><font size="1">${listValue[36]}</font></td>
				<td><font size="1">${listValue[44]}</font></td>
				<td><font size="1">${listValue[52]}</font></td>
				<td><font size="1">${listValue[60]}</font></td>
				<td><font size="1">${listValue[68]}</font></td>
				<td><font size="1">${listValue[76]}</font></td>
				<td><font size="1">${listValue[84]}</font></td>
				<td><font size="1">${listValue[92]}</font></td>
				<td><font size="1">${listValue[100]}</font></td>
				<td><font size="1">${listValue[108]}</font></td>
				<td><font size="1">${listValue[116]}</font></td>
				<td><font size="1">${listValue[124]}</font></td>
				<td><font size="1">${listValue[132]}</font></td>
				<td><font size="1">${listValue[140]}</font></td>
				<td><font size="1">${listValue[148]}</font></td>
				<td><font size="1">${listValue[156]}</font></td>
				<td><font size="1">${listValue[164]}</font></td>
				<td><font size="1">${listValue[172]}</font></td>
				<td><font size="1">${listValue[180]}</font></td>
				<td><font size="1">${listValue[188]}</font></td>
				<td><font size="1">${listValue[196]}</font></td>
		</tr>
		<tr>
			    <td>∑</td>
				<td><font size="1">${listValue[5]}</font></td>
				<td><font size="1">${listValue[13]}</font></td>
				<td><font size="1">${listValue[21]}</font></td>
				<td><font size="1">${listValue[29]}</font></td>
				<td><font size="1">${listValue[37]}</font></td>
				<td><font size="1">${listValue[45]}</font></td>
				<td><font size="1">${listValue[53]}</font></td>
				<td><font size="1">${listValue[61]}</font></td>
				<td><font size="1">${listValue[69]}</font></td>
				<td><font size="1">${listValue[77]}</font></td>
				<td><font size="1">${listValue[85]}</font></td>
				<td><font size="1">${listValue[93]}</font></td>
				<td><font size="1">${listValue[101]}</font></td>
				<td><font size="1">${listValue[109]}</font></td>
				<td><font size="1">${listValue[117]}</font></td>
				<td><font size="1">${listValue[125]}</font></td>
				<td><font size="1">${listValue[133]}</font></td>
				<td><font size="1">${listValue[141]}</font></td>
				<td><font size="1">${listValue[149]}</font></td>
				<td><font size="1">${listValue[157]}</font></td>
				<td><font size="1">${listValue[165]}</font></td>
				<td><font size="1">${listValue[173]}</font></td>
				<td><font size="1">${listValue[181]}</font></td>
				<td><font size="1">${listValue[189]}</font></td>
				<td><font size="1">${listValue[197]}</font></td>
		</tr>
		<tr>
			    <td>X平均</td>
				<td><font size="1" face="微软雅黑">${listValue[6]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[14]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[22]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[30]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[38]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[46]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[54]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[62]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[70]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[78]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[86]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[94]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[102]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[110]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[118]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[126]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[134]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[142]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[150]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[158]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[166]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[174]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[182]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[190]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[198]}</font></td>
		</tr>
		<tr>
			    <td>R</td>
				<td><font size="1" face="微软雅黑">${listValue[7]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[15]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[23]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[31]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[39]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[47]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[55]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[63]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[71]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[79]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[87]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[95]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[103]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[111]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[119]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[127]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[135]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[143]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[151]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[159]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[167]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[175]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[183]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[191]}</font></td>
				<td><font size="1" face="微软雅黑">${listValue[199]}</font></td>
		</tr> 
		</tbody>
	</table>
  </div>    
             
  
     
     
     
   