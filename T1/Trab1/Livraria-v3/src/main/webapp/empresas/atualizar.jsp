<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <!DOCTYPE html>
                <html>

                <head>
                    <title>Sistema Vagas</title>
                </head>

                <body>
                    <fmt:bundle basename="messages">
                        <header>
                            <div class="titulo">
                                <h1>Atualizar Dados</h1>
                            </div>
                            <a href="/SistemaVagas/usuario.jsp" class="btn voltar">
                                <fmt:message key="voltar" />
                            </a>
                        </header>

                        <div class="formulario">
                            <form id="forms" action="/SistemaVagas/empresa/atualizar" method="post">
                                <h3>
                                    <fmt:message key="seus_dados ${sessionScope.empresa.getNome()}" />
                                </h3>

                                <label for="email">
                                    <fmt:message key="email" />
                                </label> <br>
                                <input type="email" id="email" name="email"
                                    value="${sessionScope.empresa.email != null ? sessionScope.empresa.email : ''}"
                                    required><br>

                                <label for="senha">
                                    <fmt:message key="senha" />
                                </label> <br>
                                <input type="password" id="senha" name="senha"
                                    value="${sessionScope.empresa.senha != null ? sessionScope.empresa.senha : ''}"
                                    required><br>

                                <label for="cnpj">
                                    <fmt:message key="cnpj" />
                                </label> <br>
                                <input type="number" id="cnpj" name="cnpj"
                                    value="${sessionScope.empresa.cnpj != null ? sessionScope.empresa.cnpj : ''}"
                                    required><br>

                                <label for="nome">
                                    <fmt:message key="nome" />
                                </label> <br>
                                <input type="text" id="nome" name="nome"
                                    value="${sessionScope.empresa.nome != null ? sessionScope.empresa.nome : ''}"
                                    required><br>

                                <label for="descricao">
                                    <fmt:message key="descricao" />
                                </label> <br>
                                <input type="text" id="descricao" name="descricao"
                                    value="${sessionScope.empresa.descricao != null ? sessionScope.empresa.descricao : ''}"
                                    required><br>

                                <label for="cidade">
                                    <fmt:message key="cidade" />
                                </label> <br>
                                <input type="text" id="cidade" name="cidade"
                                    value="${sessionScope.empresa.cidade != null ? sessionScope.empresa.cidade : ''}"
                                    required><br>

                                <input type="submit" class="confirmar" value="<fmt:message key='confirmar'/>">
                            </form>
                        </div>
                    </fmt:bundle>
                </body>
                </html>