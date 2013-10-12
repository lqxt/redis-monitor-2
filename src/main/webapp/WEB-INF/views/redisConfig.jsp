<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<pubTag:resource/>
	
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	
	<link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/icon.css">
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
	<title>Angel</title>
	
</head>
<body>
	<pubTag:header/>
	<h2>Redis Server (redis.conf)</h2>
    <div style="margin:10px 0;"></div>
    
    <table class="easyui-datagrid" title="redis.conf" style="width:700px;height:900px"
            data-options="singleSelect:true,collapsible:true,url:'${pageContext.request.contextPath}/config/configDetail.htm',method:'get'" fit="true">
        <thead>
            <tr>
                <th data-options="field:'key',width:350,align:'center'">键</th>
                <th data-options="field:'value',width:350,align:'center'">值</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>EST-1</td><td>FI-SW-01</td><td>36.50</td><td>Large</td>
            </tr>
        </tbody>
    </table>
	<pubTag:footer/>
</body>
</html>