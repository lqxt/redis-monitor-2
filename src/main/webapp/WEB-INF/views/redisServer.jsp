<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<pubTag:resource/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/icon.css">
    <link href="/resources/bootstrap-switch/bootstrap-switch.css" rel="stylesheet">
	<script type="text/javascript" src="/resources/bootstrap-switch/bootstrap-switch.js"></script>
    
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	
	<script type="text/javascript" src="/resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/plugins/jquery.dialog.js"></script>
	<title>Angel</title>
	
</head>
<body>
	<pubTag:header/>
	<h2>redis服务配置</h2>
    <table id="dg" title="redis服务列表" class="easyui-datagrid" style="width:700px;height:250px"
            url="/server/redisServerList.htm"
            toolbar="#toolbar" 
            rownumbers="true" fitColumns="true" singleSelect="true" fit="true">
        <thead>
            <tr>
                <th field="uuid" width="50">uuid</th>
                <th field="host" width="50">host</th>
                <th field="port" width="50">port</th>
                <th field="maxActive" width="50">maxActive</th>
                <th field="maxIdle" width="50">maxIdle</th>
                <th field="maxWait" width="50">maxWait</th>
                <th field="testOnBorrow" width="50">testOnBorrow</th>
                <th field="master" width="50">isMaster</th>
                <th field="slaveRedisServer" width="50">slaves</th>
                <th field="name" width="50">名称</th>
                <th field="description" width="50">描述</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
       <button class="btn btn-small btn-primary" id="addRedis"  onclick="newRedis()">添加</button>
       <a href="#myModal" role="button" class="btn" data-toggle="modal">Modal</a>
       <button class="btn btn-small btn-primary" id="updateRedis">修改</button>
       <button class="btn btn-small btn-primary" id="removeRedis">删除</button>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">redis服务列表</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>First Name:</label>
                <input name="firstname" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>Last Name:</label>
                <input name="lastname" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>Phone:</label>
                <input name="phone">
            </div>
            <div class="fitem">
                <label>Email:</label>
                <input name="email" class="easyui-validatebox" validType="email">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$j('#dlg').dialog('close')">Cancel</a>
    </div>
    <script type="text/javascript">
        var url;
        function newRedis(){
            $j('#dlg').dialog('open').dialog('setTitle','New User');
            $j('#fm').form('clear');
            url = 'saveRedis.htm';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit User');
                $('#fm').form('load',row);
                url = 'updateRedis.htm?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
	<pubTag:footer/>
	
          <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              <h5 id="myModalLabel">新增</h5>
            </div>
            <div class="modal-body">
            <form class="form-horizontal e-form">
			  <div class="control-group">
			    <label class="control-label" for="inputEmail">Email</label>
			    <div class="controls">
			      <input type="text" id="inputEmail" placeholder="Email">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="inputPassword">Password</label>
			    <div class="controls">
			      <input type="password" id="inputPassword" placeholder="Password">
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			      <label class="checkbox">
			        <input type="checkbox"> Remember me
			      </label>
			      <button type="submit" class="btn">Sign in</button>
			    </div>
			  </div>
			</form>
            </div>
            <div class="modal-footer">
              <button class="btn" data-dismiss="modal">关闭</button>
              <button class="btn btn-primary">保存</button>
            </div>
          </div>
          
</body>
</html>