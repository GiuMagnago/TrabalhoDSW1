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
							<a href="">Editar</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>