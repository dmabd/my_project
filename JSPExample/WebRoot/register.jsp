<%@ page language="java" pageEncoding="gb2312"%>
<html>
<head>
	<title>简易留言板</title>
</head>
<body bgcolor="#E3E3E3">
	<form action="registerServlet" method="post">
		<table>
			<caption>用户注册</caption>
			<tr>
				<td>登录名：</td>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password"/></td>
			</tr>
		</table>
		<input type="submit" value="注册"/>
		<input type="reset" value="重置"/>
	</form>
</body>
</html>
