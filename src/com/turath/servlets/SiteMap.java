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
 * Servlet implementation class SiteMap
 */
@WebServlet("/SiteMap")
public class SiteMap extends HttpServlet {
	public static final String VUE ="/WEB-INF/Site.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiteMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  String appelSite= request.getParameter("appelSite");
		  SDBManipulation sdb = new  SDBManipulation();
	      String msg ="";
	      sdb.connexionASDB();
	      Recherche rech = new Recherche();
		  List<com.turath.model.Site> sites= rech.rechSiteParAppel(sdb.getDataset(), appelSite);
	      sdb.deconnexionDeSDB();
		  if (sites.isEmpty()|| sites.size()>1) 
	        {System.out.println("ERROR OF SIZE ");
			  msg= "Aucun site"; }
		  else
		  {
			  com.turath.model.Site site = sites.get(0);
		      request.setAttribute("site", site);
		  }
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
