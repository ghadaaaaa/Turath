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
 * Servlet implementation class Espace
 */
@WebServlet("/Espace")
public class Espace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Espace.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Espace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  int idEsp =Integer.parseInt( request.getParameter("idEsp"));
		  SDBManipulation sdb = new  SDBManipulation();
		  String msg ="";
		  sdb.connexionASDB();
		  Recherche rech = new Recherche();
		  com.turath.model.Espace esp= rech.rechEspaceParId (sdb.getDataset(),idEsp);
	      sdb.deconnexionDeSDB();
	      			  
		  if (esp == null) 
	        { msg= "Aucun espace"; }
		  else
		  {request.setAttribute("esp", esp);}
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
