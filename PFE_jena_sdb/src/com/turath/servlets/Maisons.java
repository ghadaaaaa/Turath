package com.turath.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.turath.control.Recherche;
import com.turath.model.*;
import com.turath.sdb.SDBManipulation;

/**
 * Servlet implementation class Maisons
 */
@WebServlet("/Maisons")
public class Maisons extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Maisons.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maisons() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SDBManipulation sdb = new  SDBManipulation();
		Recherche rech= new Recherche();
		sdb.connexionASDB();
		List<Maison> mais = rech.listeMaisons(sdb.getDataset());	
		sdb.deconnexionDeSDB();
		request.setAttribute("mais", mais);

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
