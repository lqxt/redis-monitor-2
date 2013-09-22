<%@tag import="com.redis.monitor.RedisJedisPool"%>
<%@tag pageEncoding="utf-8" isELIgnored="false" description="header" body-content="empty"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#">Angel</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">redis服务 <b class="caret"></b></a>
						<ul class="dropdown-menu">
						   <c:forEach var="rs" items="${redisServerList }">
						     <li class=""><a href="/index.htm?uuid=${rs.uuid }">${rs.description }</a></li>
						   </c:forEach>
							<!-- <li class=""><a href="#one">飞路快用户读服务</a></li>
							<li><a href="#two">飞路快用户写服务</a></li>
							<li class="divider"></li>
							<li><a href="#three">飞路快poi写服务</a></li>
							<li><a href="#three">飞路快poi读服务</a></li> -->
						</ul></li>
					<li><a href="#">keys</a></li>
					<li><a href="/config/toConfigDetail.htm">redis-config.xml</a></li>
					<li><a href="/server/redisServer.htm">redis节点管理</a></li>
					<li><a href="/monitor.htm">redis实时监控</a></li>
				</ul>
				<ul class="nav pull-right">
					<li class="active"><a href="#"><font size="5">redis://${host }:${port }</font></a></li>
					<li class="divider-vertical"></li>
					<li><button class="btn btn-small btn-primary" id="flushall" value="${id }">flushall</button></li>
					<li class="divider-vertical"></li>
					<li>
						<button class="btn btn-small btn-primary" id="flushdb" value="${id }">flushDB</button>
					</li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /navbar-inner -->
</div>
