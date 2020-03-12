package com.turath.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.turath.control.Recherche;
import com.turath.sdb.SDBManipulation;

/**
 * Servlet implementation class Site
 */
@WebServlet("/Site")
public class Site extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Site.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Site() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int idSite =Integer.parseInt( request.getParameter("idSite"));
		SDBManipulation sdb = new  SDBManipulation();
		String msg ="";
		sdb.connexionASDB();
		Recherche rech = new Recherche();
		com.turath.model.Site site= rech.rechSiteParId (sdb.getDataset(),idSite);
		sdb.deconnexionDeSDB();
		
		  
		  if (site == null) 
	        {
			  msg= "Aucun site"; }
		  else
		  {
			System.out.println(site.getAltitude());
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
