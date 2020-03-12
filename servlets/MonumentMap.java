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
 * Servlet implementation class MonumentMap
 */
@WebServlet("/MonumentMap")
public class MonumentMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Monument.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonumentMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String appelMo=request.getParameter("appelMo");
		  SDBManipulation sdb = new  SDBManipulation();
		  String msg ="";
		  sdb.connexionASDB();
		  Recherche rech = new Recherche();
		  List<com.turath.model.Monument> mons= rech.rechMonumentParAppel(sdb.getDataset(), appelMo);
		  sdb.deconnexionDeSDB();
		  
		  if (mons.isEmpty()) 
	        {System.out.println("Vide");
			  msg= "Aucun mon"; }
		  else if (mons.size()>1)
		  {System.out.println("Plusieurs mons ont le mm nom ");
		  msg= "Plusieurs mons"; }
		  
		  else
		  {
			  com.turath.model.Monument mon = mons.get(0);
		      request.setAttribute("mon", mon);
		  }
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
