<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title>SistemaVagas</title>
	</head>

	<body>
		<c:choose>
			<c:when test="{empresa == null}">
				<c:if test="${not empty sessionScope.semCanditaturas}">
					<script>
						alert("${sessionScope.semCanditaturas}");
					</script>
					<c:remove var="semCanditaturas" scope="session"/>
				</c:if>
				<div align="center">
					<h1>
						Suas Vagas
					</h1>
				</div>
				<div align="center">
					<table border="1">
						<caption>
							Suas vagas
						</caption>
						<tr>
							<th><fmt:message key="candidatura.id_profissional" /></th>
							<th><fmt:message key="candidatura.id_vaga" /></th>
							<th><fmt:message key="candidatura.statusCandidatura" /></th>
						</tr>
						<c:forEach var="candidatura" items="${requestScope.listaCandidatura}">
							<tr>
								<td>${candidatura.id_profissional}</td>
								<td>${candidatura.id_vaga}</td>
								<td>${candidatura.statusCandidatura}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty sessionScope.semCanditaturas}">
					<script>
						alert("${sessionScope.semCanditaturas}");
					</script>
					<c:remove var="semCanditaturas" scope="session"/>
				</c:if>
				<div align="center">
					<h1>
						Suas Vagas
					</h1>
				</div>
				<div align="center">
					<table border="1">
						<caption>
							Suas vagas
						</caption>
						<tr>
							<th><fmt:message key="candidatura.id_profissional" /></th>
							<th><fmt:message key="candidatura.id_vaga" /></th>
							<th><fmt:message key="candidatura.statusCandidatura" /></th>
						</tr>
						<c:forEach var="candidatura" items="${requestScope.listaCandidatura}">
							<tr>
								<td>${candidatura.id_profissional}</td>
								<td>${candidatura.id_vaga}</td>
								<td>${candidatura.statusCandidatura}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</body>
</fmt:bundle>

</html>