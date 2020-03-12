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
 * Servlet implementation class MaisonMap
 */
@WebServlet("/MaisonMap")
public class MaisonMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Maison.jsp";
       
 
    public MaisonMap() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String appelMaison = request.getParameter("appelMa");
		  SDBManipulation sdb = new  SDBManipulation();
		  String msg ="";
		  sdb.connexionASDB();
		  Recherche rech = new Recherche();
		  List<com.turath.model.Maison> mais= rech.rechMaisonParAppel(sdb.getDataset(), appelMaison);
		  sdb.deconnexionDeSDB();
		  
		  if (mais.isEmpty()|| mais.size()>1) 
	        {System.out.println("ERROR OF SIZE ");
			  msg= "Aucune maison"; }
		  else
		  {
			  com.turath.model.Maison mai = mais.get(0);
		      request.setAttribute("mai", mai);
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
