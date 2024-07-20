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
                <h1>Cadastro de Profissional</h1>
            </div>
            <a href="/SistemaVagas/cadastro/cadastro.jsp" class="btn voltar">
                <fmt:message key="voltar"/>
            </a>
        </header>
        <div class="formulario">
            <form id="form" action="/SistemaCadastro/profissional" method="post">
                <h3>
                    <fmt:message key="profissional" />
                </h3>

                <label for="email">
                    <fmt:message key="email"/>
                </label> <br>
                <input type="email" id="email" name="email" value="${sessionScope.email != null ? sessionScope.email : ''}" required><br>

                <label for="senha">
                    <fmt:message key="senha"/>
                </label> <br>
                <input type="password" id="senha" name="senha" value="${sessionScope.senha != null ? sessionScope.senha : ''}" required><br>

                <label for="cpf">
                    <fmt:message key="cpf"/>
                </label> <br>
                <input type="number" id="cpf" name="cpf" value="${sessionScope.cpf != null ? sessionScope.cpf : ''}" required maxlength="11"><br>

                <label for="nome">
                    <fmt:message key="nome"/>
                </label> <br>
                <input type="text" id="nome" name="nome" value="${sessionScope.nome != null ? sessionScope.nome : ''}" required><br>

                <label for="telefone">
                    <fmt:message key="telefone"/>
                </label> <br>
                <input type="number" id="telefone" name="telefone" value="${sessionScope.telefone != null ? sessionScope.telefone : ''}" required><br>

                <label for="sexo">
                    <fmt:message key="sexo"/>
                </label><br>

                <select name="sexo" id="opcao">
                    <option value="masculino" ${'masculino' eq sessionScope.sexoString ? 'selected' : ''}>
                        <fmt:message key="masculino"/>
                    </option>
                    <option value="feminino" ${'feminino' eq sessionScope.sexoString ? 'selected' : ''}>
                        <fmt:message key="feminino"/>
                    </option>
                </select> <br>

                <label for="dataNasc">
                    <fmt:message key="nascimento"/>
                </label> <br>
                <input type="date" id="dataNasc" name="dataNasc" required value="${sessionScope.dataNasc != null ? sessionScope.dataNasc : ''}"><br>

                <input type="submit" class="confirmar" value="<fmt:message key='confirmar'/>">
            </form>
        </div>
    </fmt:bundle>
</body>
</html>