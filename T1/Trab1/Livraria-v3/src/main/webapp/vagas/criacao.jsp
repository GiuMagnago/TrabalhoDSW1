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
    <c:set var="empresa" value="sessionScope.empresa" />
    <fmt:bundle basename="messages">
        <header>
            <div class="titulo">
                <h1>Criação de Vagas</h1>
            </div>
            <a href="/SistemaVagas/cadastro.jsp" class="btn voltar">
                <fmt:message key="voltar"/>
            </a>
        </header>
        <div class="formulario">
            <form id="form" action="/SistemaVagas/vaga/criarVaga" method="post">
                <h3>
                    <fmt:message key="vaga" />
                </h3>

                <label for="remuneracao">
                    <fmt:message key="remuneracao"/>
                </label> <br>
                <input type="number" id="remuneracao" name="remuneracao" required><br>

                <label for="descricao">
                    <fmt:message key="descricao"/>
                </label> <br>
                <input type="text" id="descricao" name="descricao" required><br>

                <label for="dataLimite">
                    <fmt:message key="nascimento"/>
                </label> <br>
                <input type="date" id="dataLimite" name="dataLimite" required><br>

                <input type="submit" class="confirmar" value="<fmt:message key='confirmar'/>">
            </form>
        </div>
    </fmt:bundle>
</body>
</html>