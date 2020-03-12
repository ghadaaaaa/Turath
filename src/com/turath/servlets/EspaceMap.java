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
 * Servlet implementation class EspaceMap
 */
@WebServlet("/EspaceMap")
public class EspaceMap extends HttpServlet {
	public static final String VUE ="/WEB-INF/Espace.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EspaceMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		  String appelEsp= request.getParameter("appelEsp");
		  SDBManipulation sdb = new  SDBManipulation();
	      String msg ="";
	      sdb.connexionASDB();
	      Recherche rech = new Recherche();
		  List<com.turath.model.Espace> esps= rech.rechEspaceParAppel(sdb.getDataset(), appelEsp);
	      sdb.deconnexionDeSDB();
	      
		  if (esps.isEmpty()) 
	        {System.out.println("Vide Espace");
			  msg= "Aucun espace"; }
		  else if (esps.size()>1)
		  {System.out.println("Plusieurs espaces ont le mm nom ");
		  msg= "Plusieurs espaces"; }
		  
		  else
		  {
			  com.turath.model.Espace esp = esps.get(0);
		      request.setAttribute("esp", esp);
		  }
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
