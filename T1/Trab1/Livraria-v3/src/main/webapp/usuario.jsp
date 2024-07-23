<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <!DOCTYPE html>
                <html>

                <head>
                    <title>SistemaVagas</title>
                </head>

                <body>
                    <h1>awbhduyawdn</h1>
                    <c:set var="profissional" value="${sessionScope.profissional}" />
                    <c:set var="empresa" value="${sessionScope.empresa}" />
                    <c:set var="path" value="/SistemaVagas/atualizar" />

                    <c:if test="${not empty sessionScope.erroDeletar}">
                        <script>
                            alert('${sessionScope.erroDeletar}');
                        </script>
                        <c:remove var="erroDeletar" scope="session" />
                    </c:if>

                    <main>
                        <div class="titulo">SistemaVagas</div>

                        <div class="usuario">
                            <c:choose>
                                <c:when test="${profissional == null && empresa == null}">
                                    <a href="/SistemaVagas" class="btn login">
                                        <fmt:message key="entrar" />
                                    </a>
                                    <a href="/SistemaVagas" class="btn cadastro">
                                        <fmt:message key="cadastro" />
                                    </a>
                                    <c:set var="path" value="/SistemaVagas" />
                                </c:when>
                                <c:otherwise>
                                    <div class="opcao">
                                        <div class="botao atualizar" onclick="atualizarUsuario()">
                                            atualizar
                                        </div>
                                        <hr>
                                        <c:when test="${empresa != null}">
                                            <div class="botao vagas" onclick="minhasVagas()">
                                                Vagas
                                            </div>
                                        </c:when>
                                        <c:when test="${profissional != null}">
                                            <div class="botao candidaturas" onclick="minhasCandidaturas()">
                                                Candidaturas
                                            </div>
                                        </c:when>
                                        <hr>
                                        <div class="botao desconectar" onclick="desconectarUsuario()">
                                            tchau
                                        </div>
                                        <hr>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </main>
                    <script>
                        function atualizarUsuario() {
                            window.location.href = "${path}";
                        }
                        function desconectarUsuario() {
                            window.location.href = "/SistemaVagas/usuario?action=logout";
                        }
                        function minhasVagas() {
                            window.location.href = "/SistemaVagas/vaga?action=minhasVagas&idUsuario=${empresa.getIdUsuario()}"
                        }

                        function minhasCandidaturas() {
                            window.location.href = "/SistemaVagas/vaga?action=minhasCandidaturas&idUsuario=${profissional.getIdUsuario()}";
                        }
                    </script>
                </body>

                </html>