package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/empresa/*")
public class EmpresaController extends HttpServlet {

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
            if (action.contains("/formCadastro")) {
                formCadastro(request, response, session);
            } else if ("/formUpdate".equals(action)) {
                //formUpdate(request, response, session);
            } else {
                //invalidateRequest(request, response, session);
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

	protected void formCadastro(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		response.sendRedirect("/SistemaVagas/empresas/cadastro.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        HttpSession session = request.getSession();

        try {
            if ("/cadastro".equals(action)) {
                register(request, response, session);
            } else if ("/atualizar".equals(action)) {
                update(request, response, session);
            } else {
                invalidateRequest(request, response, session);
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

	protected void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String cidade = request.getParameter("cidade");

		Empresa empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);

		long idNewUser = usuarioDAO.inserirUsuario(empresa);
		if (idNewUser != 0) {
			empresa.setIdUsuario(idNewUser);
			session.setAttribute("empresa", empresa);
			session.setAttribute("profissional", null);
			response.sendRedirect("/SistemaVagas/usuario.jsp");
		} else {
			// em caso de erro, volta pra página de cadastro com os dados preenchidos
			session.setAttribute("email", email);
			session.setAttribute("senha", senha);
			session.setAttribute("cnpj", cnpj);
			session.setAttribute("nome", nome);
			session.setAttribute("descricao", descricao);
			session.setAttribute("cidade", cidade);

			session.setAttribute("ErrorCriarNovoUsuario", "Confira os dados");
            response.sendRedirect("/SistemaVagas/empresa/formCadastro");
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		Object o = session.getAttribute("empresa");
		Usuario usuario = null;
		if (o instanceof Usuario) {
			usuario = (Usuario) o;
		}
		
		if (usuario != null) {
			if (usuarioDAO.updateUsuario(usuario)) {
				Empresa empresa = (Empresa) usuario;
				session.setAttribute("empresa", empresa);
            	response.sendRedirect("/SistemaVagas/usuario.jsp");
			} else {
				session.setAttribute("erroAtualizarEmpresa", "Cheque os dados inseridos");
            	response.sendRedirect("/SistemaVagas/atualizar/empresa.jsp");
			}
		}
	}

	protected void invalidateRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		session.invalidate();
		response.sendRedirect("SistemaVagas/index.jsp");
	}
}