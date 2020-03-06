package com.turath.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.turath.model.*;
import com.turath.control.Recherche;
import com.turath.sdb.SDBManipulation;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    SDBManipulation sdb = new  SDBManipulation();
	    //sdb.chargerFichierOWLDansSDB();
	    
		Recherche rech= new Recherche();
		
		sdb.connexionASDB();
		List<Maison> mais = rech.listeMaisons(sdb.getDataset());	
		List<Site> sites = rech.listeSites(sdb.getDataset());
		List<Monument> mons = rech.listeMonuments(sdb.getDataset());
		List<Espace> esps = rech.listeEspaces(sdb.getDataset());
		sdb.deconnexionDeSDB();
	
		request.setAttribute("mais", mais);
		request.setAttribute("sites", sites);
		request.setAttribute("mons", mons);
		request.setAttribute("esps", esps);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}


}
