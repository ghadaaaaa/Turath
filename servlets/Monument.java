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
 * Servlet implementation class Monument
 */
@WebServlet("/Monument")
public class Monument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Monument.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monument() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  int idMonument =Integer.parseInt( request.getParameter("idMonument"));
		  SDBManipulation sdb = new  SDBManipulation();
		  String msg ="";
		  sdb.connexionASDB();
		  Recherche rech = new Recherche();
		  com.turath.model.Monument mon= rech.rechMonumentParId (sdb.getDataset(),idMonument);
		  sdb.deconnexionDeSDB();
		
		  
		  if (mon == null) 
	        {
			  msg= "Aucun monument"; }
		  else
		  {System.out.println(mon.getAltitude());
			request.setAttribute("mon", mon);}
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
