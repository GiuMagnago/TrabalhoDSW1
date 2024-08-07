package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

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
            if (action.equals("/formCadastro")) {
                formCadastro(request, response, session);
            } else if (action.equals("/formUpdate")) {
                formUpdate(request, response, session);
            } else {
                invalidateRequest(request, response, session);
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

    protected void formCadastro(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		response.sendRedirect("/SistemaVagas/profissionais/cadastro.jsp");
	}

	protected void formUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		response.sendRedirect("/SistemaVagas/profissionais/atualizar.jsp");
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
            if (action.equals("/cadastro")) {
                register(request, response, session);
            } else if (action.equals("/atualizar")) {
                update(request, response, session);
            } else {
                invalidateRequest(request, response, session);
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }
    }

    private Date stringToDate(String s) {
        Date newDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newDate = new Date(format.parse(s).getTime());
        }
        catch (ParseException e)
        {

            e.printStackTrace();
        }
        return newDate;
    }

    protected void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
        Date dataNasc = stringToDate(request.getParameter("dataNasc"));

		Profissional profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, dataNasc);

		long idNewUser = usuarioDAO.inserirUsuario(profissional);
		if (idNewUser != 0) {
			profissional.setIdUsuario(idNewUser);
			session.setAttribute("profissional", profissional);
            session.setAttribute("empresa", null);
			response.sendRedirect("/SistemaVagas/usuario.jsp");
		} else {
			// em caso de erro, volta pra página de cadastro com os dados preenchidos
			session.setAttribute("email", email);
			session.setAttribute("senha", senha);
			session.setAttribute("cpf", cpf);
			session.setAttribute("nome", nome);
			session.setAttribute("telefone", telefone);
			session.setAttribute("sexo", sexo);
            session.setAttribute("dataNasc", dataNasc);

			session.setAttribute("ErrorCriarNovoUsuario", "Confira os dados");
            response.sendRedirect("/SistemaVagas/profissional/formCadastro");
		}
	}

    protected void update(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException  {
        Object o = session.getAttribute("profissional");
		Usuario usuario = null;
		if (o instanceof Usuario) {
			usuario = (Usuario) o;
		}
		
		if (usuario != null) {
			if (usuarioDAO.updateUsuario(usuario)) {
				Profissional profissional = (Profissional) usuario;
				session.setAttribute("profissional", profissional);
            	response.sendRedirect("/SistemaVagas/usuario.jsp");
			} else {
				session.setAttribute("erroAtualizarProfissional", "Cheque os dados inseridos");
            	response.sendRedirect("/SistemaVagas/atualizar/profissional.jsp");
			}
		}
	}

    protected void invalidateRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		session.invalidate();
		response.sendRedirect("SistemaVagas/index.jsp");
	}
}