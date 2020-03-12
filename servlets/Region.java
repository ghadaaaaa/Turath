package com.turath.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.turath.control.Recherche;
import com.turath.sdb.SDBManipulation;

/**
 * Servlet implementation class Region
 */
@WebServlet("/Region")
public class Region extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Region.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Region() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String nomReg =request.getParameter("nomReg");
		SDBManipulation sdb = new  SDBManipulation();
		sdb.connexionASDB();
		Recherche rech = new Recherche();
	    List<com.turath.model.Monument> mons= rech.rechMonumentParRegion(sdb.getDataset(),nomReg);
	    List<com.turath.model.Maison> mais = rech.rechMaisonParRegion(sdb.getDataset(), nomReg);
	    List<com.turath.model.Site>  sites = rech.rechSiteParRegion(sdb.getDataset(), nomReg);
	    List<com.turath.model.Espace> espaces = rech.rechEspaceParRegion(sdb.getDataset(), nomReg);
	    sdb.deconnexionDeSDB();
	   
	    request.setAttribute("mons", mons);
	    request.setAttribute("mais", mais);
	    request.setAttribute("sites", sites);
	    request.setAttribute("espaces", espaces);
	    

		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
