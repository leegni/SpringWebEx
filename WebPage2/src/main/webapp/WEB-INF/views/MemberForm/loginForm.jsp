<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/"/>
<center>
${result }
<form action="${home}membership/loginProc" method="post">
<table>
	<tr>
		<td>아이디</td>
		<td><input type=text name='memId' placeholder='ID 입력'/></td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type=password name='memPw' placeholder='PW 입력'/></td>
	</tr>
	<tr>
		<td colspan=2 align='center'>
			<input type=submit value='로그인' style="width: 86px; "/>
			<input type=reset value='취소' style="width: 86px; "/> 
		</td>
	</tr>
	<tr>
	<td><a href="${home }membership/searchID">아이디 찾기 </a></td>
	<td><a href="${home }membership/searchPW"> 비밀번호 찾기</a></td>
	</tr>
</table>
</form>
</h3>
</center>
