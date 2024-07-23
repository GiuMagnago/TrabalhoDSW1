<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>SistemaVagas</title>
</head>
<body>
    <%
			String contextPath = request.getContextPath().replace("/", "");
		%>
    <fmt:bundle basename="messages">
        <header>
            <div class="titulo">
                <h1>Cadastrar</h1>
            </div>
            <a href="/SistemaVagas/index.jsp" class="btn voltar">
                <fmt:message key="voltar"/>
            </a>
        </header>

        <div class="conteiner-central">
            <div>
                <h1>
                    <fmt:message key="msg_cadastro"/>
                </h1>
            </div>
            <div class="optionBox">
                <a href="/SistemaVagas/cadastro/profissional.jsp" class="btn profissional">
                    <fmt:message key="profissional"/>
                </a>
                <a href="/SistemaVagas/empresa/formCadastro" class="btn empresa">
                    <fmt:message key="empresa"/>
                </a>
            </div>
        </div>
    </fmt:bundle>
</body>
</html>