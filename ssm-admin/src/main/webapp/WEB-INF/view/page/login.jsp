<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SsmAdmin | Sign in</title>
<%@include file="/WEB-INF/view/include/includeCss.jsp"%>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>S</b>sm<b>A</b>dmin
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">
				Sign in to start your session
			</p>
			<form action="login.do" method="post">
			    <input type="hidden" name="action" value="do" />
				<div class="form-group has-feedback">
					<input name="uname" type="text" class="form-control" placeholder="用户名"> <span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input name="password" type="password" class="form-control" placeholder="密码"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

		</div>
		<c:if test="${not empty errMsg }">
			<div class="alert alert-danger" id="dangerMsg" role="alert">${errMsg }</div>
		</c:if>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<%@include file="/WEB-INF/view/include/includeJs.jsp"%>

</body>
</html>
