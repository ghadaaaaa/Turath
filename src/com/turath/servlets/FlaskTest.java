package com.turath.servlets;


import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import com.turath.sdb.SDBManipulation;




public class FlaskTest {
    public static void main(String args[]) {
     
	 SDBManipulation sdb = new  SDBManipulation();
	 sdb.chargerFichierOWLDansSDB();
	 sdb.connexionASDB();
     
	 Dataset dataset = sdb.getDataset();
	 
	 try
	 {
		 int idEltPatri=3;
	
		 /******************Liste des appellation des types de maisons****************/
	 String qs1 = "Select ?appelTypeMa where {graph ?g {"
			+ "?AppellationTypeMa <http://www.w3.org/ontologies/patriArchi#AppeleMa> ?TypeMa." 
	        + "?TypeMa <http://www.w3.org/ontologies/patriArchi#ContenirMa> ?Maison." 
	        + "?AppellationTypeMa <http://www.w3.org/ontologies/patriArchi#appelTypeMa> ?appelTypeMa."
            + "?Maison <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idEltPatri+"."
      + "}}";

		 try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   ResultSetFormatter.out(rs) ;
			   
		 } 
		catch(Exception e) { e.printStackTrace();}
		 
		 /***********Liste des Orgas qui reconnaissent un elt patri**********/
		 int id= 2;
		 String qs2 = "Select ?acroOrga ?nomOrga where {graph ?g {"
					+ "?Organisation <http://www.w3.org/ontologies/patriArchi#acroOrga> ?acroOrga." 
			        + "?Organisation <http://www.w3.org/ontologies/patriArchi#nomOrga> ?nomOrga." 
			        + "?Organisation <http://www.w3.org/ontologies/patriArchi#ReconnaitreEP> ?EltPatri." 
			        + "?EltPatri <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+id+"."
			        
		      + "}}";
		 try(QueryExecution qExec = QueryExecutionFactory.create(qs2, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   ResultSetFormatter.out(rs) ;
			   
		 } 
		catch(Exception e) { e.printStackTrace();}

	 /**************liste des elts architecturaux d'un elt patri*************/
		 int idElt= 3;
		 String qs3 = "Select ?idEltArchi ?nomEltArchi ?appelTradi where {graph ?g {"
			
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#idEltArchi> ?idEltArchi." 
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#appelTradi> ?appelTradi."
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#nomEltArchi> ?nomEltArchi."
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#ContenuDansEP> ?EltPatri."
					+ "?EltPatri <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idElt+"."
			        
		      + "}}";
		 try(QueryExecution qExec = QueryExecutionFactory.create(qs3, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   ResultSetFormatter.out(rs) ;
			   
		 } 
		catch(Exception e) { e.printStackTrace();}
		 
		                   /****************Le type d'un elt archi******************/
		 int idEltArchi=1;
		 String qs4 = "Select ?typeEA where {graph ?g {"				
					+ "?TypeEA <http://www.w3.org/ontologies/patriArchi#typeEA> ?typeEA." 
					+ "?TypeEA <http://www.w3.org/ontologies/patriArchi#ContenirEltA> ?EltArchi."
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#idEltArchi> "+idEltArchi+"."       
		      + "}}";
		 try(QueryExecution qExec = QueryExecutionFactory.create(qs4, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   ResultSetFormatter.out(rs) ;
			   
		 } 
		catch(Exception e) { e.printStackTrace();}
		 
		                  /****************Les fonctions d'un elt archi*************/
		 int idEltA=1;
		 String qs5 = "Select ?fonction where {graph ?g {"	
				 
					+ "?FonctionEltArchi <http://www.w3.org/ontologies/patriArchi#fonction> ?fonction." 
					+ "?FonctionEltArchi <http://www.w3.org/ontologies/patriArchi#AssureeParEA> ?EltArchi."
					+ "?EltArchi <http://www.w3.org/ontologies/patriArchi#idEltArchi> "+idEltA+"."
		      + "}}";
		 try(QueryExecution qExec = QueryExecutionFactory.create(qs5, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   ResultSetFormatter.out(rs) ;
			   
		 } 
		catch(Exception e) { e.printStackTrace();}
		 
		                  /****************Les aspects d'une fonction***************/ 
		 			    	/*******************Les sources******************/
		 
		 					/******************Les personnes******************/
		 
     sdb.deconnexionDeSDB();
      
    
    } catch(Exception e) {}}}