function getNextElement(node) {
	if(node.nextSibling.nodeType == 1) { //判断下一个节点类型为1则是“元素”节点   
		return node.nextSibling;
	}
	if(node.nextSibling.nodeType == 3) { //判断下一个节点类型为3则是“文本”节点  ，回调自身函数  
		return getNextElement(node.nextSibling);
	}
	return null;
}

function orderjs() {
	var i = 1;
	//$("#jia1").click(function(){
	document.getElementById("jia").onclick = function() {
		i++;
		var sourceNode = document.getElementById("div_1");
		var cloneNode = sourceNode.cloneNode(true);
		cloneNode.setAttribute("id", "div_" + i);
		sourceNode.parentNode.appendChild(cloneNode);
		var div = document.getElementById("div_" + i);
		var firstChild = div.firstChild;
		var chejian = getNextElement(firstChild);
		var jiaofu_num = getNextElement(chejian);
		//var  jiaofu_date=getNextElement(jiaofu_num);
		var div_date = getNextElement(jiaofu_num);
		chejian.name = "chejian" + i;
		jiaofu_num.name = "jiaofu_num" + i;
		var div_first = div_date.firstChild;
		var jiaofu_date = getNextElement(div_first);
		jiaofu_date.name = "jiaofu_date" + i;
		$('.form_date').each(function() {
			$(this).datetimepicker({
				language: 'zh-CN',
				weekStart: 0,
				todayBtn: 1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 3,
				minView: 2,
				forceParse: 0
			});
		});
	};
	$("#jian").click(function() {

		if(i > 1) {
			i--;
			var form = document.getElementById("form");
			form.removeChild(form.childNodes[form.childNodes.length - 1]);
		}
	});

	$("#tijiao").click(function() {
		/*var val=new Array(i);
		var val1=new Array(i);
		var val2=new Array(i);
		for(j=1;j<=i;j++){
			var  div=document.getElementById("div_"+j);
			var  firstNode=div.firstChild;
			var  select=getNextElement(firstNode);
			var  shebei=getNextElement(select);
			var  shuliang=getNextElement(shebei);
		    val[j-1]=select.options[select.selectedIndex].value;
			val1[j-1]=shebei.options[shebei.selectedIndex].value;
			val2[j-1]=shuliang.value;
		};
		var json=JSON.stringify(val);
		alert("工序："+val+"设备："+val1+"数量:"+val2);*/
		var formParam = $("form").serializeArray();
		alert(formParam);
		console.info(formParam);
	});
	$('.date').datetimepicker({
		language: 'zh-CN',
		weekStart: 0,
		todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 3,
		minView: 2,
		forceParse: 0
	});
}
	function order_js() {
		alert("ok");
		var i = 1;
		var len=$(".used").length;
			i=len;
		$("#jia").click(function(){
			i++;
			var sourceNode = $("#div_0");
			var cloneNode = sourceNode.clone();
			cloneNode.attr("id", "div_" + i);
			sourceNode.parent().append(cloneNode);
			$("#div_" + i).removeClass("hidden");
			var chejian = $("#div_" + i).children().first();
			var jiaofu_num =chejian.next();
			var div_date =jiaofu_num.next();
			chejian.attr("name","chejian" + i);
			jiaofu_num.attr("name","jiaofu_num" + i);
			var jiaofu_date = div_date.children().first();
			jiaofu_date.attr("name","jiaofu_date" + i);
			var div_date2 =div_date.next();
			var plan_finish_date = div_date2.children().first();
			plan_finish_date.attr("name","plan_finish_date" + i);
			$(".form_date").each(function() {
				$(this).datetimepicker({
					language: 'zh-CN',
					weekStart: 0,
					todayBtn: 1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 3,
					minView: 2,
					forceParse: 0
				});
			});
		});
		$("#jian").click(function() {

			if(i > len) {
				i--;
				var form = document.getElementById("form");
				form.removeChild(form.childNodes[form.childNodes.length - 1]);
			}
		});

		$("#tijiao").click(function() {
			/*var val=new Array(i);
			var val1=new Array(i);
			var val2=new Array(i);
			for(j=1;j<=i;j++){
				var  div=document.getElementById("div_"+j);
				var  firstNode=div.firstChild;
				var  select=getNextElement(firstNode);
				var  shebei=getNextElement(select);
				var  shuliang=getNextElement(shebei);
			    val[j-1]=select.options[select.selectedIndex].value;
				val1[j-1]=shebei.options[shebei.selectedIndex].value;
				val2[j-1]=shuliang.value;
			};
			var json=JSON.stringify(val);
			alert("工序："+val+"设备："+val1+"数量:"+val2);*/
			var formParam1 = $("form").serialize();
			var formParam2 = $("form").serializeArray();
			alert(formParam1);
			console.info(formParam2);
		});
		var j=1;
		$(".used").each(function(){
			$(this).attr("id","div_"+j);
			var chejian = $(this).children().first();
			var jiaofu_num =chejian.next();
			var div_date =jiaofu_num.next();
			chejian.attr("name","chejian" + j);
			jiaofu_num.attr("name","jiaofu_num" + j);
			var jiaofu_date = div_date.children().first();
			jiaofu_date.attr("name","jiaofu_date" + j);
			var div_date2 =div_date.next();
			var plan_finish_date = div_date2.children().first();
			plan_finish_date.attr("name","plan_finish_date" + j);
			j++;
		});
		
	}
	function calender(){
		$('.date').datetimepicker({
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 2,
			forceParse: 0
		});

	}
