<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                <!DOCTYPE html>
                <html>

                <head>
                    <title>SistemaVagas</title>
                </head>

                <body>

                    <c:if test="${not empty sessionScope.erroAtualizarCliente}">
                        <script>
                            alert("${sessionScope.erroAtualizarCliente}");
                        </script>
                        <c:remove var="erroAtualizarCliente" scope="session" />
                    </c:if>

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
                            <form id="forms" action="/SistemaVagas/profissional" method="POST">
                                <input type="hidden" name="profissional" value="${sessionScope.profissional}" />
                                <h3>
                                    <fmt:message key="seus_dados" />
                                </h3>

                                <label for="email">
                                    <fmt:message key="email" />
                                </label> <br>
                                <input type="email" id="email" name="email"
                                    value="${sessionScope.profissional.email != null ? sessionScope.profissional.email : ''}"
                                    required><br>

                                <label for="senha">
                                    <fmt:message key="senha" />
                                </label> <br>
                                <input type="password" id="senha" name="senha"
                                    value="${sessionScope.profissional.senha != null ? sessionScope.profissional.senha : ''}"
                                    required><br>

                                <label for="cpf">
                                    <fmt:message key="cpf" />
                                </label> <br>
                                <input type="number" id="cpf" name="cpf"
                                    value="${sessionScope.profissional.cpf != null ? sessionScope.profissional.cpf : ''}"
                                    required><br>

                                <label for="nome">
                                    <fmt:message key="nome" />
                                </label> <br>
                                <input type="text" id="nome" name="nome"
                                    value="${sessionScope.profissional.nome != null ? sessionScope.profissional.nome : ''}"
                                    required><br>

                                <label for="telefone">
                                    <fmt:message key="telefone" />
                                </label> <br>
                                <input type="text" id="telefone" name="telefone"
                                    value="${sessionScope.profissional.telefone != null ? sessionScope.profissional.telefone : ''}"
                                    required><br>

                                <label for="sexo">
                                    <fmt:message key="sexo" />
                                </label><br>

                                <select name="sexo" id="opcao">
                                    <option value="masculino" ${'masculino' eq
                                        String(sessionScope.profissional.sexo)? 'selected' : '' }>
                                        <fmt:message key="masculino" />
                                    </option>
                                    <option value="feminino" ${'masculino' eq
                                        String(sessionScope.profissional.sexo)? 'selected' : '' }>
                                        <fmt:message key="feminino" />
                                    </option>
                                </select> <br>

                                <label for="dataNasc">
                                    <fmt:message key="nascimento" />
                                </label> <br>
                                <input type="date" id="dataNasc" name="dataNasc" required
                                    value="${sessionScope.profissional.dataNasc!= null ? sessionScope.profissional.dataNasc: ''}"><br>

                                <input type="submit" class="confirmar" value="<fmt:message key='confirmar'/>">
                            </form>
                        </div>
                    </fmt:bundle>
                    <script>
                    </script>
                </body>

                </html>