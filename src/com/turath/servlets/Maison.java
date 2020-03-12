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
 * Servlet implementation class Maison
 */
@WebServlet("/Maison")
public class Maison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Maison.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maison() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  int idMaison =Integer.parseInt( request.getParameter("idMaison"));
		  SDBManipulation sdb = new  SDBManipulation();
		  String msg ="";
		  sdb.connexionASDB();
		  Recherche rech = new Recherche();
		  com.turath.model.Maison mai= rech.rechMaisonParId (sdb.getDataset(),idMaison);
	      sdb.deconnexionDeSDB();
		  
		  if (mai == null) 
	        {System.out.println("nuuuuuuulll");
			  msg= "Aucune maison"; }
		  else
		  {System.out.println(mai.getAltitude());
			request.setAttribute("mai", mai);}
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
