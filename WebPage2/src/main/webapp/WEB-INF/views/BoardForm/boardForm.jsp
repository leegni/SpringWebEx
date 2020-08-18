<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="home" value="/"/>
<script>
	function SearchBoard(){
		let searchFrm = document.getElementById("searchFrm");
		searchFrm.submit();
	}
	
	function checkBoxSelect(){
		let checkBox = document.getElementsByName("checkBox");
		let checkAll = document.getElementById("checkAll");
		
		for(let i = 0; i<checkBox.length; i++){
			checkBox[i].checked = checkAll.checked;
		}
	}
	
	function deleteBoard(){
		let checkBox = document.getElementsByName("checkBox");
		let deleteNum = "";
		
		for(let i=0; i<checkBox.length;i++){
			if(checkBox[i].checked)
				deleteNum += checkBox[i].value+" ";
		}
		document.location.href="/WebPage2/board/deleteBoard/"+deleteNum;
	}
	
	function writeBoard(){
		document.location.href="/WebPage2/board/writeBoard"
	}
</script>
<center>

<table style="width: 650px; ">
	<thead>
	<tr>
		<th style="width: 40px; height:20px;" align="center">선택</th>
		<th style="width: 330px; height:20px;" align="center">제 목</th>
		<th style="width: 80px; height:20px;" align="center">작성자</th>
		<th style="width: 120px; height:20px;" align="center">작성일</th>
		<th style="width: 80px; height:20px;" align="center">조회수</th>
	</tr>
	</thead>
	<tr>
		<td style="width: 40px; height:20px;" align="center"><hr/></td>
		<td style="width: 330px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
		<td style="width: 120px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
	</tr>

	<c:forEach var="boardLst" items="${boardLst }" varStatus="loop">
		<tr>
		<td style="width: 40px; height:40px;" align="center"><input type="checkbox" name="checkBox" value="${boardLst.num }"/></td>
		<td style="width: 330px; height:40px;" align="center"><a href="${home }board/getContents/${boardLst.num }"><pre>${boardLst.title }</pre></a></td>
		<td style="width: 80px; height:40px;" align="center">${boardLst.memId }</td>
		<td style="width: 120px; height:40px;" align="center">${boardLst.writedate }</td>
		<td style="width: 80px; height:40px;" align="center">${boardLst.hits }</td>
		</tr>
	</c:forEach>
	
	<tr><td colspan=5><hr/></td></tr>
	<tr>
		<td colspan=2><input type="checkbox" id="checkAll" onclick="checkBoxSelect();"/>전체선택</td>
		<td colspan=3 align="right">
			<input type="button" value='삭제' onclick="deleteBoard();" style="width: 100px; "/>
			<input type="button" value='글쓰기' onclick="writeBoard();" style="width: 100px; "/>
		</td>
	</tr>
	<tr><td colspan=5><hr/></td></tr>
</table>
${nav }
<form action="${home }board/selectBoard" id='searchFrm' method="post">
${search }
</form>
</center>