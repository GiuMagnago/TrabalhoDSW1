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
    <c:if test="${not empty sessionScope.erroLogarUsuario}">
        <script>
            alert("${sessionScope.erroLogarUsuario}");
        </script>
        <c:remove var="erroLogarUsuario" scope="session"/>
    </c:if>

    <fmt:bundle basename="messages">
        <header>
            <div class="titulo">
                <h1>SistemaVagas</h1>
            </div>

            <a href="/SistemaVagas/index.jsp" class="btn voltar">
                <fmt:message key="voltar"/>
            </a>
        </header>

        <div class="formulario">
            <form action="/SistemaVagas/usuario/login" method="GET">

                <h3><fmt:message key="login"/></h3>

                <label for="Email">
                <fmt:message key="email"/>
                </label> <br>

                <input type="email" id="email" name="email" required>
                <br>
                
                <label for="Senha">
                    <fmt:message key="senha"/>
                </label> <br>

                <input type="password" id="senha" name="senha" required>

                <br>
                
                <input
                    type="submit"
                    class="confirmar"
                    value="<fmt:message key="confirmar"/>"
                >
            </form>
        </div>
    </fmt:bundle>

</body>
</html>