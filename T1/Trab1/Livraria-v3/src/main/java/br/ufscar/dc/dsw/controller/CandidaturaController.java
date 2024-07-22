package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/candidatura")
public class CandidaturaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CandidaturaDAO dao;

	@Override
	public void init() {
		dao = new CandidaturaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try{
            switch (action) {
                case "minhasCandidaturas":
                    minhasCandidaturas(request, response, session);
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

    protected void minhasCandidaturas(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Candidatura> listCanditaturas = dao.getAllFromProfissional(Long.parseLong(request.getParameter("userId")));

        if (listCanditaturas.size() > 0) {
            session.setAttribute("listaCandidaturas", listCanditaturas);
        } else {
            session.setAttribute("semCanditaturas", "Este profissional não se candidatou a nenhuma vaga");
        }
        response.sendRedirect("SistemaCanditaturas/listaCandidatura.jsp");
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
                case "candidatar":
                    candidatar(request, response, session);
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

    protected void candidatar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		Object o = session.getAttribute("profissional");
        long id_vaga = Long.parseLong(request.getParameter("id_vaga"));
        long id_profissional = 0;
        
        if (o instanceof Profissional) {
            Profissional empresa = (Profissional) o;
            id_profissional = empresa.getIdProfissional();
        }

        Candidatura candidatura = new Candidatura(id_profissional, id_vaga);
        dao.insert(candidatura);

        response.sendRedirect("SistemaVagas/candidatura/minhasCandidaturas");
	}
}
