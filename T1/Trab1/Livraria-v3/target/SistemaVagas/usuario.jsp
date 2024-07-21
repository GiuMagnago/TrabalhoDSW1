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
    <c:set var="path" value="/SistemaVagas/atualizar" />

    <c:if test="${not empty sessionScope.erroDeletar}">
        <script>
            alert('${sessionScope.erroDeletar}');
        </script>
        <c:remove var="erroDeletar" scope="session"/>
    </c:if>

    <fmt:bundle basename="messages">
    <header>
        <div class="titulo">BuscarX</div>

        <div class="usuario">
            <c:choose>
                <c:when test="${profissional != null}">
                    <a href="/SistemaVagas" class="btn profissional" >
                        <fmt:message key="BoasVindas" /> ${profissional.nome}
                        <c:set var="path" value="${path}/profissional.jsp" />
                        <c:set var="idUsuario" value="${profissional.idUsuario}" />
                    </a>
                </c:when>
                <c:when test="${empresa != null}">
                    <a href="/SistemaVagas" class="btn empresa">
                        <fmt:message key="BoasVindas" /> ${empresa.nome}
                        <c:set var="path" value="${path}/empresa.jsp" />
                        <c:set var="idUsuario" value="${empresa.idUsuario}" />
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/SistemaVagas" class="btn login">
                        <fmt:message key="entrar"/>
                    </a>
                    <a href="/SistemaVagas" class="btn cadastro">
                        <fmt:message key="cadastro"/>
                    </a>
                    <c:set var="path" value="/SistemaVagas" />
                </c:otherwise>
            </c:choose>
        </div>
    </header>
    <main>
        <div class="opcao">
            <div class="botao atualizar" onclick="atualizarUsuario()"> 
                <input type="hidden" name="action" value="update">
                <fmt:message key="atualizar"/>
            </div>
            <hr>
            <c:when test="${profissional != null}">
                    <div class="botao vagas" onclick="meusRegistros()">
                        <fmt:message key="Candidaturas"/>
                    </div>
                </c:when>
                <c:when test="${empresa != null}">
                    <div class="botao vagas" onclick="meusRegistros()">
                        <fmt:message key="Vagas"/>
                    </div>
                </c:when>
            <hr>
            <div class="botao desconectar" onclick="desconectarUsuario()">
                <fmt:message key="desconectar"/>
            </div>
            <hr>
            <div class="botao deletar" onclick="deletarUsuario()">
                <fmt:message key="deletar"/>
            </div>
        </div>
    </main>
    </fmt:bundle>
    <script>
        function atualizarUsuario(){
         window.location.href = "${path}";
        }
        function desconectarUsuario(){
            window.location.href = "/SistemaVagas/usuario?action=logout";
        }
        function minhasConsultas(){
            window.location.href = "/SistemaVagas/agendamento?action=minhasConsultas&idUsuario=${idUsuario}";
        }
        function deletarUsuario(){
            const confirmacao = confirm("Tem certeza que quer excluir sua conta?");
            if (confirmacao){
                window.location.href = "/SistemaVagas/usuario?action=deletarUsuario&idUsuario=${idUsuario}";
            }
        }
    </script>
</body>
</html>