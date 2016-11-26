<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发送通知公告</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8"
	src="../js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../js/ueditor/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="../js/ueditor/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
div {
	width: 100%;
}
</style>
</head>
<body>
	<div>
		<h1>发送通知公告</h1>
        <div>

        <form class="form-inline" role="form">
          <div class="form-group">
            <label>消息类型</label>
            <select class="form-control" id="msglx_list">
            <option value ="1">通知公告</option>
  			<option value ="2">系统消息</option>
            </select>
          </div>
          <div class="form-group" id='part' style="display:none;">
            <div class="input-group">
              <label>部门</label>
                <select class="form-control" id="collage_list">
                <option value ="1">全部</option>
                <option value ="2">信息学院</option>
                </select>
            </div>
          </div>
        </form>        
        
        </div>
		<script id="editor" type="text/plain"
			style="width:1024px;height:500px;"></script>
	</div>
	<div id="btns">
		<div>
			<button onclick="saveNotifitation()">发布通知</button>
			<button onclick="setContent()">清空内容</button>
			
			<button onclick="addMessage()">发布消息</button>
		</div>
	</div>

	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript">
		$(document).on("change", "#collage_list", function() {
		  if( $(".selector").val()=='1')
			   $("#part").hidden();
		  else
			   $("#part").show();		
		}	
		var ue = UE.getEditor('editor');
		function setContent(isAppendTo) {
			UE.getEditor('editor').setContent('', isAppendTo);
			//alert(arr.join("\n"));
		}

		function saveNotifitation() {
		    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		    //var range = UE.getEditor('editor').selection.getRange();
			//range.select();
			var message='';
			message = UE.getEditor('editor').getContent();
		    //alert(message);
		   
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'saveNotification.do',//后台修改
				data:{
					data:message
				},
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "发布失败",
						size : 'small'
					});
				},
				success : function(em) {
					bootbox.alert({
						message : "发布成功",
						size : 'small'
					});
				}
			});
		}
		function addMessage() {
		    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		    //var range = UE.getEditor('editor').selection.getRange();
			//range.select();
			var message='';
			message = UE.getEditor('editor').getContentTxt();
		    //alert(message);
		    var title="测试";
		    var userid=1245041;
		    
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'saveMessage.do',//后台修改
				data:{
					content:message,
					title:title,
					userid:userid
				},
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "发布失败",
						size : 'small'
					});
				},
				success : function(em) {
					bootbox.alert({
						message : "发布成功",
						size : 'small'
					});
				}
			});
		}
	});
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	</script>

</body>
</html>
