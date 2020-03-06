package com.turath.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import com.turath.model.*;

public class Recherche {
	
	public Recherche() {}
	
	 /********************************Liste toutes les maisons**********************/
	 public List<Maison> listeMaisons(Dataset dataset)
		{
			 try
			
			 {
				 String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
					  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
					  		+ "where {graph ?g {"        
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceSol> ?surfaceSol."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceMaison> ?surfaceMaison."
			      
			         + "}}";
				 try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
					   ResultSet rs = qExec.execSelect() ;
					   QuerySolution soln;
					   RDFNode node;
					   Maison mai;
					   List<Maison> mais = new ArrayList<Maison>();
					
					   List<String> appels;
					   List<String> images;
					   while(rs.hasNext()){
						    soln = rs.nextSolution() ;
						    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
						    appels = rechAppelEP(dataset,idEltPatri);
						    images =rechImagesEP(dataset, idEltPatri);
						    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
						    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
						    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
						    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
						    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
						    mai= new Maison(idEltPatri,descEltPatri,altitude,longitude,
						    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
						  
						    mais.add(mai);
						    }
					   return mais;
					 }
				 } 
				 finally {}
		 }
	 
	 /******************************Liste des espaces*********************************/
	 public List<Espace> listeEspaces (Dataset dataset)
	 {
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#typeEspace> ?typeEspace." 
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Espace espace;
			   List<Espace> espaces = new ArrayList<Espace>();
			   List<String> appels;
			   List<String> images;
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images = rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, typeEspace, appels, images);
				  
				    espaces.add(espace);
				    }
		
			   return espaces;
			 }
		 } 
		 finally { }
	 }
	 
	 
	 /******************************LISTE SITES*************************************/
	 public List<Site> listeSites (Dataset dataset)
	 {
		 try	
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"       
	          + "?Site <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi#surfaceSite> ?surfaceSite."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Site site;
			   List<Site> sites = new ArrayList<Site>();
			   List<String> appels;
			   List<String> images;
			
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images =rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSite"); String surfaceSite = node.toString();
				    site= new Site(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, surfaceSite, appels,images);
				  
				    sites.add(site);
				    }
		
			   return sites;
			 }
		 } 
		 finally {  }
	 }
	 /******************************Lister tous les monuments*************************************/

	 public List<Monument> listeMonuments (Dataset dataset)
	 {
		 try		
		 {
			  String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude "
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {"
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#typeMo> ?typeMo."
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			   QuerySolution soln;
			   RDFNode node;
			   Monument mon;
			   List<String> appels;
			   List<String> images;
			   List<Monument> mons = new ArrayList<Monument>();
			   
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images = rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeMo"); String typeMo = node.toString();
				    mon= new Monument(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, typeMo,appels,images );			  
				    mons.add(mon);
				    }
			   return mons;
			 }
		 } 
		 finally { }
	 }
	 
	 /************************/
		public List<String> rechAppelEP(Dataset dataset, int idEltPatri )
		{		
			 try			
			 {
			 String qs1 = "Select ?appelEP where {graph ?g {"
		          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#Appele> ?EltPatri."          
		          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> ?appelEP." 
		          + "?EltPatri <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idEltPatri+"."
		         + "}}";
			 
			  try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
		       ResultSet rs = qExec.execSelect() ;
			   //ResultSetFormatter.out(rs) ;
		       QuerySolution soln;
			   RDFNode node;
			   List<String> appels = new ArrayList<String>();
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("appelEP"); String appel = node.toString();
				    appels.add(appel);
				    }
		
			   return appels;
			    }
			 }
		  
		 finally { }
		}
		
		
		/**********************************************************************************/
		
		public List<String> rechImagesEP(Dataset dataset, int idEltPatri )
		{
			try
			 {	
			 String qs1 = "Select ?illustration where {graph ?g {"
		          + "?Illustration <http://www.w3.org/ontologies/patriArchi#IllustrerEP> ?EltPatri."          
		          + "?Illustration <http://www.w3.org/ontologies/patriArchi#illustration> ?illustration." 
		          + "?EltPatri <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idEltPatri+"."
		         + "}}";
			 
			  try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
		       ResultSet rs = qExec.execSelect() ;
			   //ResultSetFormatter.out(rs) ;
		       QuerySolution soln;
			   RDFNode node;
			   List<String> images = new ArrayList<String>();
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("illustration"); String appel = node.toString();
				    images.add(appel);
				    }
		
			   return images;
			    }
			 }
		  
		 finally {  }
			
		}

}
