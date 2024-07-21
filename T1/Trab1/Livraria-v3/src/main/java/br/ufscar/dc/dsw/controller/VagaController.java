package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/vaga")
public class VagaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VagaDAO dao;

	@Override
	public void init() {
		dao = new VagaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try{
            switch (action) {
                case "listarPorCidade":
                    listarPorCidade(request, response, session);
                    break;

                case "minhasVagas":
                    minhasVagas(request, response, session);
                    break;

                default:
                    invalidateRequest(request, response, session);
                    break;
            }
        }
        catch (ServletException e)
        {
            throw new ServletException(e);
        }
    }

    protected void listarPorCidade(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Vaga> listVagas = dao.getAllOrderByCidade();
        session.setAttribute("listaVagas", listVagas);
        response.sendRedirect("SistemaVagas/listaVagas.jsp");
    }

    protected void minhasVagas(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Vaga> listVagas = dao.getAllFromEmpresa(Long.parseLong(request.getParameter("userId")));

        if (listVagas.size() > 0) {
            session.setAttribute("listaVagas", listVagas);
        } else {
            session.setAttribute("semVagas", "Esta empresa não nenhuma vaga registrada");
        }
        response.sendRedirect("SistemaVagas/listaVagas.jsp");
    }

    protected void invalidateRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		session.invalidate();
		response.sendRedirect("SistemaVagas/index.jsp");
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try{
            switch (action) {
                case "criarVaga":
                    criarVaga(request, response, session);
                    break;

                default:
                    invalidateRequest(request, response, session);
                    break;
            }
        }
        catch (ServletException e)
        {
            throw new ServletException(e);
        }
    }

    protected void criarVaga(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		Object o = session.getAttribute("empresa");

        long id_empresa = 0;
        String cnpj_empresa = "";
        
        if (o instanceof Empresa) {
            Empresa empresa = (Empresa) o;
            id_empresa = empresa.getIdEmpresa();
            cnpj_empresa = empresa.getCNPJ();
        }

        double remuneracao = Double.parseDouble(request.getParameter("remuneracao"));
        String descricao  = request.getParameter("descricao");
        Date dataLimite = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            dataLimite = formatter.parse(request.getParameter("dataLimite"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dataLimite.compareTo(new Date()) > 0) {
            Vaga vaga = new Vaga(id_empresa, cnpj_empresa, descricao, remuneracao, dataLimite);
            dao.insert(vaga);
        } else {
            session.setAttribute("erroAgendamento", "Data invalida");
        }

        response.sendRedirect("SistemaVagas/vaga/minhasVagas");
	}
}