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
 * Servlet implementation class MotCle
 */
@WebServlet("/MotCle")
public class MotCle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/MotCle.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotCle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String keyWord = request.getParameter( "keyWord");
	    byte[] temporaire = keyWord.getBytes("ISO-8859-1");
	    String motCle = new String(temporaire , 0, temporaire .length, "UTF-8");
	    System.out.println(motCle.toLowerCase());
	    SDBManipulation sdb = new  SDBManipulation();
	    String msg ="";
		sdb.connexionASDB();
		Recherche rech = new Recherche();
		List<com.turath.model.EltPatri> elts= rech.rechParMotCle(sdb.getDataset(), motCle);
		sdb.deconnexionDeSDB();
		 if ( elts.isEmpty()) System.out.println("emptyyyyyyyyyyyys");
		 
		 if (elts.isEmpty()) 
	        { System.out.println("Aucun elt patrimonial");
			  msg= "Aucun elt patrimonial"; }
		 
		  else {request.setAttribute("elts", elts);}
		 
		  request.setAttribute("msg", msg);
		
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

}
