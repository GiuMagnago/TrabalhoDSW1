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
                    <c:set var="profissional" value="${sessionScope.profissional}" />
                    <c:set var="empresa" value="${sessionScope.empresa}" />
                    <c:set var="username" value="${(empresa != null) ? empresa.getNome() : profisional.getNome()}" />
                    <c:set var="pathUpdate" value="" />

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
                                    <div class="perfil">
                                        <h1>${username}</h1>
                                    </div>
                                    <div class="opcao">
                                        <button class="botao atualizar" onclick="atualizarUsuario()">
                                            Atualizar
                                        </button>
                                        <c:choose>
                                            <c:when test="${empresa != null}">
                                                <c:set var="pathUpdate" value="/SistemaVagas/empresas/atualizar.jsp" />
                                                <button class="botao vagas" onclick="minhasVagas()">
                                                    Vagas
                                                </button>
                                            </c:when>
                                            <c:when test="${profissional != null}">
                                                <button class="botao candidaturas" onclick="minhasCandidaturas()">
                                                    Candidaturas
                                                </button>
                                            </c:when>
                                        </c:choose>
                                        <button class="botao desconectar" onclick="desconectarUsuario()">
                                            Desconectar
                                        </button>
                                        <button class="botao deletar" onclick="deletarUsuario()">
                                            Deletar conta
                                        </button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </main>
                    <script>
                        function atualizarUsuario() {
                            window.location.href = "${pathUpdate}";
                        }
                        function desconectarUsuario() {
                            window.location.href = "/SistemaVagas/usuario/logout";
                        }
                        function minhasVagas() {
                            window.location.href = "/SistemaVagas/vaga/minhasVagas?idUsuario=${empresa.getIdUsuario()}"
                        }
                        function minhasCandidaturas() {
                            window.location.href = "/SistemaVagas/candidatura/minhasCandidaturas?idUsuario=${profissional.getIdUsuario()}";
                        }
                        function deletarUsuario() {
                            window.location.href = "/SistemaVagas/usuario/deletar";
                        }
                    </script>
                </body>

                </html>