<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Erro</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty usuarioLogado}">
                <h3>Informações do Usuário</h3>
                <p>Nome: ${usuario.nome}</p>
                <p>Email: ${usuario.email}</p>
                <p>Papel: ${usuario.papel}</p>
                <!-- Adicione mais atributos conforme necessário -->
            </c:when>
            <c:otherwise>
                <p>Não há informações do usuário disponíveis.</p>
            </c:otherwise>
        </c:choose>
        <center>
            <h1>Página não existenteefsfsef</h1>
        </center>	
    </body>
</html>