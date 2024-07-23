<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>SistemaVagas</title>
</head>
<body>
    <c:set var="profissional" value="${sessionScope.profissional}" />
    <c:set var="empresa" value="${sessionScope.empresa}" />
    <c:set var="listaVagas" value="${sessionScope.listaVagas}" />
    <c:set var="agendamento" value="${sessionScope.agendamento}" />
    <c:set var="deletado" value="${sessionScope.deletado}" />

    <c:if test="${not empty agendamento}">
        <script>
            alert("${sessionScope.agendamentoFeito}");
        </script>
        <c:remove var="agendamento" scope="session"/>
    </c:if>

    <fmt:bundle basename="messages">
    <header>
        <div class="titulo">Bem vindo!</div>

        <div class="usuario">
            <c:choose>
                <c:when test="${profissional != null}">
                    <a href="/SistemaVagas/usuario.jsp" class="btn profissional">
                        <fmt:message key="BoasVindas" /> ${profissional.nome}
                    </a>
                </c:when>
                <c:when test="${empresa != null}">
                    <a href="/SistemaVagas/usuario.jsp" class="btn empresa">
                        <fmt:message key="BoasVindas" /> ${empresa.nome}
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/SistemaVagas/login.jsp" class="btn login">
                        <fmt:message key="entrar"/>
                    </a>
                    <a href="/SistemaVagas/cadastro.jsp" class="btn cadastro">
                        <fmt:message key="cadastro"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
    <main>
        <div class="filtro">
            <label for="filtro">
                Filtro:
            </label>
            <br>
            <input type="filtro" id="filtro" name="filtro">
        </div>

        <h1 align="center"> Lista de Vagas </h1>
        <div class="tabela" id="tabela"></div>
    </main>
    </fmt:bundle>
</body>
</html>