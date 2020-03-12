package com.turath.sdb;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.JDBC;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.apache.jena.util.FileManager;



public class SDBManipulation {
	
	private Dataset dataset;
	private Store store;
	private String OWL_FILE = "E:\\patriArchi.owl"; 
	private String DB_URL = "jdbc:postgresql://localhost:5432/SDB"; 
	private String DB_USER = "Turath";                         
	private String DB_PASSWD = "Turath";
	
	
	public SDBManipulation(){}

	public Dataset getDataset() {
		return dataset;
	}
	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}    
	
	public void chargerFichierOWLDansSDB()
	{
		System.out.println("***************Début chargement****************");
		// create store description
        StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,DatabaseType.PostgreSQL);
        JDBC.loadDriverPGSQL();
        // create SDBConnection
        SDBConnection sdbConnection = new SDBConnection(DB_URL,DB_USER,DB_PASSWD);
        // connect to store
        this.store = SDBFactory.connectStore(sdbConnection,storeDesc);
        // connect store to dataset
        this.dataset = SDBFactory.connectDataset(this.store);

        // prepare the model
        Model tmpModel = ModelFactory.createDefaultModel();
        FileManager.get().readModel(tmpModel,OWL_FILE,"OWL");
        System.out.println(tmpModel.size());
        // add the model into the dataset
       
        
     // this.dataset.getDefaultModel().removeAll();
     // this.dataset.getDefaultModel().add(tmpModel);
      this.dataset.addNamedModel("patriArchi",tmpModel);
      Model model=  this.dataset.getNamedModel("patriArchi");
      System.out.println(model.size());
        // all done ... hopefully
        this.store.getConnection().close();
       this.store.close();
       System.out.println("***************Fin chargement****************");
	}
	
	
	public void connexionASDB()
	{ 
		System.out.println("***************Connexion****************");
        StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,DatabaseType.PostgreSQL);
        JDBC.loadDriverPGSQL();
        SDBConnection sdbConnection = new SDBConnection(DB_URL,DB_USER,DB_PASSWD);
        this.store = SDBFactory.connectStore(sdbConnection,storeDesc);
        this.dataset = SDBFactory.connectDataset(this.store);
    	System.out.println("***************Connecté****************");
	}
	 
	
	public void deconnexionDeSDB()
	{	
		System.out.println("***************Déconnexion****************");
	   this.store.getConnection().close();
	   this.store.close();
		System.out.println("***************Déconnecté****************");
	}
	


}
