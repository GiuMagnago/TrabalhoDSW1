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
    <c:if test="${not empty sessionScope.erroNovoUsuario}">
        <script>
            alert("${sessionScope.erroNovoUsuario}");
        </script>
        <c:remove var="erroNovoUsuario" scope="session"/>
    </c:if>

    <fmt:bundle basename="messages">
        <header>
            <div class="titulo">
                <h1>Cadastro de Empresa</h1>
            </div>
            <a href="/SistemaVagas/cadastro.jsp" class="btn voltar">
                <fmt:message key="voltar"/>
            </a>
        </header>
        <div class="formulario">
            <form id="form" action="/SistemaVagas/empresa/cadastro" method="post">
                <h3>
                    <fmt:message key="empresa" />
                </h3>
                <input type="hidden" name="action" value="cadastro">

                <label for="email">
                    <fmt:message key="email"/>
                </label> <br>
                <input type="email" id="email" name="email" required><br>

                <label for="senha">
                    <fmt:message key="senha"/>
                </label> <br>
                <input type="password" id="senha" name="senha" required><br>

                <label for="cnoj">
                    <fmt:message key="cnpj"/>
                </label> <br>
                <input type="number" id="cnpj" name="cnpj" required maxlength="14"><br>

                <label for="nome">
                    <fmt:message key="nome"/>
                </label> <br>
                <input type="text" id="nome" name="nome" required><br>

                <label for="descricao">
                    <fmt:message key="descricao"/>
                </label> <br>
                <input type="text" id="descricao" name="descricao" required><br>

                <label for="cidade">
                    <fmt:message key="cidade"/>
                </label> <br>
                <input type="text" id="cidade" name="cidade" required><br>

                <input type="submit" class="confirmar" value="<fmt:message key='confirmar'/>">
            </form>
        </div>
    </fmt:bundle>
</body>
</html>