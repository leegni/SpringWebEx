<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="home" value="/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">

function setParentAddr1(postcode, address){
opener.document.getElementById("memPostCode").value = postcode;
opener.document.getElementById("memAdd").value = address;
window.close();
}

</script>
<style type="text/css">
.list_over {overflow:auto; background-color:pink; }
.list_out { background-color:#FFFFFF; }
</style>
</head>
<body>
<form action='${home }membership/searchZipCode' method='post'>
	<h3>
		동면<input type='text' name='address'/>
		<input type='submit' value='우편번호 검색'/>
	</h3>
</form>
<br/>
<c:forEach var="post" items="${postcodeLst }">
<c:set var="postcode" value="${post.postcode }"/>
<c:set var="address" value="${post.sido } ${post.sigungu } ${post.ubmyeun } ${post.dong1 } ${post.dong2 } ${post.ri }"/>
<div onmouseover="this.className='list_over'"
onmouseout="this.className='list_out'"
onclick="setParentAddr1('${postcode}', '${address}');">
${postcode} ${address }<br/>
</div>
</c:forEach>
</body>
</html>