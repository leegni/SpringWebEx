<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/"/>
<script type="text/javascript">
	function SearchPostCode(url){
		window.name = "우편번호 검색"
		window.open(url, "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400");
	}
</script>
<center>
<h3><font color='red'>${msg }</font></h3>

<!-- 인증번호 확인 추후 삭제 -->
세션번호 : ${sessionInfo }<br/>
인증번호 : ${sessionInfo.verifyNum }

<form action='${home }membership/membershipProc' method='post'>
<table>
<tr><td colspan='4' align='center'><hr/>필수사항<hr/></td></tr>
	<tr>
		<td align='right' height=40>아이디</td>
		<td>
			<input type=text name='memId' placeholder='id 입력' value='${member.memId }'/> 
		</td>
		<td colspan="2"><button formaction='${home }membership/idCheck' >중복 확인</button></td>
	</tr>
	<tr>
		<td align='right' height=40>패스워드</td>
		<td>
			<input type="password" name='memPw' placeholder='pw 입력' value='${member.memPw }'/> 
		</td>
		<td align='right'>패스워드 확인</td>
		<td>
			<input type="password" name='pwOk' placeholder='pw 입력' value='${member.memPw }'/> 
		</td>
	</tr>
	<tr>
		<td align='right' height=40>E-Mail</td>
		<td>
			<input type=text name='memEmail' placeholder='E-Mail 입력' value='${member.memEmail }'/> 
		</td>
		<td colspan='2'><button formaction='${home }membership/sendVerifyNum' >인증번호 전송</button></td>
	</tr>
	<tr>
		<td align='right'>인증번호</td>
		<td>
			<input type=text name='verifyNum' placeholder='인증번호 입력'/> 
		</td>
		<td colspan='2'><button formaction='${home }membership/verifyProc'>인증번호 확인</button></td>
	</tr>
	<tr><td colspan='4' align='center'><hr/>선택사항<hr/></td></tr>
	<tr>
		<td align='right'>우편번호</td>
		<td>
			<input type=text name='memPostCode' id='memPostCode' value='${member.memPostCode }' readonly="readonly"/> 
		</td>
		<td colspan="2"><input type='button' value='우편번호 검색' onclick="SearchPostCode('${home}membership/searchPostCode');"/></td>
	</tr>
	<tr>
		<td align='right'>주소</td>
		<td colspan="3">
			<input type=text name='memAdd' id='memAdd' value='${member.memAdd }' readonly="readonly" style="width: 475px; "/> 
		</td>
	</tr>
	<tr>	
		<td align='right'>상세주소</td>
		<td colspan="3">
			<input type=text name='memDetailAdd' value='${member.memDetailAdd }' style="width: 475px; "/> 
		</td>
	</tr>
	<tr>
		<td align='right' width=120>성 별</td>
		<td colspan="3">
		<c:set var="nchk" value="checked"/>
		<c:set var="mchk" value=""/>
		<c:set var="wchk" value=""/>
		<c:choose>
			<c:when test="${member.memGender eq 'n' }">
				<c:set var="nchk" value="checked"/>
				<c:set var="mchk" value=""/>
				<c:set var="wchk" value=""/>
			</c:when>
			<c:when test="${member.memGender eq 'm' }">
				<c:set var="nchk" value=""/>
				<c:set var="mchk" value="checked"/>
				<c:set var="wchk" value=""/>
			</c:when>
			<c:when test="${member.memGender eq 'w' }">
				<c:set var="nchk" value=""/>
				<c:set var="mchk" value=""/>
				<c:set var="wchk" value="checked"/>
			</c:when>
		</c:choose>
			<input type=radio name='memGender' value='n' ${nchk }/>선택안함
			<input type=radio name='memGender' value='m' ${mchk }/>남자
			<input type=radio name='memGender' value='w' ${wchk }/>여자 
		</td>
	</tr>
	
	<tr>
		<td align='center' height=40 colspan=4>
			<input type=submit value='회원가입' style="width: 120px; "/>
			<input type=reset value='취소' style="width: 120px; "/>	 
		</td>
	</tr>

</table>
	</form>
</center>