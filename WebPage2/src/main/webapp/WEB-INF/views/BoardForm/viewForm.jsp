<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="home" value="/"/>
<script>
	function backToBoard(){
		document.location.href="/WebPage2/board/selectBoard";
	}
	
	function deleteBoard(){
		let boardNum = document.getElementsByName("boardNum");

		document.location.href="/WebPage2/board/deleteBoard/"+boardNum.value;
	}
	
	function writeBoard(){
		document.location.href="/WebPage2/board/writeBoard"
	}
</script>
<center>
<table style="width: 650px; ">
<input type="hidden" name="boardNum" value="${board.num}"/>
	<tr>
		<td style="width: 300px; height:40px;" valign="middle"><h2>${board.title }</h2></td>
		<td style="width: 350px; height:40px;" align="right" valign="bottom">${board.writedate }</td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td  style="width: 300px; height:40px;" valign="top">${board.memId }</td>
		<td style="width: 350px; height:40px;" align="right" valign="top">첨부파일</td>
	</tr>
	<tr>
		<td colspan=2 style="width: 650px; height: 300px" valign="top">
		<pre>
${board.contents }
		</pre>
		</td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td colspan=2 align="right">
			<input type=button style="width: 60px; " onclick="writeBoard();" value='글쓰기'/> 
			<input type=button style="width: 60px; " value='답글'/>
			<input type=button style="width: 60px; " value='수정'/>
			<input type=button style="width: 60px; " onclick="deleteBoard();" value='삭제'/>
			<input type=button style="width: 60px; " onclick="backToBoard();" value='목록'/>
		</td>
	</tr>
</table>
</center>