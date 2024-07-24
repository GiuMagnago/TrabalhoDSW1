package br.ufscar.dc.dsw.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

@WebServlet(urlPatterns = {"/usuario/*"})
public class UsuarioController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        HttpSession session = request.getSession();

        try {
            if (action.equals("/login")) {
                logar(request, response, session);
            } else if (action.equals("/logout")) {
                invalidateRequest(request, response, session);
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

    protected void logar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException
    {
        session.removeAttribute("erroLogarUsuario");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = usuarioDAO.getUsuario(email, senha);

        if (usuario != null) {
            if (usuario instanceof Empresa) {
                session.setAttribute("empresa", (Empresa) usuario);
                session.setAttribute("profissional", null);
            } else {
                session.setAttribute("empresa", null);
                session.setAttribute("profissional", (Profissional) usuario);
            }

            response.sendRedirect("/SistemaVagas/usuario.jsp");
        } 
        else
        {
            session.setAttribute("erroLogarUsuario", "Email ou Senha incorretos");
            response.sendRedirect("/SistemaVagas/login.jsp");
        }
    }

    protected void invalidateRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        session.invalidate();
        response.sendRedirect("/SistemaVagas/index.jsp");
    }
}