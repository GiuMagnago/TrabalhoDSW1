<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
								<c:remove var="semVagas" scope="session" />
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
										<th>
											<fmt:message key="vaga.IdVaga" />
										</th>
										<th>
											<fmt:message key="vaga.IdEmpresa" />
										</th>
										<th>
											<fmt:message key="vaga.CnpjEmpresa" />
										</th>
										<th>
											<fmt:message key="vaga.Remuneracao" />
										</th>
										<th>
											<fmt:message key="vaga.Descricao" />
										</th>
										<th>
											<fmt:message key="vaga.DataLimite" />
										</th>
									</tr>
									<c:forEach var="vagas" items="${requestScope.listaVagas}">
										<tr>
											<td>${vaga.getIdVaga()}</td>
											<td>${vaga.getIdEmpresa()}</td>
											<td>${vaga.getCnpjEmpresa()}</td>
											<td>${vaga.getRemuneracao()}</td>
											<td>${vaga.getDescricao()}</td>
											<td>${vaga.getDataLimite()}</td>
											<td>
												<a href="">Editar</a> <br />
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
								<c:remove var="semVagas" scope="session" />
							</c:if>
							<div align="center">
								<h1>
									Vagas
								</h1>
							</div>
							<div align="center">
								<table border="1">
									<caption>
										Suas vagas
									</caption>
									<tr>
										<th>
											<fmt:message key="vaga.IdVaga" />
										</th>
										<th>
											<fmt:message key="vaga.IdEmpresa" />
										</th>
										<th>
											<fmt:message key="vaga.CnpjEmpresa" />
										</th>
										<th>
											<fmt:message key="vaga.Remuneracao" />
										</th>
										<th>
											<fmt:message key="vaga.Descricao" />
										</th>
										<th>
											<fmt:message key="vaga.DataLimite" />
										</th>
									</tr>
									<c:forEach var="vaga" items="${requestScope.listaVagas}">
										<tr>
											<td>${vaga.getIdVaga()}</td>
											<td>${vaga.getIdEmpresa()}</td>
											<td>${vaga.getCnpjEmpresa()}</td>
											<td>${vaga.getRemuneracao()}</td>
											<td>${vaga.getDescricao()}</td>
											<td>${vaga.getDataLimite()}</td>
											<td>
												<a
													href="/SistemaVagas/canditura/candidatar?id_vaga${vaga.id_vaga}">Aplicar</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							c:if test="${not empty sessionScope.semVagas}">
							<script>
								alert("${sessionScope.semVagas}");
							</script>
							<c:remove var="semVagas" scope="session" />
							</c:if>
							<div align="center">
								<h1>
									Vagas
								</h1>
							</div>
							<div align="center">
								<table border="1">
									<caption>
										Suas vagas
									</caption>
									<tr>
										<th>
											<fmt:message key="vaga.id_vaga" />
										</th>
										<th>
											<fmt:message key="vaga.id_empresa" />
										</th>
										<th>
											<fmt:message key="vaga.cnpj_empresa" />
										</th>
										<th>
											<fmt:message key="vaga.remuneracao" />
										</th>
										<th>
											<fmt:message key="vaga.descricao" />
										</th>
										<th>
											<fmt:message key="vaga.dataLimite" />
										</th>
									</tr>
									<c:forEach var="vagas" items="${requestScope.listaVagas}">
										<tr>
											<td>${vaga.getIdVaga()}</td>
											<td>${vaga.getIdEmpresa()}</td>
											<td>${vaga.getCnpjEmpresa()}</td>
											<td>${vaga.getRemuneracao()}</td>
											<td>${vaga.getDescricao()}</td>
											<td>${vaga.getDataLimite()}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:otherwise>
					</c:choose>

				</body>

				</html>