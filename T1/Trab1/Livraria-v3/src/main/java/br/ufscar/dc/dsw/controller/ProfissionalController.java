package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionals/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("ADMIN")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}
		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> listaProfissionals = dao.getAll();
        request.setAttribute("listaProfissionals", listaProfissionals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getEmpresas() {
        Map<Long, String> empresas = new HashMap<>();
        for (Empresa empresa : new EmpresaDAO().getAll()) {
            empresas.put(empresa.getId(), empresa.getNome());
        }
        return empresas;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("empresas", getEmpresas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = dao.get(id);
        request.setAttribute("profissional", profissional);
        request.setAttribute("empresas", getEmpresas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        Date dataNasc = null;
        try {
            dataNasc = new java.sql.Date(formato.parse(request.getParameter("datanasc")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Data de nascimento inválida.");
            apresentaFormCadastro(request, response);
            return;
        }

        Profissional profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, dataNasc);
        dao.insert(profissional);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        Date dataNasc = null;
        try {
            dataNasc = new java.sql.Date(formato.parse(request.getParameter("datanasc")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Data de nascimento inválida.");
            apresentaFormEdicao(request, response);
            return;
        }

        Profissional profissional = new Profissional(id, email, senha, cpf, nome, telefone, sexo, dataNasc);
        dao.update(profissional);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Profissional profissional = new Profissional(id);
        dao.delete(profissional);
        response.sendRedirect("lista");
    }
}