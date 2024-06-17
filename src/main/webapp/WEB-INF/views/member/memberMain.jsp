<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
		function fCheck() {
			let pwd = checkForm.pwd.value;
			if(pwd.trim() == "") {
				alert("비밀번호를 입력하세요");
				return false;
			}
			else checkForm.submit();
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	요기는 memberMain페이지
	<h4>비밀번호 암호화</h4>
	<div>
		<a href="${pageContext.request.contextPath}/pwdCheck?pwd=1234">비밀번호 암호화</a>
	</div>
	기존비밀번호:${pwd}
	암호화비밀번호:${encPwd}
	<c:if test="${!empty encPwd}">
		<form name="checkForm" method="post" action="${ctp}/pwdCheckOk">
			비밀번호를 입력하세요: <input type="text" name="pwd" class="form-control"/>
			<input type="button" value="비밀번호확인" onclick="fCheck()" class="btn btn-success"/>
			<input type="hidden" name="encPwd" value="${encPwd}"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</c:if>
		<div>결과: ${pwdFlag}</div>
	<form action="post" action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="로그아웃" class="btn btn-secondary"/>
	</form>
</div>
<p><br/></p>
</body>
</html>