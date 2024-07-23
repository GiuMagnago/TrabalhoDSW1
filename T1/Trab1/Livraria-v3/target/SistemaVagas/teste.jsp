<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
<title>SistemaVagas</title>
	</head>
	
	<body>
		<c:choose>
			<c:when test="${empresa != null}"> 
				<c:if test="${not empty sessionScope.semVagas}">
					<script>
						alert("${sessionScope.semVagas}");
					</script>
					<c:remove var="semVagas" scope="session"/>
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
							<th><fmt:message key="vaga.id_vaga" /></th>
							<th><fmt:message key="vaga.id_empresa" /></th>
							<th><fmt:message key="vaga.cnpj_empresa" /></th>
							<th><fmt:message key="vaga.remuneracao" /></th>
							<th><fmt:message key="vaga.descricao" /></th>
							<th><fmt:message key="vaga.dataLimite" /></th>
						</tr>
						<c:forEach var="vagas" items="${requestScope.listaVagas}">
							<tr>
								<td>${vaga.id_vaga}</td>
								<td>${vaga.empresa}</td>
								<td>${vaga.cnpj_empresa}</td>
								<td>${vaga.remuneracao}</td>
								<td>${vaga.descricao}</td>
								<td>${vaga.dataLimite}</td>
								<td>
									<a href="">Editar</a> <br/>
									<a href="listaCandidatura.jsp">Ver Candidaturas</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:when test="${profissional != null}">
				<c:if test="${not empty sessionScope.semVagas}">
					<script>
						alert("${sessionScope.semVagas}");
					</script>
					<c:remove var="semVagas" scope="session"/>
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
							<th><fmt:message key="vaga.id_vaga" /></th>
							<th><fmt:message key="vaga.id_empresa" /></th>
							<th><fmt:message key="vaga.cnpj_empresa" /></th>
							<th><fmt:message key="vaga.remuneracao" /></th>
							<th><fmt:message key="vaga.descricao" /></th>
							<th><fmt:message key="vaga.dataLimite" /></th>
						</tr>
						<c:forEach var="vagas" items="${requestScope.listaVagas}">
							<tr>
								<td>${vaga.id_vaga}</td>
								<td>${vaga.empresa}</td>
								<td>${vaga.cnpj_empresa}</td>
								<td>${vaga.remuneracao}</td>
								<td>${vaga.descricao}</td>
								<td>${vaga.dataLimite}</td>
								<td>
									<a href="">Aplicar</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
		</c:choose>

	</body>

</html>