package com.turath.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import com.turath.model.*;

public class Recherche {
	private static final float Min_SYN_SIM = (float) 0.8;
	private static final float Min_SEM_SIM = (float) 0.6;
	//private PythonInterpreter mypy;
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
	 
	 /****************************Recherche Maison par Id******************/
	 public Maison rechMaisonParId (Dataset dataset, int idMaison)
	 {
		 try
		
		 {
			  String qs1 = "Select ?descEltPatri ?altitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
			  		+ "where {graph ?g {"
			  		
		      + "?Maison <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idMaison+"."
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
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Maison mai = null;
			
			   List<String> appels;
			   List<String> images;
			   if(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    appels = rechAppelEP(dataset,idMaison);
				    images =rechImagesEP(dataset, idMaison);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idMaison,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
				  
				    }
			   return mai;
			 }
		 }
		 finally {}
		 }
	 /***************************Recherche Maison Par Appellation******************************************/
		public List<Maison> rechMaisonParAppel(Dataset dataset, String appel)
		{
			 try	
			 { String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?longitude "
				  		+ "?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
				  		+ "where {graph ?g {"
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#SappelerEP> ?AppellationEP."          
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceSol> ?surfaceSol."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceMaison> ?surfaceMaison."
		          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> \""+appel+"\" ."
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
		/***************Recherche d'une maison par région*************************/
		 public List<Maison> rechMaisonParRegion (Dataset dataset, String nomRegion)
		 {
			 try			
			 {    String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude"
				  + "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
				  + "where {graph ?g {"			 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#SeSituerEP> ?Region."          
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceSol> ?surfaceSol."
		          + "?Maison <http://www.w3.org/ontologies/patriArchi#surfaceMaison> ?surfaceMaison."
		          + "?Region <http://www.w3.org/ontologies/patriArchi#nomRegion> \""+nomRegion+"\" ."
		         + "}}";
				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
				 //  ResultSetFormatter.out(rs) ;
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
				 } } 
			 finally {} }
	 
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
	 /************************Recherche d'un espace par id*****************************/
	 
	 public Espace rechEspaceParId(Dataset dataset, int idEsp) {
			 try		
			 {
				 String qs1="Select ?idEltPatri ?descEltPatri ?altitude"
					  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"          
			          + "?Espace  <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idEsp+"." 
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
				   Espace esp = null;
				   List<String> appels;
				   List<String> images;
				   if(rs.hasNext()){
					    soln = rs.nextSolution() ;
					    appels = rechAppelEP(dataset,idEsp);
					    images =rechImagesEP(dataset, idEsp);
					    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
					    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    node = soln.get("typeEspace"); String typeEspace = node.toString();
					    esp= new Espace(idEsp,descEltPatri,altitude,longitude,
					    		dateConstruction,périodeConstruction, typeEspace, appels, images);
					    }
				   return esp;
				 }
			 } 
			 finally { }
		}
	 /************************RECHERCHE ESPACE PAR APPELLATION EP*****************/
	 public List<Espace> rechEspaceParAppel( Dataset dataset, String  appelEspace)
	 {
		 try	
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"
			 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#SappelerEP> ?AppellationEP."          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#typeEspace> ?typeEspace." 
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> \""+appelEspace+"\" ."
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
		 finally {  }
		 
	 }
	 
	 public List<Espace> rechEspaceParRegion (Dataset dataset, String nomRegion)
	 {
		 try		
		 {    String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude"
			  + "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"		 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#SeSituerEP> ?Region."          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi#typeEspace> ?typeEspace." 
	          + "?Region <http://www.w3.org/ontologies/patriArchi#nomRegion> \""+nomRegion+"\" ."
	         + "}}";
			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
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
			 } } 
		 finally { }}
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
	 
	 		/*********************Recherche d'un site par Id***********************/
		public Site rechSiteParId(Dataset dataset, int idSite) {
			 try		
			 {
				  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
				  + "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"       
		          + "?Site <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idSite+"." 
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
				   Site site=null;
				  
				   List<String> appels;
				   List<String> images;
				
				   if(rs.hasNext()){
					    soln = rs.nextSolution() ;
					   
					    appels = rechAppelEP(dataset,idSite);
					    images =rechImagesEP(dataset, idSite);
					    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
					    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    node = soln.get("surfaceSite"); String surfaceSite = node.toString();
					    site= new Site(idSite,descEltPatri,altitude,longitude,
					    		dateConstruction,périodeConstruction, surfaceSite, appels,images);
					    }
			
				   return site;
				 }
			 } 
			 finally { }
		 }
		
		 /********************Recherche Site Par Appellation EP***************************/
		 
		 public List<Site> rechSiteParAppel(Dataset dataset, String appel)
		 {
			 try			
			 {
				  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
				  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"	 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#SappelerEP> ?AppellationEP."          
		          + "?Site <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
		          + "?Site <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
		          + "?Site <http://www.w3.org/ontologies/patriArchi#surfaceSite> ?surfaceSite."
		          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> \""+appel+"\" ."
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
			 finally { }
		 }
		 public List<Site> rechSiteParRegion (Dataset dataset, String nomRegion)
		 {
			 try			
			 {
				  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
				  + "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"	 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#SeSituerEP> ?Region."          
		          + "?Site <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
		          + "?Site <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
		          + "?Site <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
		          + "?Site <http://www.w3.org/ontologies/patriArchi#surfaceSite> ?surfaceSite."
		          + "?Region <http://www.w3.org/ontologies/patriArchi#nomRegion> \""+nomRegion+"\" ."
		         + "}}";

				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
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
	 
	 			/*****************Recherche d'un monument par Id*******************/
	 public Monument rechMonumentParId(Dataset dataset, int idMonument) {
			 try
			 {
				  String qs1 = "Select ?descEltPatri ?altitude"
				  		+ "?longitude ?dateConstruction ?périodeConstruction ?typeMo "
				  		+ "where {graph ?g {"
			      + "?Monument <http://www.w3.org/ontologies/patriArchi#idEltPatri> "+idMonument+"."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#typeMo> ?typeMo."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."	         
		         + "}}";

				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
				 //  ResultSetFormatter.out(rs) ;
				   QuerySolution soln;
				   RDFNode node;
				   Monument mon = null;	
				   List<String> appels;
				   List<String> images;
				   if(rs.hasNext()){
					    soln = rs.nextSolution() ;
					    appels = rechAppelEP(dataset,idMonument);
					    images =rechImagesEP(dataset, idMonument);
					    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
					    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    node = soln.get("typeMo"); String typeMo = node.toString();
					    mon= new Monument(idMonument,descEltPatri,altitude,longitude,
					    		dateConstruction,périodeConstruction, typeMo, appels,images );
					    }
				   return mon;
				 }
			 } 
			 finally {}
		}
	 
	 /*****************************Recherche Monument Par Appellation*******************************/
	 
	 public List<Monument> rechMonumentParAppel(Dataset dataset,String appel)
	 {
		 try	
		 {    String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude"
			  + "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {"
			 
	          + "?Monument<http://www.w3.org/ontologies/patriArchi#SappelerEP> ?AppellationEP."          
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#typeMo> ?typeMo."
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> \""+appel+"\" ."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
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
				    		dateConstruction,périodeConstruction, typeMo, appels,images );			  
				    mons.add(mon);
				    }	
			   return mons;
			 } } 
		 finally {}}
	 
	               /************RECHERCHE MONUMENT PAR REGION************/
	 public List<Monument> rechMonumentParRegion (Dataset dataset, String nomRegion)
	 {
		 try		
		 {
			  String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude"
			  + "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {" 
	          + "?Monument<http://www.w3.org/ontologies/patriArchi#SeSituerEP> ?Region."          
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#idEltPatri> ?idEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#descEltPatri> ?descEltPatri." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#altitude> ?altitude."  
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#longitude> ?longitude." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#typeMo> ?typeMo." 
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#dateConstruction> ?dateConstruction."
	          + "?Monument <http://www.w3.org/ontologies/patriArchi#périodeConstruction> ?périodeConstruction."
	          + "?Region <http://www.w3.org/ontologies/patriArchi#nomRegion> \""+nomRegion+"\" ."
	         + "}}";
			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
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
				    node = soln.get("typeMo") ;String typeMo  = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    mon= new Monument(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction,typeMo, appels,images );  
			        mons.add(mon);
	    		    }
			   return mons;
			 }} 
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
		
		
	 	/*********************MOT_CLE*********************************/
	   public List<String> listeNomsRegions(Dataset dataset)
		{
			 try			
			 {
				   String qs1 = "Select  ?nomRegion  where {graph ?g {"
				 + "?Region <http://www.w3.org/ontologies/patriArchi#nomRegion> ?nomRegion.}}";
				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
					List<String> nomsRegs = new ArrayList<String>();
					QuerySolution soln;
					RDFNode node;
				   while (rs.hasNext()){
					    soln = rs.nextSolution() ;	    
					    node = soln.get("nomRegion") ; String nom = node.toString();
					    nomsRegs.add(nom);
					 }		
					return nomsRegs;
				 }
			 }  finally { }}
	 
	 
	 public List<String> listeNomsAppels(Dataset dataset)
		{
			 try		
			 {
				 String qs1 = "Select  ?appelEP  where {graph ?g {"
				 + "?AppellationEP <http://www.w3.org/ontologies/patriArchi#appelEP> ?appelEP.}}";
				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
					List<String> appels = new ArrayList<String>();
					QuerySolution soln;
					RDFNode node;
				   while (rs.hasNext()){
					    soln = rs.nextSolution() ;	    
					    node = soln.get("appelEP") ; String appel = node.toString();
					    appels.add(appel);
					 }		
					return appels;
				 }
			 }  finally {}}
	 

	      /************ Comparer Le mot clé aux régions ****************/
	 public List<EltPatri> comparerMotCleReg(Dataset dataset, String motCle)
	 {
		 List<EltPatri> elts = new ArrayList<EltPatri>();
		 List<String> regs= listeNomsRegions(dataset);
		 Iterator<String> iter = regs.iterator();
		 String reg="";
		 Similarite sim= new Similarite(0,0);
	
		 while (iter.hasNext() && !reg.toLowerCase().equals(motCle.toLowerCase())) 
			 {
			 reg =iter.next();
			 sim.synSimilarity(motCle, reg);
			 if(sim.getSynSim()>=Min_SYN_SIM ) 
			     {  elts.addAll(rechMonumentParRegion(dataset,reg));
				    elts.addAll(rechMaisonParRegion(dataset, reg));
				    elts.addAll( rechSiteParRegion(dataset, reg));
				    elts.addAll(rechEspaceParRegion(dataset, reg));
				  } 			 		
			 }
		 if (reg.toLowerCase().equals(motCle.toLowerCase()))
		    {
			elts.clear();
			elts.addAll(rechMonumentParRegion(dataset,reg));
		    elts.addAll(rechMaisonParRegion(dataset, reg));
		    elts.addAll( rechSiteParRegion(dataset, reg));
		    elts.addAll(rechEspaceParRegion(dataset, reg));
		    }
		 return elts;
		 
	 }
	      /******** Comparer Le mot clé aux appellations des elts *******/
	 
	 public List<EltPatri> comparerMotCleAppel(Dataset dataset, String motCle)
	 {
		 List<EltPatri> elts = new ArrayList<EltPatri>();
		 
		 List<String> appels= listeNomsAppels(dataset);
		 Iterator<String>  iter = appels.iterator();
		 String appel="";
		 Similarite sim= new Similarite(0,0);
		 while (iter.hasNext() ) 
			 {
			 appel =iter.next();
			 sim.synSimilarity(motCle.toLowerCase(), appel.toLowerCase());
			 if(sim.getSynSim()>=Min_SYN_SIM ) 
			 {      elts.addAll(rechMonumentParAppel(dataset,appel));
				    elts.addAll(rechMaisonParAppel(dataset, appel));
				    elts.addAll( rechSiteParAppel(dataset, appel));
				    elts.addAll(rechEspaceParAppel(dataset, appel));
		     }  
	
			 }
		 if (elts.isEmpty() )
		 {
			 Iterator<String>  iter2 = appels.iterator();
			 String appel2="";
			
			 while (iter2.hasNext() && !appel2.toLowerCase().equals(motCle.toLowerCase())) 
			 {
			 appel2 =iter2.next();
			 sim.semSimilarity(motCle, appel2);
			 if(sim.getSemSim()>=Min_SEM_SIM ) 
			 {      elts.addAll(rechMonumentParAppel(dataset,appel2));
				    elts.addAll(rechMaisonParAppel(dataset, appel2));
				    elts.addAll(rechSiteParAppel(dataset, appel2));
				    elts.addAll(rechEspaceParAppel(dataset, appel2));
		     }  
			 }
		 }
		 
		 
		 return elts;
	 }
	      /******** Comparer Le mot clé aux catégories des elts**********/
	
	     /**************************Recherche par mot clé***************/
				 public List<EltPatri> rechParMotCle(Dataset dataset, String motCle)
				 {  
					    List<EltPatri> elts = new ArrayList<EltPatri>();
				    	elts= comparerMotCleReg(dataset,motCle);
				    	if (elts.isEmpty()) 
				    	{
				    	   elts= comparerMotCleAppel(dataset,motCle);
				    	}
				    	return elts;
		             }
				 

}
